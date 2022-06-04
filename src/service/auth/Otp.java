package service.auth;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Singleton.Singleton;
import com.controller.TwoFAAuth;
import com.dao.UserDao;
import com.dao.UserSecretDao;
import com.model.User;
import com.model.UserSecret;

public class Otp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String otp = req.getParameter("otp");
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();
        if(session==null){
            resp.setContentType("text/plain");
            resp.sendError(400,"Something went wrong...");
            resp.sendRedirect("/bank/auth/usersignup.jsp");
            return;
        }
        String aotp = (String)session.getAttribute("otp");
        if(otp.equals(aotp)){
            User u = (User)session.getAttribute("user");
            String secret = TwoFAAuth.generateSecretKey();
            UserSecretDao usdao = Singleton.getUserSecretDao();
            UserSecret us = new UserSecret(u.getAccno(), secret);
            UserDao udao = Singleton.getUserDao();
            boolean success = udao.saveUser(u)&&usdao.saveSecret(us);

            if(success){
                resp.setContentType("text/plain");
                session.invalidate();
                resp.setStatus(200);
                out.println(secret);
                return;
            }
            session.invalidate();
            resp.setContentType("text/plain");
            out.println("Something went wrong...");
            resp.setStatus(400);
            return;
        }
        session.invalidate();
        resp.setContentType("text/plain");
        out.println("Incorrect OTP...");
        resp.setStatus(400);
        return;
    }
}
