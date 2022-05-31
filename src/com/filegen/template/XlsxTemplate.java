package com.filegen.template;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.filegen.util.common.MetaDataHandler;
import com.filegen.util.xlsx.XlsxConstructor;

public class XlsxTemplate {
    public static File createXlsx(Class<?> cls,List<Object[]> records,Map<String,String> filter){
        String docTitle = MetaDataHandler.generateTitle(cls);
        String docDesc = MetaDataHandler.generateRecordDesc(cls,records);
        String filterDesc = MetaDataHandler.generateFilter(cls, filter);
        String[] labels = MetaDataHandler.getLabels(cls);
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
        String df = dformat.format(new Date());
        String filepath = "records/transaction-"+df+".pdf";
        XlsxConstructor xlsxConstructor = new XlsxConstructor(filepath, labels.length);
        xlsxConstructor.addHeader();
        xlsxConstructor.addSubHeader(docTitle);
        if(filterDesc==null){
            xlsxConstructor.addDesc(docDesc);
        }else{
            xlsxConstructor.addDesc(docDesc, filterDesc);
        }
        xlsxConstructor.addLabel(labels);
        xlsxConstructor.addRecords(records);
        xlsxConstructor.adjustCellSize();
        try {
            xlsxConstructor.write();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return xlsxConstructor.getFile();
    }
}
