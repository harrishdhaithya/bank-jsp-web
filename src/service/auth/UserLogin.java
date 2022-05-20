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
import com.dao.UserDao;
import com.model.User;
import org.json.JSONException;
import org.json.JSONObject;

public class UserLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String s;
        while((s=br.readLine())!=null){
            sb.append(s);
        }
        PrintWriter out = resp.getWriter();
        try{
            JSONObject obj = new JSONObject(sb.toString());
            String email = obj.getString("email");
            String password = obj.getString("password");
            if(
                email==null ||
                password==null
            ){
                resp.setStatus(500);
                resp.setContentType("text/html");
                out.println("All the Fields are required...");
                return;
            }
            UserDao ud = Singleton.getUserDao();
            User user = ud.getUserByEmail(email);
            if(user==null){
                resp.setStatus(500);
                resp.setContentType("text/html");
                out.println("Incorrect Email...");
                return;
            }
            if(user.evalPassword(password)){
                HttpSession session = req.getSession(true);
                session.setAttribute("name", user.getFname());
                session.setAttribute("accno", user.getAccno());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("role", "user");
                resp.setStatus(200);
                resp.setContentType("text/html");
                resp.sendRedirect("/bank/menu/usermenu.jsp");
                return;
            }else{
                resp.setStatus(400);
                resp.setContentType("text/html");
                out.println("Incorrect Password...");
                return;
            }
        }catch(JSONException e){
            resp.setStatus(400);
            resp.setContentType("text/html");
            out.println("Incorrect Data format...");
            return;
        }
    }
    
}
