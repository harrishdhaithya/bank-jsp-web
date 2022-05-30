package com.filegen.util.xlsx;

import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class XlsxBean {
    protected File file = null;
    protected XSSFWorkbook workbook = null;
    protected XSSFSheet sheet = null;
    protected int cellcount;
    public XlsxBean(String pathname,int cellcount){
        file = new File(pathname);
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet();
        this.cellcount=cellcount;
    }
    public File getFile(){
        return file;
    }
}
