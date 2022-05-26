package com.filegen;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.util.Random;
import com.Singleton.Singleton;
import com.dao.TransactionDao;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.Transaction;




public class GeneratePdf {
    public static String generateRandom(){
        String s = "";
        Random rand = new Random();
        for(int i=0;i<=4;i++){
            s+=rand.nextInt(10);
        }
        return s;
    }
    public static File generateRecord(List<Transaction> transactions){
        File f=null;
        try {
            Document document = new Document();
            document.setMargins(30F, 30F, 30F, 30F);
            String path = "records/";
            SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
            String df = dformat.format(new Date());
            String filepath = path+"transaction-"+df+".pdf";
            f=new File(filepath);
            OutputStream os = new FileOutputStream(f);
            PdfWriter.getInstance(document, os);
            document.open();
            PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("ID"));
            pdfPCell1.setBackgroundColor(BaseColor.GRAY);
            pdfPCell1.setPaddingTop(10F);
            pdfPCell1.setPaddingBottom(10F);
            pdfPCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Source"));
            pdfPCell2.setBackgroundColor(BaseColor.GRAY);
            pdfPCell2.setPaddingTop(10F);
            pdfPCell2.setPaddingBottom(10F);
            pdfPCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Destination"));
            pdfPCell3.setBackgroundColor(BaseColor.GRAY);
            pdfPCell3.setPaddingTop(10F);
            pdfPCell3.setPaddingBottom(10F);
            pdfPCell3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("Amount"));
            pdfPCell4.setBackgroundColor(BaseColor.GRAY);
            pdfPCell4.setPaddingTop(10F);
            pdfPCell4.setPaddingBottom(10F);
            pdfPCell4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            PdfPCell pdfPCell5 = new PdfPCell(new Paragraph("Date"));
            pdfPCell5.setBackgroundColor(BaseColor.GRAY);
            pdfPCell5.setPaddingTop(10F);
            pdfPCell5.setPaddingBottom(10F);
            pdfPCell5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            PdfPCell pdfPCell6 = new PdfPCell(new Paragraph("Time"));
            pdfPCell6.setBackgroundColor(BaseColor.GRAY);
            pdfPCell6.setPaddingTop(10F);
            pdfPCell6.setPaddingBottom(10F);
            pdfPCell6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            PdfPTable pdfPTable = new PdfPTable(new float[]{50F,100F,100F,100F,100F,100F});
            pdfPTable.addCell(pdfPCell1);
            pdfPTable.addCell(pdfPCell2);
            pdfPTable.addCell(pdfPCell3);
            pdfPTable.addCell(pdfPCell4);
            pdfPTable.addCell(pdfPCell5);
            pdfPTable.addCell(pdfPCell6);
            for(Transaction t:transactions){
                PdfPCell pdfPCelli1 = new PdfPCell(new Paragraph(Integer.toString(t.getId())));
                // pdfPCell1.setBackgroundColor(BaseColor.GRAY);
                pdfPCelli1.setPaddingTop(10F);
                pdfPCelli1.setPaddingBottom(10F);
                pdfPCelli1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                PdfPCell pdfPCelli2 = new PdfPCell(new Paragraph(t.getSrc()));
                // pdfPCell2.setBackgroundColor(BaseColor.GRAY);
                pdfPCelli2.setPaddingTop(10F);
                pdfPCelli2.setPaddingBottom(10F);
                pdfPCelli2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                PdfPCell pdfPCelli3 = new PdfPCell(new Paragraph(t.getDest()));
                // pdfPCell3.setBackgroundColor(BaseColor.GRAY);
                pdfPCelli3.setPaddingTop(10F);
                pdfPCelli3.setPaddingBottom(10F);
                pdfPCelli3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                PdfPCell pdfPCelli4 = new PdfPCell(new Paragraph(Double.toString(t.getAmount())));
                // pdfPCell4.setBackgroundColor(BaseColor.GRAY);
                pdfPCelli4.setPaddingTop(10F);
                pdfPCelli4.setPaddingBottom(10F);
                pdfPCelli4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                PdfPCell pdfPCelli5 = new PdfPCell(new Paragraph(t.getDate()));
                // pdfPCell5.setBackgroundColor(BaseColor.GRAY);
                pdfPCelli5.setPaddingTop(10F);
                pdfPCelli5.setPaddingBottom(10F);
                pdfPCelli5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                PdfPCell pdfPCelli6 = new PdfPCell(new Paragraph(t.getTime()));
                // pdfPCell6.setBackgroundColor(BaseColor.GRAY);
                pdfPCelli6.setPaddingTop(10F);
                pdfPCelli6.setPaddingBottom(10F);
                pdfPCelli6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                pdfPTable.addCell(pdfPCelli1);
                pdfPTable.addCell(pdfPCelli2);
                pdfPTable.addCell(pdfPCelli3);
                pdfPTable.addCell(pdfPCelli4);
                pdfPTable.addCell(pdfPCelli5);
                pdfPTable.addCell(pdfPCelli6);
            }
            document.add(pdfPTable);
            document.close();
            os.close();
 
        } catch (Exception e) {
            System.out.println("Exception caught...");
            f=null;
        }
        return f;
    }
    public static void main(String args[]) throws Exception {           
        TransactionDao tdao = Singleton.getTransactionDao();
        File f=generateRecord(tdao.getAllTransaction());
        
     } 
}
