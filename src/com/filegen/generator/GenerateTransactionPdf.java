package com.filegen.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.dao.TransactionDao;
import com.filegen.template.PdfTemplate;
import com.model.Transaction;


public class GenerateTransactionPdf {
    public static File generateRecord(Map<String,String> filter){
        TransactionDao tdao = new TransactionDao();
        List<Transaction> transactions = null;
        List<Object[]> records = new ArrayList<>();
        if(filter==null){
            transactions = tdao.getAllTransaction();
        }else if(filter.get("name").equals("accno")){
            String accno = filter.get("accno");
            transactions = tdao.getTransactionsByAccno(accno);
        }else if(filter.get("name").equals("date")){
            String from = filter.get("from");
            String to = filter.get("to");
            transactions = tdao.getTransactionBetweenDate(from, to);
        }
        for(Transaction t:transactions){
            Object[] o = {t.getId(),t.getSrc(),t.getDest(),t.getAmount(),t.getDate(),t.getTime()};
            records.add(o);
        }
        File f = PdfTemplate.createPdf(Transaction.class,records,filter);
        return f;
    }
}
