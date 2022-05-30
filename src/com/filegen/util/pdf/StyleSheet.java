package com.filegen.util.pdf;

import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

class StyleSheet {
    static Style getHeaderLogoStyle(){
        Style style = new Style()
                        .setHeight(100f)
                        .setWidth(new UnitValue(UnitValue.PERCENT,100))
                        .setHeight(70);
        return style;
    }
    static Style getHeaderStyle(){
        Style style = new Style()
                        .setFontSize(40f)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE);
        return style;
    }
    static Style getSubHeaderStyle(){
        Style style = new Style()
        .setPadding(5f)
        .setFontSize(28f)
        .setWidth(new UnitValue(UnitValue.PERCENT,100))
        .setBorder(new SolidBorder(DeviceGray.GRAY,1))
        .setBorderTop(new SolidBorder(0))
        .setBorderBottom(new SolidBorder(0))
        .setTextAlignment(TextAlignment.CENTER)
        .setMargin(0);
        return style;
    }
    static Style getRecordTableStyle(){
        Style style = new Style()
        .setBorder(new SolidBorder(DeviceGray.GRAY, 1))
        .setWidth(new UnitValue(UnitValue.PERCENT,100));
        return style;
    }
    static Style getRecordLableStyle(){
        Style style = new Style()
        .setBackgroundColor(DeviceRgb.GREEN)
        .setTextAlignment(TextAlignment.CENTER);
        return style;
    }
    static Style getDescStyle(){
        Style style = new Style()
        .setPadding(5f)
        .setFontSize(14f)
        .setWidth(new UnitValue(UnitValue.PERCENT,100))
        .setBorder(new SolidBorder(DeviceGray.GRAY, 1))
        .setBorderBottom(new SolidBorder(0))
        .setMargin(0);
        return style;
    }
    static Style getDescTableStyle(){
        Style style = new Style()
                        .setFontSize(14f)
                        .setWidth(new UnitValue(UnitValue.PERCENT,100));
        return style;
    }
}
