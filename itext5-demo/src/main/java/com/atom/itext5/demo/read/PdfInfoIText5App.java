package com.atom.itext5.demo.read;

import java.io.IOException;

/**
 * @author Atom
 */
public class PdfInfoIText5App {

    private static final String SOURCE_PDF = "./itext5-demo/src/main/resources/MultiPages-encryped.pdf";
    public static void main(String[] args) throws IOException {
        System.err.println(PdfInfoIText5.isPasswordProtected(SOURCE_PDF));
        System.err.println(PdfInfoIText5.getNumberOfPages(SOURCE_PDF));
        System.err.println(PdfInfoIText5.getInfo(SOURCE_PDF));
    }
}
