package com.atom.itext7.demo.write;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;

/**
 * @author Atom
 */
public class EditExistingPDFDemo {
    public static void main(String[] args) throws IOException {
        PdfReader reader = new PdfReader("./itext7-demo/src/main/resources/existingPDF4Edit.pdf");
        PdfWriter writer = new PdfWriter("./itext7-demo/output/existingPDF4Edit-modified.pdf");
        PdfDocument pdfDocument = new PdfDocument(reader, writer);
        addContentToDocument(pdfDocument);
    }

    private static void addContentToDocument(PdfDocument pdfDocument) throws IOException {
//        Adding a Form
        //使用系统本地字体，可以解决生成的pdf中无法显示中文问题，本处字体为阿里巴巴普惠体-R
        PdfFont sysFont = PdfFontFactory.createFont("font/Alibaba-PuHuiTi-Regular.otf", PdfEncodings.IDENTITY_H);

        PdfFormField personal = PdfFormField.createEmptyField(pdfDocument);
        personal.setFieldName("information");
        PdfTextFormField name = PdfFormField.createText(pdfDocument, new Rectangle(35, 400, 100, 30), "name", "");
        personal.addKid(name);
        PdfAcroForm.getAcroForm(pdfDocument, true)
                .addField(personal, pdfDocument.getFirstPage());

//        Adding a New Page
        pdfDocument.addNewPage(1);

//        Adding an Annotation
        PdfAnnotation ann = new PdfTextAnnotation(new Rectangle(40, 435, 0, 0))
                .setTitle(new PdfString("name"))
                .setContents("Your name 气象局");
        pdfDocument.getPage(2)
                .addAnnotation(ann);

//        Adding an Image
        Document document = new Document(pdfDocument);
        ImageData imageData = ImageDataFactory.create("./itext7-demo/src/main/resources/image/yuanshitianzun.png");
        Image image = new Image(imageData).scaleAbsolute(550, 100)
                .setFixedPosition(1, 10, 50);
        document.add(image);

//        Adding a Paragraph
        Text title = new Text("This is a demo示例").setFontSize(16);
        Text author = new Text("atom tutorials.");
        Paragraph p = new Paragraph()
                .setFont(sysFont)
                .setFontSize(12)
                .add(title)
                .add(" from ")
                .add(author);
        document.add(p);

//        Adding a Table
        Table table = new Table(UnitValue.createPercentArray(2));
        table.addHeaderCell("#");
        table.addHeaderCell("company");
        table.addCell("name");
        table.addCell("atom");
        document.add(table);

        document.close();
        pdfDocument.close();


    }
}
