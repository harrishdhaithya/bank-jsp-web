package service.googleauth;

import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.Singleton.Singleton;
import com.dao.AdminDao;
import com.dao.UserDao;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.model.Admin;
import com.model.User;


public class GoogleLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String inp;
        StringBuilder sb = new StringBuilder();
        while((inp=reader.readLine())!=null){
            sb.append(inp);
        }
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
            .setAudience(Collections.singletonList("875838647414-pt8dh19mhtm9osjevk8gj0fdukq2s1mt.apps.googleusercontent.com"))
            .build();
        PrintWriter out = resp.getWriter();
        GoogleIdToken idToken;
        try {
            JSONObject jobj = new JSONObject(sb.toString());
            String code = jobj.getString("code");
            String role = jobj.getString("role");
            idToken = verifier.verify(code);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                boolean emailVerified = payload.getEmailVerified();
                JSONObject object = new JSONObject();
                if(emailVerified){
                    HttpSession session = req.getSession();
                    if(role.equals("user")){
                        UserDao udao = Singleton.getUserDao();
                        User user = udao.getUserByEmail(email);
                        if(user!=null){
                            session.setAttribute("name", user.getFname());
                            session.setAttribute("accno", user.getAccno());
                            session.setAttribute("email", user.getEmail());
                            session.setAttribute("role", "user");
                            object.put("accno", user.getAccno());
                            object.put("role", "user");
                            object.put("status", "Success");
                            resp.setContentType("application/json");
                            resp.setStatus(200);
                            out.println(object.toString());
                            return;
                        }else{
                            resp.setContentType("text/plain");
                            resp.setStatus(400);
                            out.println("Invalid Login");
                            return;
                        }
                    }else if(role.equals("admin")){
                        AdminDao adao = Singleton.getAdminDao();
                        Admin admin = adao.getAdminByEmail(email);
                        if(admin!=null){
                            session.setAttribute("name", admin.getName());
                            session.setAttribute("empid", admin.getEmpid());
                            session.setAttribute("email", admin.getEmail());
                            session.setAttribute("role", "admin");
                            object.put("empid", admin.getEmpid());
                            object.put("role", "user");
                            object.put("status", "Success");
                            resp.setContentType("application/json");
                            resp.setStatus(200);
                            out.println(object.toString());
                            return;
                        }else{
                            resp.setContentType("text/plain");
                            resp.setStatus(400);
                            out.println("Invalid Login");
                            return;
                        }
                    }
                }
            } else {
                resp.setContentType("text/plain");
                resp.setStatus(400);
                out.println("Authentication Failed...Try Again.");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setContentType("text/plain");
            resp.setStatus(400);
            out.println("Something went wrong");
            return;
        }
    }
}
