package com.atom.itext5.convert;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用html2pdf转换
 * html2pdf 是 itextpdf 的插件
 * html2pdf 是 新版本的转换工具
 *
 * @author Atom
 */
public class HTML2PDFExample2 {

    public static void main(String[] args) throws IOException {
        // IO
        File htmlSource = new File("./itext5-demo/src/main/resources/html.html");
        File pdfDest = new File("./itext5-demo/html.pdf");
        // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(new FileInputStream(htmlSource), new FileOutputStream(pdfDest), converterProperties);
    }

}
