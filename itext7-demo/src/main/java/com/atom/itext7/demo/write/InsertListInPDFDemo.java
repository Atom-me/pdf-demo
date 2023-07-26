package com.atom.itext7.demo.write;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;

/**
 * 创建一个新的PDF文件，并往里面写入列表
 *
 * @author Atom
 */
public class InsertListInPDFDemo {
    public static void main(String[] args) throws IOException {

        //使用系统本地字体，可以解决生成的pdf中无法显示中文问题，本处字体为阿里巴巴普惠体-R
        //在创建字体时直接使用即可解决中文问题
        PdfFont sysFont = PdfFontFactory.createFont("font/Alibaba-PuHuiTi-Regular.otf", PdfEncodings.IDENTITY_H);

        //带路径的文件名
        PdfWriter writer = new PdfWriter("./itext7-demo/itext7SampleList.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("iText7 list:").setFont(sysFont));
        // 创建list，位于itextpdf.layout包下
        List list = new List()
                .setSymbolIndent(12)//列表项的缩进单元
                .setListSymbol("\u2022")//列表符号，没有找到具体说明，有需求只能一个一个试了
                .setFont(sysFont);
        // 添加数据
        list.add(new ListItem("English test1"))
                .add(new ListItem("English test2"))
                .add(new ListItem("中文测试1"))
                .add(new ListItem("中文测试2"));
        document.add(list);
        document.close();


    }
}
