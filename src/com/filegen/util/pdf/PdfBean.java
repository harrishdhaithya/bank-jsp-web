package com.filegen.util.pdf;

import java.io.File;
import java.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class PdfBean {
    protected File file = null;
    protected PdfWriter writer = null;
    protected PdfDocument pdfdoc = null;
    protected Document document = null;
    public PdfBean(String path) throws IOException{
        file = new File(path);
        writer = new PdfWriter(file);
        pdfdoc = new PdfDocument(writer);
        document = new Document(pdfdoc);
    }
    public File getFile(){
        return this.file;
    }
}
