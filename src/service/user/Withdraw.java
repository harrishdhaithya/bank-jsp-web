package service.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.controller.Bank;
import org.json.JSONException;
import org.json.JSONObject;

public class Withdraw extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String s;
        while((s=br.readLine())!=null){
            sb.append(s);
        }
        PrintWriter out = resp.getWriter();
        try {
            JSONObject obj = new JSONObject(sb.toString());
            double amount = Double.parseDouble(obj.getString("amount"));
            HttpSession session = req.getSession(false);
            if(session==null){
                resp.sendRedirect("/bank");
            }
            boolean success = Bank.withdraw((String)session.getAttribute("accno"), amount);
            if(!success){
                resp.setStatus(500);
                resp.setContentType("text/plain");
                out.println("Transaction Failed...");
                return;
            }else{
                resp.setStatus(200);
                resp.setContentType("text/plain");
                out.println("Transaction Successfull...");
                return;
            }
        }catch(JSONException je) {
            resp.setStatus(500);
            resp.setContentType("text/plain");
            out.println("Incorrect data format...");
            return;
        }catch(NumberFormatException ex){
            resp.setStatus(500);
            resp.setContentType("text/plain");
            out.println("Incorrect Number Format...");
            return;
        }catch(NullPointerException ne){
            resp.setStatus(500);
            resp.setContentType("text/plain");
            out.println("Null values are not accepted...");
        }
    }
}
