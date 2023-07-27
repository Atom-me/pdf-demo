package com.atom.itext7.convert;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.FontSet;

import java.io.*;

/**
 * @author Atom
 */
public class Html2PdfExample {
    public static void main(String[] args) throws IOException {
        // IO
        File htmlSource = new File("./itext7-demo/src/main/resources/html.html");
        File pdfDest = new File("./itext7-demo/output/html.pdf");
        // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        //字体设置，解决中文不显示问题
        FontSet fontSet = new FontSet();
        // 加载自定义字体
        fontSet.addFont(Html2PdfExample.class.getClassLoader().getResource("font/Alibaba-PuHuiTi-Regular.otf").getPath(),
                PdfEncodings.IDENTITY_H);
        FontProvider fontProvider = new FontProvider(fontSet);
        converterProperties.setFontProvider(fontProvider);
        HtmlConverter.convertToPdf(new FileInputStream(htmlSource), new FileOutputStream(pdfDest), converterProperties);
    }
}
