package com.atom.itext7.demo.read;

import java.io.IOException;

/**
 * @author Atom
 */
public class PdfInfoIText7App {

    private static final String SOURCE_PDF = "./itext7-demo/src/main/resources/MultiPages-encryped.pdf";

    public static void main(String[] args) throws IOException {
//        System.err.println(PdfInfoIText7.getNumberOfPages(SOURCE_PDF));
        System.err.println(PdfInfoIText7.isPasswordRequired(SOURCE_PDF));
//        System.err.println(PdfInfoIText7.getInfo(SOURCE_PDF));
    }
}
