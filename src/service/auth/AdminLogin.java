package service.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Singleton.Singleton;
import com.dao.AdminDao;
import com.dao.AdminSecretDao;
import com.model.Admin;
import org.json.JSONException;
import org.json.JSONObject;


public class AdminLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s=br.readLine())!=null) {
            sb.append(s);
        }
        br.close();
        PrintWriter out = resp.getWriter();
        try{
            JSONObject obj = new JSONObject(sb.toString());
            String email = obj.getString("email");
            String password = obj.getString("password");
            AdminDao adao = Singleton.getAdminDao();
            AdminSecretDao asdao = Singleton.getAdminSecretDao();
            Admin admin = adao.getAdminByEmail(email);
            if(admin==null){
                resp.setStatus(400);
                resp.setContentType("text/html");
                out.println("Incorrect Email");
                return;
            }
            if(admin.evalPassword(password)){
                HttpSession session = req.getSession(true);
                // session.setAttribute("name", admin.getName());
                // session.setAttribute("empid", admin.getEmpid());
                // session.setAttribute("email", admin.getEmail());
                // session.setAttribute("role", "admin");
                session.setAttribute("user", admin);
                session.setAttribute("secret", asdao.getSecret(admin.getEmpid()));
                session.setAttribute("role", "admin");
                resp.setStatus(200);
                resp.sendRedirect("/bank/auth/evalsecret.jsp");
                return;
            }
            
        }catch(JSONException ex){
            out.println(ex.toString());
        }

    }
}
