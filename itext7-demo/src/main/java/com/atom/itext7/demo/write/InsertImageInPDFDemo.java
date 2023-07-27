package com.atom.itext7.demo.write;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

/**
 * PDF文件插入图片
 *
 * @author Atom
 */
public class InsertImageInPDFDemo {
    public static void main(String[] args) throws IOException {

        //使用系统本地字体，可以解决生成的pdf中无法显示中文问题，本处字体为阿里巴巴普惠体-R
        //在创建字体时直接使用即可解决中文问题
        PdfFont sysFont = PdfFontFactory.createFont("font/Alibaba-PuHuiTi-Regular.otf", PdfEncodings.IDENTITY_H);

        //创建基础模块
        PdfWriter writer = new PdfWriter("./itext7-demo/output/image.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        //document就是我们的pdf对象
        Document document = new Document(pdf);
        //生成图片对象
        Image image = new Image(ImageDataFactory.create("./itext7-demo/src/main/resources/image/yuanshitianzun.png"), 200, 400, 200);
        Image image1 = new Image(ImageDataFactory.create("./itext7-demo/src/main/resources/image/yuanshitianzun.png")).setHeight(50);
        Paragraph paragraph = new Paragraph("图片输出测试元始天尊： ").setFont(sysFont)
                .add(image)
                .add(image1);
        document.add(paragraph);
        document.close();

    }
}
