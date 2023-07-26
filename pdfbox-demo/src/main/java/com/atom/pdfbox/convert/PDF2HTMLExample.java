package com.atom.pdfbox.convert;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 *
 * PDFDomTree 转换效果很差
 * @author Atom
 */
public class PDF2HTMLExample {
    private static final String SOURCE_PDF = "./pdfbox-demo/src/main/resources/pdf.pdf";

    public static void main(String[] args) {
        try {
            generateHTMLFromPDF(SOURCE_PDF);
        } catch (IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static void generateHTMLFromPDF(String fileName) throws ParserConfigurationException, IOException {
        PDDocument pdf = PDDocument.load(new File(fileName));
        PDFDomTree parser = new PDFDomTree();
        Writer output = new PrintWriter("./pdfbox-demo/pdf.html", StandardCharsets.UTF_8);
        parser.writeText(pdf, output);
        output.close();
        if (pdf != null) {
            pdf.close();
        }
    }
}
