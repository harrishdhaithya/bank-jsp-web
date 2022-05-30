package service.record;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.filegen.generator.GenerateTransactionPdf;
import com.filegen.generator.GenerateTransactionXls;

public class GenerateRecord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter = req.getParameter("filter");
        String format = req.getParameter("format");
        PrintWriter out = resp.getWriter();
        if(
            filter==null||
            format==null
        ){
            resp.setStatus(400);
            resp.setContentType("text/plain");
            out.println("Illegal data format...");
            return;
        }
        if(filter.equals("date")){
            String from = req.getParameter("from");
            String to = req.getParameter("to");
            if(from==null||to==null){
                resp.setStatus(400);
                resp.setContentType("text/plain");
                out.println("Something went wrong...");
                return;
            }
            Map<String,String> filterMap = new HashMap<>();
            filterMap.put("name","date");
            filterMap.put("from",from);
            filterMap.put("to",to);
            if(format.equals("pdf")){
                File f = GenerateTransactionPdf.generateRecord(filterMap);
                if(f==null){
                    resp.setStatus(400);
                    resp.setContentType("text/plain");
                    out.println("Something went wrong...");
                    return;
                }
                FileInputStream fis = new FileInputStream(f);
                resp.setContentType("application/pdf");
                resp.setHeader("Content-Disposition", "attachment;filename=\""+f.getName()+"\"");
                int in;
                while((in=fis.read())!=-1){
                    out.write(in);
                }
                fis.close();
                out.close();
                return;
            }else if(format.equals("xls")){
                File f = GenerateTransactionXls.generateRecord(filterMap);
                if(f==null){
                    resp.setStatus(400);
                    resp.setContentType("text/plain");
                    out.println("Something went wrong...");
                    return;
                }
                FileInputStream fis = new FileInputStream(f);
                resp.setContentType("application/xlsx");
                resp.setHeader("Content-Disposition", "attachment;filename=\""+f.getName()+"\"");
                int in;
                while((in=fis.read())!=-1){
                    out.write(in);
                }
                fis.close();
                out.close();
                return;
            }
        }else if(filter.equals("accno")){
            String accno = req.getParameter("accno");
            if(accno==null){
                resp.setStatus(400);
                resp.setContentType("text/plain");
                out.println("Something went wrong...");
                return;
            }
            Map<String,String> filterMap = new HashMap<>();
            filterMap.put("name","accno");
            filterMap.put("accno",accno);
            if(format.equals("pdf")){
                File f = GenerateTransactionPdf.generateRecord(filterMap);
                if(f==null){
                    resp.setStatus(400);
                    resp.setContentType("text/plain");
                    out.println("Something went wrong...");
                    return;
                }
                FileInputStream fis = new FileInputStream(f);
                resp.setContentType("application/pdf");
                resp.setHeader("Content-Disposition", "attachment;filename=\""+f.getName()+"\"");
                int in;
                while((in=fis.read())!=-1){
                    out.write(in);
                }
                fis.close();
                out.close();
                return;
            }else if(format.equals("xls")){
                File f = GenerateTransactionXls.generateRecord(filterMap);
                if(f==null){
                    resp.setStatus(400);
                    resp.setContentType("text/plain");
                    out.println("Something went wrong...");
                    return;
                }
                FileInputStream fis = new FileInputStream(f);
                resp.setContentType("application/xlsx");
                resp.setHeader("Content-Disposition", "attachment;filename=\""+f.getName()+"\"");
                int in;
                while((in=fis.read())!=-1){
                    out.write(in);
                }
                fis.close();
                out.close();
                return;
            }
        }else if(filter.equals("none")){
            if(format.equals("pdf")){
                File f = GenerateTransactionPdf.generateRecord(null);
                if(f==null){
                    resp.setStatus(400);
                    resp.setContentType("text/plain");
                    out.println("Something went wrong...");
                    return;
                }
                FileInputStream fis = new FileInputStream(f);
                resp.setContentType("application/pdf");
                resp.setHeader("Content-Disposition", "attachment;filename=\""+f.getName()+"\"");
                int in;
                while((in=fis.read())!=-1){
                    out.write(in);
                }
                fis.close();
                out.close();
                return;
            }else if(format.equals("xls")){
                File f = GenerateTransactionXls.generateRecord(null);
                if(f==null){
                    resp.setStatus(400);
                    resp.setContentType("text/plain");
                    out.println("Something went wrong...");
                    return;
                }
                FileInputStream fis = new FileInputStream(f);
                resp.setContentType("application/xlsx");
                resp.setHeader("Content-Disposition", "attachment;filename=\""+f.getName()+"\"");
                int in;
                while((in=fis.read())!=-1){
                    out.write(in);
                }
                fis.close();
                out.close();
                return;
            }
        }
    }
    
}