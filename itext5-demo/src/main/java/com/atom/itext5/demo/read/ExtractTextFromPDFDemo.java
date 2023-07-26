package com.atom.itext5.demo.read;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 读取PDF文件中的文本内容
 *
 * @author Atom
 */
public class ExtractTextFromPDFDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractTextFromPDFDemo.class);

    public static void main(String[] args) throws IOException {
        PdfReader reader = new PdfReader("./itext5-demo/src/main/resources/sample.pdf");
        int pages = reader.getNumberOfPages();
        StringBuilder text = new StringBuilder();
        for (int i = 1; i <= pages; i++) {
            text.append(PdfTextExtractor.getTextFromPage(reader, i));
        }
        reader.close();

        LOGGER.info("read text from pdf: [{}]", text);

    }
}
