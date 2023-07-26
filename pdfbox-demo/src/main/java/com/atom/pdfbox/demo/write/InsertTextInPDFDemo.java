package com.atom.pdfbox.demo.write;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

/**
 * 创建一个新的PDF文件，并往里面写入文本
 *
 * @author Atom
 */
public class InsertTextInPDFDemo {
    public static void main(String[] args) throws IOException {

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.COURIER, 18);

        // 添加第一行文本
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("hello line1");
        contentStream.endText();

        // 添加第二行文本
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 680);
        contentStream.showText("hello line2");
        contentStream.endText();

        // 添加第三行文本
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 660);
        contentStream.showText("hello line3");
        contentStream.endText();

        contentStream.close();
        document.save("./pdfbox-demo/pdfBoxHelloWorld.pdf");
        document.close();

    }
}
