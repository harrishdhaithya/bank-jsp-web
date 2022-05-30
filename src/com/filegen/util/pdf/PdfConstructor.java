package com.filegen.util.pdf;
import java.net.MalformedURLException;
import java.util.List;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import java.io.IOException;

public class PdfConstructor extends PdfBean {
    public PdfConstructor(String filepath) throws IOException{
        super(filepath);
    }
    public void addHeader() throws MalformedURLException{
        Table header = new Table(new float[]{100F,500F});
        header.setBorder(new SolidBorder(DeviceGray.GRAY,1));
        header.setWidth(new UnitValue(UnitValue.PERCENT,100));
        ImageData imgdata = ImageDataFactory.create("img/logo.jpg");
        Cell logo = new Cell();
        logo.setPadding(0);
        Style logoStyle = StyleSheet.getHeaderLogoStyle();
        logo.add(new Image(imgdata).addStyle(logoStyle));
        Cell heading = new Cell();
        Style headerStyle = StyleSheet.getHeaderStyle();
        heading.add(new Paragraph("Banking System").addStyle(headerStyle));
        header.addCell(logo);
        header.addCell(heading);
        document.add(header);
    }
    public void addSubHeader(String s){
        Paragraph subH = new Paragraph(s);
        subH.addStyle(StyleSheet.getSubHeaderStyle());
        document.add(subH);
    }
    public void addRecordTable(String[] labels,List<Object[]> records){
        Table table = new Table(labels.length); 
        table.addStyle(StyleSheet.getRecordTableStyle());
        for(String label:labels){
            Cell cell = new Cell();
            cell.add(new Paragraph(label));
            cell.addStyle(StyleSheet.getRecordLableStyle());
            table.addCell(cell);
        }
        for(Object[] record:records){
            for(Object o:record){
                Cell cell = new Cell().setTextAlignment(TextAlignment.CENTER);
                if(o instanceof String){
                    cell.add(new Paragraph((String)o));
                }else if(o instanceof Double){
                    cell.add(new Paragraph(Double.toString((Double)o)));
                }else if(o instanceof Integer){
                    cell.add(new Paragraph(Integer.toString((Integer)o)));
                }
                table.addCell(cell);
            }
        }
        document.add(table);
    }
    public void addDesc(String recordDesc){
        Paragraph recDesc = new Paragraph(recordDesc);
        recDesc.addStyle(StyleSheet.getDescStyle());
        document.add(recDesc);
    }
    public void addDesc(String recordDesc,String filterDesc){
        Table desc = new Table(2);
        desc.setWidth(new UnitValue(UnitValue.PERCENT,100));
        desc.addStyle(StyleSheet.getDescTableStyle());
        Cell cell1 = new Cell();
        cell1.add(new Paragraph(recordDesc));
        desc.addCell(cell1);
        Cell cell2 = new Cell();
        cell2.add(new Paragraph(filterDesc));
        desc.addCell(cell2);
        document.add(desc);
    }
    public void close(){
        document.close();
    }
}
