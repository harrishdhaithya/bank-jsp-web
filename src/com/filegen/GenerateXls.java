package com.filegen;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Date;
import java.io.File;
import java.io.FileOutputStream;
import com.dao.TransactionDao;
import com.model.Transaction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GenerateXls {
    public static File generateRecord(List<Transaction> transactions){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        ArrayList<Object[]> datas = new ArrayList<>();
        Object[] title = {"ID","Source","Destination","Amount","Date","Time"};
        datas.add(title);
        for(Transaction t:transactions){
            int id = t.getId();
            String src = t.getSrc();
            String dest = t.getDest();
            double amount = t.getAmount();
            String date = t.getDate();
            String time = t.getTime();
            Object[] data = {id,src,dest,amount,date,time};
            datas.add(data);
        }
        int rownum=0;
        for(Object[] rowset:datas){
            Row row = sheet.createRow(rownum++);
            int cellnum = 0;
            for(Object obj:rowset){
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String){
                    cell.setCellValue((String)obj);
                }else if(obj instanceof Integer){
                    cell.setCellValue((Integer)obj);
                }else if(obj instanceof Double){
                    cell.setCellValue((Double)obj);
                }
            }
        }
        String path = "records/";
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
        String df = dformat.format(new Date());
        String filepath = path+"transaction-"+df+".xlsx";
        System.out.println(filepath);
        File file = null;
        try{
            file = new File(filepath);
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);    
        }catch(IOException ex){
            System.out.println(ex);
            file=null;
        }
        return file;
    }    
}
