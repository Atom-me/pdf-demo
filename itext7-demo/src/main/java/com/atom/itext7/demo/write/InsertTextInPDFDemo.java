package com.atom.itext7.demo.write;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

/**
 * 创建一个新的PDF文件，并往里面写入文本
 *
 * @author Atom
 */
public class InsertTextInPDFDemo {
    public static void main(String[] args) throws IOException {

        //使用系统本地字体，可以解决生成的pdf中无法显示中文问题，本处字体为阿里巴巴普惠体-R
        //在创建字体时直接使用即可解决中文问题
        PdfFont sysFont = PdfFontFactory.createFont("font/Alibaba-PuHuiTi-Regular.otf", PdfEncodings.IDENTITY_H);

        //带路径的文件名
        PdfWriter writer = new PdfWriter("./itext7-demo/output/itext7SampleText.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("Hello World! 中文测试").setFont(sysFont));
        document.close();

    }
}
