package com.filegen.template;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.filegen.util.pdf.PdfConstructor;

public class PdfTemplate {
    public static File createPdf(String docTitle,String docDesc,String filterDesc,String[] labels,List<Object[]> records){
        PdfConstructor pdfConstructor = null;
        try {
            SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
            String df = dformat.format(new Date());
            String filepath = "records/transaction-"+df+".pdf";
            pdfConstructor = new PdfConstructor(filepath);
            pdfConstructor.addHeader();
            pdfConstructor.addSubHeader(docTitle);
            if(filterDesc==null){
                pdfConstructor.addDesc(docDesc);
            }else{
                pdfConstructor.addDesc(docDesc,filterDesc);
            }
            pdfConstructor.addRecordTable(labels, records);
            pdfConstructor.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return pdfConstructor.getFile();
    }
}
