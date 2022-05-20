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

public class Transaction extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String s;
        PrintWriter out = resp.getWriter();
        if((s=br.readLine())!=null){
            sb.append(s);
        }
        try{
            JSONObject obj = new JSONObject(sb.toString());
            String accno = obj.getString("accno");
            double amount = Double.parseDouble(obj.getString("amount"));
            HttpSession session = req.getSession(false);
            if(accno==null){
                resp.setStatus(400);
                resp.setContentType("text/html");
                out.println("All the fields are required...");
                return;
            }
            if(session==null){
                resp.setStatus(400);
                resp.setContentType("text/html");
                System.out.println("Redirect...");
                resp.sendRedirect("/bank");
                return;
            }
            boolean success = Bank.makeTransaction((String)session.getAttribute("accno"), accno, amount);
            if(success){
                resp.setStatus(200);
                resp.setContentType("text/plain");
                out.println("Transaction Successfull...");
            }else{
                resp.setStatus(400);
                resp.setContentType("text/plain");
                out.println("Transaction Failed...");
            }
        }catch(JSONException E){
            resp.setStatus(200);
            resp.setContentType("text/plain");
            out.println("Illegal format...");
        }
    }
}
