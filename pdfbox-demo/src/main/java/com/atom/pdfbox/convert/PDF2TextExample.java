package com.atom.pdfbox.convert;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Atom
 */
public class PDF2TextExample {
    private static final String PDF = "./pdfbox-demo/src/main/resources/invoice.pdf";

    public static void main(String[] args) {
        try {
            generateTxtFromPDF(PDF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateTxtFromPDF(String fileName) throws IOException {
        File file = new File(fileName);
        try (PDDocument pdDoc = PDDocument.load(file)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String parsedText = pdfStripper.getText(pdDoc);

            //write out
            try (PrintWriter pw = new PrintWriter("./pdfbox-demo/output/pdf.txt")) {
                pw.print(parsedText);
            }
        }
    }


}
