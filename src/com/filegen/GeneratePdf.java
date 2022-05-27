package com.filegen;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.Singleton.Singleton;
import com.dao.TransactionDao;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.model.Transaction;


public class GeneratePdf {
    private static void AddFilterByDate(List<Transaction> transactions,Map<String,String> filter,Table trans){
        String from = filter.get("from");
        String to = filter.get("to");
        Cell description = new Cell(0,4);
        Cell filterCell = new Cell(0,2);
        Paragraph p1 = new Paragraph();
        int size = transactions.size();
        if(size>0){
            String startDate = transactions.get(0).getDate();
            String endDate = transactions.get(size-1).getDate();
            p1.add("Start Date: "+startDate+"\n");
            p1.add("End Date: "+endDate+"\n");
            p1.add("Total Number of transaction: "+size);
            p1.setBold();
        }else{
            p1.add("No Transactions Available...");
            p1.setBold();
        }
        description.add(p1);
        Paragraph p2 = new Paragraph();
        p2.add("Filter: Date\n");
        p2.add("From: "+from+ "\n");
        p2.add("To: "+to);
        p2.setBold();
        filterCell.add(p2);
        trans.addCell(description);
        trans.addCell(filterCell);
    }
    private static void AddNoFilter(List<Transaction> transactions,Table trans){
        Cell description = new Cell(0, 6);
        int size = transactions.size();
        Paragraph p = new Paragraph();
        if(size>0){
            String startDate = transactions.get(0).getDate();
            String endDate = transactions.get(size-1).getDate();
            p.add("Start Date: "+startDate+"\n");
            p.add("End Date: "+endDate+"\n");
            p.add("Total Number of transaction: "+size);
            // p.setBold();
            p.setFontSize(14);
        }else{
            p.add("No Transactions Available...");
            // p.setBold();
            p.setFontSize(14);
        }
        description.add(p);
        trans.addCell(description);
    }
    private static void AddFilterByAccno(List<Transaction> transactions,Map<String,String> filter,Table trans){
        Cell description = new Cell(0,4);
        Cell filterCell = new Cell(0,2);
        Paragraph p1 = new Paragraph();
        int size = transactions.size();
        if(size>0){
            String startDate = transactions.get(0).getDate();
            String endDate = transactions.get(size-1).getDate();
            p1.add("Start Date: "+startDate+"\n");
            p1.add("End Date: "+endDate+"\n");
            p1.add("Total Number of transaction: "+size);
            p1.setBold();
        }else{
            p1.add("No Transactions Available...");
            p1.setBold();
        }
        description.add(p1);
        Paragraph p2 = new Paragraph();
        p2.add("Filter:\n");
        p2.add("Source Account Number: "+filter.get("accno"));
        p2.setBold();
        filterCell.add(p2);
        trans.addCell(description);
        trans.addCell(filterCell);
    }
    private static void formTable(Table trans,List<Transaction> transactions){
        String[] headings = {"ID","Source","Destination","Amount","Date","Time"};
        for(String h:headings){
            Cell cell1 = new Cell();
            cell1.add(new Paragraph(h));
            cell1.setBackgroundColor(DeviceRgb.GREEN);
            cell1.setTextAlignment(TextAlignment.CENTER);
            cell1.setPaddingTop(10f);
            cell1.setPaddingBottom(10f);
            trans.addCell(cell1);
        }
        for(Transaction t:transactions){
            String id = Integer.toString(t.getId());
            String src = t.getSrc();
            String dest = t.getDest();
            String amount = Double.toString(t.getAmount());
            String date = t.getDate();
            String time = t.getTime();
            String[] datas = {id,src,dest,amount,date,time};
            for(String data:datas){
                Cell celli1 = new Cell();
                celli1.add(new Paragraph(data));
                celli1.setTextAlignment(TextAlignment.CENTER);
                celli1.setPaddingTop(8f);
                celli1.setPaddingBottom(8f);
                trans.addCell(celli1);
            }
        }
    }
    public static File generateRecord(Map<String,String> filter){
        File f=null;
        String path = "records/";
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
        String df = dformat.format(new Date());
        String filepath = path+"transaction-"+df+".pdf";
        System.out.println(filepath);
        try {
            f=new File(filepath);
            PdfWriter writer = new PdfWriter(f);
            PdfDocument pdfdoc = new PdfDocument(writer);
            Document document = new Document(pdfdoc);
            document.setMargins(30F, 30F, 30F, 30F);
            Table header = new Table(new float[]{100F,500F});
            header.setWidth(new UnitValue(UnitValue.PERCENT,100));
            ImageData imgdata = ImageDataFactory.create("img/logo.jpg");
            Cell logo = new Cell();
            logo.add(new Image(imgdata).setHeight(100f).setWidth(new UnitValue(UnitValue.PERCENT,100)).setHeight(70));
            Cell heading = new Cell();
            heading.add(new Paragraph("Banking System").setFontSize(43f).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE));
            header.addCell(logo);
            header.addCell(heading);
            document.add(header);
            Table trans = new Table(new float[]{50F,100F,100F,100F,100F,100F});
            Cell title = new Cell(0,6);
            title.add(new Paragraph("Transaction Records").setTextAlignment(TextAlignment.CENTER).setFontSize(28F));
            trans.addCell(title);
            List<Transaction> transactions = null;
            TransactionDao tdao = Singleton.getTransactionDao();
            if(filter==null){
                transactions=tdao.getAllTransaction();
                AddNoFilter(transactions, trans);
            }else if(filter.get("name").equals("accno")){
                transactions = tdao.getTransactionsByAccno(filter.get("accno"));
                AddFilterByAccno(transactions, filter, trans);
            }else if(filter.get("name").equals("date")){
                transactions=tdao.getTransactionBetweenDate(filter.get("from"),filter.get("to"));
                AddFilterByDate(transactions, filter, trans);
            }else{
                document.close();
                throw new Exception("Illegal Filter");
            }
            formTable(trans, transactions);
            document.add(trans);
            document.close();
        } catch (Exception e) {
            f=null;
            System.out.println(e);
            System.out.println("Exception caught...");
        }
        
        return f;
    }
}
