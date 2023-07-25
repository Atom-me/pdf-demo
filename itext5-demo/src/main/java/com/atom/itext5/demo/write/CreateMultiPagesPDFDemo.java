package com.atom.itext5.demo.write;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Atom
 */
public class CreateMultiPagesPDFDemo {
    public static void main(String[] args) throws IOException, DocumentException {

        //创建文档
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);

        String sourcePdfFilePath = "/Users/atom/work/workspace/pdf-demo/MultiPages.pdf";
        OutputStream fos = new FileOutputStream(sourcePdfFilePath);
        //创建PdfWriter
        PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
        //加密
        pdfWriter.setEncryption( "userpass".getBytes(),
                "ownerpass".getBytes(),
                0,
                PdfWriter.ENCRYPTION_AES_256);

        //打开文档
        document.open();

        //写入内容
        document.add(new Paragraph("This is Page 1"));

        //开启一个新页
        document.newPage();
        //写入新页内容
        document.add(new Paragraph("This is Page 2"));


        //开启一个新页
        document.newPage();
        //写入新页内容
        document.add(new Paragraph("This is Page 3"));

        document.close();
        fos.close();
    }
}
