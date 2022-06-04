package api.transaction;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.Singleton.Singleton;
import com.dao.TransactionDao;

public class TransactionByAccno extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accno = req.getParameter("accno");
        TransactionDao tdao = Singleton.getTransactionDao();
        List<Transaction> transactions = tdao.getTransactionsByAccno(accno);
        JSONArray jsarr = new JSONArray();
        try{
            for(Transaction t:transactions){
                JSONObject jobj = new JSONObject();
                jobj.put("id", t.getId());
                jobj.put("src", t.getSrc());
                jobj.put("dest", t.getDest());
                jobj.put("amount", t.getAmount());
                jobj.put("date", t.getDate());
                jobj.put("time", t.getTime());
                jsarr.put(jobj);
            }
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().print(jsarr.toString());
            return;
        }catch(JSONException ex){
            resp.setContentType("text/plain");
            resp.setStatus(400);
            resp.getWriter().println("Something went wrong");
        }
        
    }
}
