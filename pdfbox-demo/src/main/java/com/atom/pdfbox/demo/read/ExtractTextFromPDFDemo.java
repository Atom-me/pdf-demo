package com.atom.pdfbox.demo.read;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * 读取PDF文件中的文本内容
 *
 * @author Atom
 */
public class ExtractTextFromPDFDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("sample.pdf");
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();

        System.err.println(text);

    }
}
