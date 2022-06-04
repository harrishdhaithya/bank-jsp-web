package service.auth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.controller.TwoFAAuth;
import com.model.Admin;
import com.model.User;
import org.json.JSONException;
import org.json.JSONObject;

public class EvalAuth extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        PrintWriter out = resp.getWriter();
        Cookie[] cook = req.getCookies();
        System.out.println(cook.length);
        for(Cookie c:cook){
            System.out.println(c.getName());
            System.out.println(c.getValue());
        }
        HttpSession session = req.getSession(false);
        if(session==null){
            resp.setStatus(400);
            System.out.println("No Session");
            out.println("Something went wrong...");
            return;
        }
        String secret = (String)session.getAttribute("secret");
        String acode = TwoFAAuth.getTOTPCode(secret);
        String role = (String)session.getAttribute("role");
        if(code.equals(acode)){
            if(role.equals("user")){
                User user = (User)session.getAttribute("user");
                session.removeAttribute("user");
                session.removeAttribute("secret");
                session.setAttribute("name", user.getFname());
                session.setAttribute("accno", user.getAccno());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("role", "user");
                JSONObject jobj = new JSONObject();
                try {
                    resp.setContentType("application/json");
                    resp.setStatus(200);
                    jobj.put("accno", user.getAccno());
                    jobj.put("role", "user");
                    out.print(jobj.toString());
                } catch (JSONException e) {
                    resp.setContentType("text/plain");
                    resp.setStatus(400);
                    out.println("Something went wrong...");
                    e.printStackTrace();
                }
                // resp.sendRedirect("/bank/menu/usermenu.jsp");
                return;
            }else if(role.equals("admin")){
                Admin admin =(Admin)session.getAttribute("user");
                session.removeAttribute("user");
                session.removeAttribute("secret");
                session.setAttribute("name", admin.getName());
                session.setAttribute("empid", admin.getEmpid());
                session.setAttribute("email", admin.getEmail());
                session.setAttribute("role", "admin");
                JSONObject jobj = new JSONObject();
                try {
                    resp.setContentType("application/json");
                    resp.setStatus(200);
                    jobj.put("empid", admin.getEmpid());
                    jobj.put("role", "admin");
                    out.print(jobj.toString());
                } catch (JSONException e) {
                    resp.setContentType("text/plain");
                    resp.setStatus(400);
                    out.println("Something went wrong...");
                    e.printStackTrace();
                }
                // resp.sendRedirect("/bank/menu/adminmenu.jsp");
                return;
            }
        }
        resp.setStatus(400);
        out.println("Incorrect code...");
        return;
    }
}
