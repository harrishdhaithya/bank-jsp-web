package com.filegen.template;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.filegen.util.xlsx.XlsxConstructor;

public class XlsxTemplate {
    public static File createXlsx(String docTitle,String docDesc,String filterDesc,String[] labels,List<Object[]> records){
        XlsxConstructor xlsxConstructor = new XlsxConstructor("records/sample.xlsx", labels.length);
        xlsxConstructor.addHeader();
        xlsxConstructor.addSubHeader(docTitle);
        if(filterDesc==null){
            xlsxConstructor.addDesc(docDesc);
        }else{
            xlsxConstructor.addDesc(docDesc, filterDesc);
        }
        xlsxConstructor.addLabel(labels);
        xlsxConstructor.addRecords(records);
        try {
            xlsxConstructor.write();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return xlsxConstructor.getFile();
    }
}
