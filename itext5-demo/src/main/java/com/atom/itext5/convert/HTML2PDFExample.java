package com.atom.itext5.convert;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Atom
 */
public class HTML2PDFExample {

    private static final String SOURCE_HTML = "./itext5-demo/src/main/resources/html.html";

    public static void main(String[] args) {
        try {
            generatePDFFromHTML(SOURCE_HTML);
        } catch (IOException | ParserConfigurationException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private static void generatePDFFromHTML(String filename) throws ParserConfigurationException, IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("./itext5-demo/html.pdf"));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(filename));
        document.close();
    }
}
