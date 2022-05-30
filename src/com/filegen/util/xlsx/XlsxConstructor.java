package com.filegen.util.xlsx;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

public class XlsxConstructor extends XlsxBean {
    private int row = 0;
    private int col = 0;
    public XlsxConstructor(String filepath,int cellcount){
        super(filepath,cellcount);
    }
    public void addHeader(){
        Row header = sheet.createRow(row);
        Cell heading= header.createCell(col);
        heading.setCellValue("Banking System");
        heading.setCellStyle(StyleSheet.getHeaderStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(0,2,0,cellcount-1));
        row+=3;
        col=0;
    }
    public void addSubHeader(String title){
        Row subHeader = sheet.createRow(3);
        Cell subheading = subHeader.createCell(col);
        subheading.setCellValue(title);
        subheading.setCellStyle(StyleSheet.getSubHeaderStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(row,row+1,col,cellcount-1));
        row+=2;
        col=0;
    }
    public void addDesc(String desc){
        Row desRow = sheet.createRow(row);
        Cell descCell = desRow.createCell(0);
        descCell.setCellStyle(StyleSheet.getDescStyle(workbook));
        descCell.setCellValue(desc);
        sheet.addMergedRegion(new CellRangeAddress(row, row+2, col, cellcount-1));
        row+=3;
        col=0;
    }
    public void addDesc(String desc,String filter){
        Row desRow = sheet.createRow(row);
        Cell descCell = desRow.createCell(0);
        Cell filterCell = desRow.createCell(3);
        descCell.setCellStyle(StyleSheet.getDescStyle(workbook));
        filterCell.setCellStyle(StyleSheet.getDescStyle(workbook));
        descCell.setCellValue(desc);
        filterCell.setCellValue(filter);
        sheet.addMergedRegion(new CellRangeAddress(row,row+2,0, 2));
        sheet.addMergedRegion(new CellRangeAddress(row,row+2,3, 5));
        row+=3;
        col=0;
    }
    public void addLabel(String[] labels){
        Row labelRow = sheet.createRow(row);
        int count = 0;
        for(String label:labels){
            Cell cell = labelRow.createCell(count++);
            cell.setCellValue(label);
        }
        row+=1;
        col=0;
    }
    public void addRecords(List<Object[]> records){
        for(Object[] record:records){
            Row recordRow = sheet.createRow(row++);
            for(Object o:record){
                Cell cell = recordRow.createCell(col++);
                if(o instanceof String){
                    cell.setCellValue((String)o);
                }else if(o instanceof Integer){
                    cell.setCellValue((int)o);
                }else if(o instanceof Double){
                    cell.setCellValue((double)o);
                }
            }
            col=0;
        }
    }
    public void adjustCellSize(){
        for(int i=0;i<6;i++){
            sheet.autoSizeColumn(i);
        }
    }
    public void write() throws IOException{
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        workbook.close();
    }
}
