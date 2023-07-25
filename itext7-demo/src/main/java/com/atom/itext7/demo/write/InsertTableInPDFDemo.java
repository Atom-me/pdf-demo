package com.atom.itext7.demo.write;


import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.StringTokenizer;

/**
 * 创建一个新的PDF文件，并往里面插入表哥
 *
 * @author Atom
 */
public class InsertTableInPDFDemo {
    public static void main(String[] args) throws IOException, URISyntaxException {
        PdfWriter writer = new PdfWriter("/Users/atom/work/workspace/pdf-demo/itext7-demo/sampleTablePDF.pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());//设置页面大小，rotate（）表示页面横向
        document.setMargins(20, 20, 20, 20);//设置页边距
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);//定义不同的字体类型
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);//定义不同的字体类型
        Table table = new Table(new float[]{4, 1, 3, 4, 3, 3, 3, 3, 1});//每个数定义一个列的相对宽度。
        table.setWidth(UnitValue.createPercentValue(100));//表的宽度相对于页面的可用宽度，在这种情况下，表将使用100% 的页面宽度，减去页边距。
        //接下来的操作是通过读取一个csv文件里的内容填充到pdf的表格上
        BufferedReader br = new BufferedReader(new FileReader("/Users/atom/work/workspace/pdf-demo/itext7-demo/src/main/resources/file/data.csv"));
        String line = br.readLine();
        //process ()方法，使用特定的字体将行添加到表中，并定义行是否包含标题行的内容。
        process(table, line, bold, true);
        while ((line = br.readLine()) != null) {
            process(table, line, font, false);
        }
        br.close();
        document.add(table);
        document.close();

    }

    public static void process(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(
                        new Cell().add(
                                new Paragraph(tokenizer.nextToken()).setFont(font)));
            } else {
                table.addCell(
                        new Cell().add(
                                new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }


}
