package com.atom.itext.demo.read;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;

/**
 * 读取PDF文件中的文本内容
 *
 * @author Atom
 */
public class ExtractTextFromPFDDemo {
    public static void main(String[] args) throws IOException {
        PdfReader reader = new PdfReader("sample.pdf");
        int pages = reader.getNumberOfPages();
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= pages; i++) {
            text.append(PdfTextExtractor.getTextFromPage(reader, i));
        }
        reader.close();

        System.err.println(text);

    }
}
