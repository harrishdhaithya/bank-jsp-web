package service.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Bank;
import com.controller.Otp;
import com.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends HttpServlet{
    private static final long serialVersionUID = 1L;
    public SignUp(){}
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
            String fname = obj.getString("fname");
            String lname = obj.getString("lname");
            String email = obj.getString("email");
            String phone = obj.getString("phone");
            double deposit = Double.parseDouble(obj.getString("deposit"));
            String password = obj.getString("password");
            if(
                fname==null||
                lname==null||
                email==null||
                phone==null||
                password==null
            ){
                resp.setStatus(400);
                out.println("All the fields are required...");
                return;
            }
            User user = new User(fname, lname, phone, email, password, deposit);
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            String OTP = Otp.generateOTP();
            session.setAttribute("otp", OTP);
            boolean success = Otp.mailOTP(OTP,email);
            if(success){
                resp.setStatus(200);
                resp.sendRedirect("/bank/auth/otp.jsp");
                return;
            }else{
                resp.setStatus(400);
                out.println("Something went wrong.");
                return;
            }
        }catch(JSONException e){
            resp.setStatus(400);
            out.println("Incorrect Data format...");
            return;
        }
    }
}