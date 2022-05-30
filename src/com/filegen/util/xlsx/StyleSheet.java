package com.filegen.util.xlsx;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StyleSheet{
    static CellStyle getHeaderStyle(XSSFWorkbook workbook){
        CellStyle headStyle = workbook.createCellStyle();
        Font headfont = workbook.createFont();
        headfont.setBold(true);
        headfont.setFontHeightInPoints((short)32);
        headStyle.setFont(headfont);
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        return headStyle;
    }
    static CellStyle getSubHeaderStyle(XSSFWorkbook workbook){
        CellStyle subheadStyle = workbook.createCellStyle();
        Font subheadfont = workbook.createFont();
        subheadfont.setFontHeightInPoints((short)22);
        subheadStyle.setFont(subheadfont);
        subheadStyle.setAlignment(HorizontalAlignment.CENTER);
        return subheadStyle;
    }
    static CellStyle getDescStyle(XSSFWorkbook workbook){
        CellStyle descStyle = workbook.createCellStyle();
        descStyle.setWrapText(true);
        descStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return descStyle;
    }
}
