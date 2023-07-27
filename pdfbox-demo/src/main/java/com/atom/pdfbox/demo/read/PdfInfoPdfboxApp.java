package com.atom.pdfbox.demo.read;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Atom
 */
public class PdfInfoPdfboxApp {

    private static final String SOURCE_PDF = "./pdfbox-demo/src/main/resources/MultiPages-encryped.pdf";

    public static void main(String[] args) throws IOException {
        PdfInfoPdfbox.getNumberOfPages(SOURCE_PDF);
        System.err.println(PdfInfoPdfbox.isPasswordProtected(SOURCE_PDF));
        System.err.println(PdfInfoPdfbox.getInfo(SOURCE_PDF));
    }
}
