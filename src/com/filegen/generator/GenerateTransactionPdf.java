package com.filegen.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.Singleton.Singleton;
import com.dao.TransactionDao;
import com.filegen.util.pdf.PdfConstructor;
import com.model.Transaction;


public class GenerateTransactionPdf {
    static String getTransactionData(List<Transaction> transactions){
        int size = transactions.size();
        String data;
        if(size>0){
            String startDate = transactions.get(0).getDate();
            String endDate = transactions.get(size-1).getDate();
            data = "Start Date: "+startDate+"\n"+
                    "End Date: "+endDate+"\n"+
                    "Total Transactions: "+size;
        }else{
            data = "No Transactions Found";
        }   
        return data;
    }
    static String getFilterData(Map<String,String> filter){
        if(filter==null){
            return null;
        }
        String name = filter.get("name");
        String data;
        if(name.equals("accno")){
            data = "Filter By: Account Number"+"\n"+
                    "Account Number: "+filter.get("accno");
            return data;
        }else if(name.equals("date")){
            data = "Filter By: Date"+"\n"+
                    "Start: "+filter.get("from")+"\n"+
                    "End: "+filter.get("to");
            return data;
        }
        return null;
    }
    public static File generateRecord(Map<String,String> filter){
        PdfConstructor builder = null;
        TransactionDao tdao = Singleton.getTransactionDao();
        List<Transaction> transactions = null;
        try {
            builder = new PdfConstructor("records/test.pdf");
            builder.addHeader();
            builder.addSubHeader("Transaction Records");
            if(filter==null){
                transactions = tdao.getAllTransaction();
                builder.addDesc(getTransactionData(transactions));
            }else{
                if(filter.get("name").equals("accno")){
                    transactions = tdao.getTransactionsByAccno(filter.get("accno"));
                }else if(filter.get("name").equals("date")){
                    transactions = tdao.getTransactionBetweenDate(filter.get("from"), filter.get("to"));
                }else{
                    return null;
                }
                builder.addDesc(getTransactionData(transactions), getFilterData(filter));
            }
            String[] labels = {"ID","Source","Destination","Amount","Date","Time"};
            List<Object[]> records = new ArrayList<>();
            for(Transaction t:transactions){
                Object[] o = {t.getId(),t.getSrc(),t.getDest(),t.getAmount(),t.getDate(),t.getTime()};
                records.add(o);
            }
            builder.addRecordTable(labels,records);
            builder.close();
        } catch (IOException e) {
            System.out.println("Something went wrong...");
            return null;
        }
        return builder.getFile();
    }
}
