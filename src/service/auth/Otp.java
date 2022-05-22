package service.auth;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Singleton.Singleton;
import com.dao.UserDao;
import com.model.User;

public class Otp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String otp = req.getParameter("otp");
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();
        if(session==null){
            resp.setStatus(400);
            resp.setContentType("text/plain");
            out.println("Something went wrong...");
            resp.sendRedirect("/bank/auth/usersignup.jsp");
            return;
        }
        String aotp = (String)session.getAttribute("otp");
        if(otp.equals(aotp)){
            User u = (User)session.getAttribute("user");
            UserDao udao = Singleton.getUserDao();
            boolean success = udao.saveUser(u);
            out.println(success);
            if(!success){
                resp.setStatus(400);
                resp.setContentType("text/plain");
                out.println("Something went wrong...");
                session.invalidate();
                resp.sendRedirect("/bank/auth/usersignup.jsp");
                return;
            }
            resp.setStatus(200);
            resp.setContentType("text/plain");
            out.println("SignUp successfull...Go ahed and login.");
            session.invalidate();
            resp.sendRedirect("/bank/auth/userlogin.jsp");
            return;
        }
        resp.setStatus(400);
        resp.setContentType("text/plain");
        out.println("Incorrect OTP...");
        resp.sendRedirect("/bank/auth/usersignup.jsp");
        session.invalidate();
        return;
    }
}