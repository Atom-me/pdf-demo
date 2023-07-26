package com.atom.itext7.convert;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import java.io.*;

/**
 * @author Atom
 */
public class HTML2PDFExample {
    public static void main(String[] args) throws IOException {
        // IO
        File htmlSource = new File("./itext7-demo/src/main/resources/html.html");
        File pdfDest = new File("./itext7-demo/html.pdf");
        // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(new FileInputStream(htmlSource), new FileOutputStream(pdfDest), converterProperties);
    }
}
