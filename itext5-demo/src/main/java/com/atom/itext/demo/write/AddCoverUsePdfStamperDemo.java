package com.atom.itext.demo.write;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 给指定PDF文件添加首页
 *
 * @author Atom
 */
public class AddCoverUsePdfStamperDemo {

    /**
     * With PdfStamper, you preserve the properties that are defined at the document level of pages.
     * pdf, but you lose all the interactive features of the cover page (if any).
     * You may need to tweak the example if you want to define the cover as an artifact and if the original cover page has odd page boundaries,
     * but it would lead us too far to discuss this in this simple answer.
     *
     * @param args
     * @throws IOException
     * @throws DocumentException
     */
    public static void main(String[] args) throws IOException, DocumentException {
        PdfReader cover = new PdfReader("hero.pdf");
        PdfReader reader = new PdfReader("pages.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("cover_with_pages.pdf"));

        stamper.insertPage(1, cover.getPageSizeWithRotation(1));
        PdfContentByte page1 = stamper.getOverContent(1);
        PdfImportedPage page = stamper.getImportedPage(cover, 1);
        page1.addTemplate(page, 0, 0);
        stamper.close();
        cover.close();
        reader.close();
    }
}


