package com.filegen.generator;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.io.File;
import com.Singleton.Singleton;
import com.dao.TransactionDao;
import com.filegen.util.xlsx.XlsxConstructor;
import com.model.Transaction;

public class GenerateTransactionXls {
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
        XlsxConstructor builder = new XlsxConstructor("records/test.xlsx", 6);
        builder.addHeader();
        builder.addSubHeader("Transaction Records");
        TransactionDao tdao = Singleton.getTransactionDao();
        List<Transaction> transactions = null;
        if(filter==null){
            transactions = tdao.getAllTransaction();
            builder.addDesc(getTransactionData(transactions));
        }else if(filter.get("name").equals("date")){
            String from = filter.get("from");
            String to = filter.get("to");
            transactions = tdao.getTransactionBetweenDate(from,to);
            builder.addDesc(getTransactionData(transactions), getFilterData(filter));
        }else if(filter.get("name").equals("accno")){
            String accno = filter.get("accno");
            transactions = tdao.getTransactionsByAccno(accno);
            builder.addDesc(getTransactionData(transactions),getFilterData(filter));
        }
        String[] labels = {"ID","Source","Destination","Amount","Date","Time"};
        builder.addLabel(labels);
        List<Object[]> records = new ArrayList<>();
        for(Transaction t:transactions){
            Object[] o = {t.getId(),t.getSrc(),t.getDest(),t.getAmount(),t.getDate(),t.getTime()};
            records.add(o);
        }
        builder.addRecords(records);
        builder.adjustCellSize();
        return builder.getFile();
    }
       
}
