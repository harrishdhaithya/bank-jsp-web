package api.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Singleton.Singleton;
import com.dao.UserDao;
import com.model.User;
import org.json.JSONException;
import org.json.JSONObject;

public class FindUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accno = req.getParameter("accno");
        UserDao udao = Singleton.getUserDao();
        User u = udao.getUserByAccno(accno);
        JSONObject uobj = new JSONObject();
        try {
            uobj.put("name", u.getFname()+" "+u.getLname());
            uobj.put("accno", u.getAccno());
            uobj.put("phone", u.getPhone());
            uobj.put("email", u.getEmail());
            uobj.put("balance",u.getBalance());
            resp.setContentType("application/json");
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.setStatus(200);
            resp.getWriter().print(uobj.toString());
            return;
        } catch (JSONException e) {
            resp.setContentType("text/plain");
            resp.addHeader("Access-Control-Allow-Origin", "*");
            resp.setStatus(400);
            resp.getWriter().print("Someting went wrong"); 
            e.printStackTrace();
            return;
        }
    }
}
