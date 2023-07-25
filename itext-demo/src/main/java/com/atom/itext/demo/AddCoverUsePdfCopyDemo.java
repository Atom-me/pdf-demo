package com.atom.itext.demo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 给指定PDF文件添加首页
 *
 * @author Atom
 */
public class AddCoverUsePdfCopyDemo {

    /**
     * With PdfCopy, you may lose some of the properties that are defined at the document level (e.g. the page layout setting),
     * but you keep the interactive features of both files.
     * You may need to set some parameters in case you want to preserve tagging, form fields, etc...
     * but for simple PDFs, you don't need all of that.
     *
     * @param args
     * @throws IOException
     * @throws DocumentException
     */
    public static void main(String[] args) throws IOException, DocumentException {
        PdfReader cover = new PdfReader("hero.pdf");
        PdfReader reader = new PdfReader("pages.pdf");
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, new FileOutputStream("pages_with_cover.pdf"));
        document.open();
        copy.addDocument(cover);
        copy.addDocument(reader);
        document.close();
        cover.close();
        reader.close();
    }
}


