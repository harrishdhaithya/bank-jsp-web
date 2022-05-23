package service.auth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.controller.TwoFAAuth;
import com.model.Admin;
import com.model.User;

public class EvalAuth extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(false);
        if(session==null){
            resp.setStatus(400);
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
                resp.setStatus(200);
                resp.sendRedirect("/bank/menu/usermenu.jsp");
                return;
            }else if(role.equals("admin")){
                Admin admin =(Admin)session.getAttribute("user");
                session.removeAttribute("user");
                session.removeAttribute("secret");
                session.setAttribute("name", admin.getName());
                session.setAttribute("empid", admin.getEmpid());
                session.setAttribute("email", admin.getEmail());
                session.setAttribute("role", "admin");
                resp.setStatus(200);
                resp.sendRedirect("/bank/menu/adminmenu.jsp");
                return;
            }
        }
        resp.setStatus(400);
        out.println("Incorrect code...");
        return;
    }
}
