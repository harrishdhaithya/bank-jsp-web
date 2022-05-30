package com.filegen.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.dao.TransactionDao;
import com.filegen.template.PdfTemplate;
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
        TransactionDao tdao = new TransactionDao();
        List<Transaction> transactions = null;
        String docDesc = null;
        String filterDesc = null;
        String[] labels = {"Id","Source","Destination","Amount","Date","Time"};
        List<Object[]> records = new ArrayList<>();
        if(filter==null){
            transactions = tdao.getAllTransaction();
            docDesc = getTransactionData(transactions);
        }else if(filter.get("name").equals("accno")){
            String accno = filter.get("accno");
            transactions = tdao.getTransactionsByAccno(accno);
            docDesc = getTransactionData(transactions);
            filterDesc = getFilterData(filter);
        }else if(filter.get("name").equals("date")){
            String from = filter.get("from");
            String to = filter.get("to");
            transactions = tdao.getTransactionBetweenDate(from, to);
            docDesc = getTransactionData(transactions);
            filterDesc = getFilterData(filter);
        }
        for(Transaction t:transactions){
            Object[] o = {t.getId(),t.getSrc(),t.getDest(),t.getAmount(),t.getDate(),t.getTime()};
            records.add(o);
        }
        File f = PdfTemplate.createPdf("Transaction Records", docDesc, filterDesc, labels, records);
        return f;
    }
}
