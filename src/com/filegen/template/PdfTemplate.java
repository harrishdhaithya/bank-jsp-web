package com.filegen.template;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.filegen.util.common.MetaDataHandler;
import com.filegen.util.pdf.PdfConstructor;

public class PdfTemplate {
    public static File createPdf(Class<?> cls,List<Object[]> records,Map<String,String> filter){
        String docTitle = MetaDataHandler.generateTitle(cls);
        String docDesc = MetaDataHandler.generateRecordDesc(cls,records);
        String filterDesc = MetaDataHandler.generateFilter(cls, filter);
        String[] labels = MetaDataHandler.getLabels(cls);
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
