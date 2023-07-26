package com.atom.pdfbox.demo.read;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * 读取PDF文件中的文本内容
 *
 * @author Atom
 */
public class ExtractTextFromPDFDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtractTextFromPDFDemo.class);

    public static void main(String[] args) throws IOException {
        File file = new File("./pdfbox-demo/src/main/resources/sample.pdf");
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();

        LOGGER.info("read text from pdf : [{}]", text);

    }
}
