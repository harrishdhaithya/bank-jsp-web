package api.user;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.Singleton.Singleton;
import com.dao.UserDao;
import com.model.User;
import org.json.JSONArray;
import org.json.JSONObject;

public class AllUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            JSONArray jarr = new JSONArray();
            UserDao udao = Singleton.getUserDao();
            List<User> users = udao.getAllUsers();
            for(User u:users){
                JSONObject uobj = new JSONObject();
                uobj.put("name", u.getFname()+" "+u.getLname());
                uobj.put("accno", u.getAccno());
                uobj.put("phone", u.getPhone());
                uobj.put("email", u.getEmail());
                uobj.put("balance",u.getBalance());
                jarr.put(uobj);
            }
            resp.setStatus(200);
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.setContentType("application/json");
            resp.getWriter().print(jarr.toString());
            return;
        }catch(Exception ex){
            resp.setStatus(400);
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.setContentType("text/plain");
            resp.getWriter().println("Something went wrong...");
            ex.printStackTrace();
            return;
        }
    }
}
