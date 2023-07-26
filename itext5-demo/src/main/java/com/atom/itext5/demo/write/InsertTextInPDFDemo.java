package com.atom.itext5.demo.write;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 创建一个新的PDF文件，并往里面写入文本
 *
 * @author Atom
 */
public class InsertTextInPDFDemo {
    public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("./itext5-demo/iTextHelloWorld.pdf"));
        document.open();

        //英文字体
        Font font1 = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello World~~", font1);
        document.add(chunk);


        // 定义中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font2 = new Font(bfChinese, 16, Font.NORMAL);
        // 中文文本
        String chineseText = "hello 你好，世界！";
        // 将中文文本转换为 PDF 文本
        Phrase phrase = new Phrase(chineseText, font2);
        // 添加 PDF 文本到文档中
        document.add(phrase);

        document.close();
    }
}
