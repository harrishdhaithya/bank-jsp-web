package com.filegen;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.io.File;
import java.io.FileOutputStream;
import com.Singleton.Singleton;
import com.dao.TransactionDao;
import com.model.Transaction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GenerateXls {
    public static File generateRecord(Map<String,String> filter){
        TransactionDao tdao = Singleton.getTransactionDao();
        List<Transaction> transactions = null;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        String[] title = {"ID","Source","Destination","Amount","Date","Time"};
        CellStyle headStyle = workbook.createCellStyle();
        Font headfont = workbook.createFont();
        headfont.setBold(true);
        headfont.setFontHeightInPoints((short)32);
        headStyle.setFont(headfont);
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        CellStyle subheadStyle = workbook.createCellStyle();
        Font subheadfont = workbook.createFont();
        // subheadfont.setBold(true);
        subheadfont.setFontHeightInPoints((short)22);
        subheadStyle.setFont(subheadfont);
        subheadStyle.setAlignment(HorizontalAlignment.CENTER);
        Row header = sheet.createRow(0);
        Cell heading= header.createCell(0);
        heading.setCellValue("Banking System");
        heading.setCellStyle(headStyle);
        sheet.addMergedRegion(new CellRangeAddress(0,2,0,5));
        Row subHeader = sheet.createRow(3);
        Cell subheading = subHeader.createCell(0);
        subheading.setCellValue("Transaction Records");
        subheading.setCellStyle(subheadStyle);
        sheet.addMergedRegion(new CellRangeAddress(3,4,0,5));
        int rownum=5;
        CellStyle descStyle = workbook.createCellStyle();
        descStyle.setWrapText(true);
        descStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        if(filter==null){
            transactions = tdao.getAllTransaction();
            Row desRow = sheet.createRow(rownum);
            Cell descCell = desRow.createCell(0);
            int size = transactions.size();
            descCell.setCellStyle(descStyle);
            if(size>0){
                String startDate = transactions.get(0).getDate();
                String endDate = transactions.get(size-1).getDate();
                String desc = "Start Date: "+startDate+"\n"+
                                "End Date: "+endDate+"\n"+
                                "Total Number of Transactions: "+size;
                descCell.setCellValue(desc);
            }else{
                descCell.setCellValue("No Transaction found...");
            }
            sheet.addMergedRegion(new CellRangeAddress(rownum, rownum+2, 0, 5));
        }else if(filter.get("name").equals("date")){
            String from = filter.get("from");
            String to = filter.get("to");
            transactions = tdao.getTransactionBetweenDate(from,to);
            Row row = sheet.createRow(rownum);
            Cell descCell = row.createCell(0);
            Cell filterCell = row.createCell(3);
            int size = transactions.size();
            descCell.setCellStyle(descStyle);
            filterCell.setCellStyle(descStyle);
            if(size>0){
                String startDate = transactions.get(0).getDate();
                String endDate = transactions.get(size-1).getDate();
                String desc = "Start Date: "+startDate+"\n"+
                "End Date: "+endDate+"\n"+
                "Total Number of Transactions: "+size;
                descCell.setCellValue(desc);
            }else{
                descCell.setCellValue("No Transaction found...");
            }
            String filterDesc = "Filtered By: Date \n"+
                                "From: "+from+"\n"+
                                "To: "+to;
            filterCell.setCellValue(filterDesc);
            sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+2,0, 2));
            sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+2,3, 5));
        }else if(filter.get("name").equals("accno")){
            String accno = filter.get("accno");
            transactions = tdao.getTransactionsByAccno(accno);
            Row row = sheet.createRow(rownum);
            Cell descCell = row.createCell(0);
            Cell filterCell = row.createCell(3);
            int size = transactions.size();
            descCell.setCellStyle(descStyle);
            filterCell.setCellStyle(descStyle);
            if(size>0){
                String startDate = transactions.get(0).getDate();
                String endDate = transactions.get(size-1).getDate();
                String desc = "Start Date: "+startDate+"\n"+
                "End Date: "+endDate+"\n"+
                "Total Number of Transactions: "+size;
                descCell.setCellValue(desc);
            }else{
                descCell.setCellValue("No Transaction found...");
            }
            String filterDesc = "Filtered By: Account Number\n"+
                                "Account Number: "+accno;
            filterCell.setCellValue(filterDesc);
            sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+2,0, 2));
            sheet.addMergedRegion(new CellRangeAddress(rownum,rownum+2,3, 5));
        }
        rownum+=3;
        Row titRow = sheet.createRow(rownum++);
        int count=0;
        for (String t : title) {
            Cell idcell = titRow.createCell(count++);
            idcell.setCellValue(t);
        }
        for(Transaction t:transactions){
            Row row = sheet.createRow(rownum++);
            Cell idcell = row.createCell(0);
            idcell.setCellValue(t.getId());
            Cell srcCell = row.createCell(1);
            srcCell.setCellValue(t.getSrc());
            Cell destCell = row.createCell(2);
            destCell.setCellValue(t.getDest());
            Cell amtCell = row.createCell(3);
            amtCell.setCellValue(t.getAmount());
            Cell dateCell = row.createCell(4);
            dateCell.setCellValue(t.getDate());
            Cell timeCell = row.createCell(5);
            timeCell.setCellValue(t.getTime());
        }
        for(int i=0;i<6;i++){
            sheet.autoSizeColumn(i);
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
            workbook.close();
        }catch(IOException ex){
            System.out.println(ex);
            file=null;
        }
        return file;
    }    
}
