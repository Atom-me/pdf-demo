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
public class PdfInfoPdfbox {
    private static final Logger LOGGER = LoggerFactory.getLogger(PdfInfoPdfbox.class);

    private static final String PASS = "ownerpass";

    public static int getNumberOfPages(final String pdfFile) throws IOException {
        LOGGER.info("file :" + pdfFile);
        PDDocument doc = PDDocument.load(new File(pdfFile),PASS);
        int pageCount = doc.getNumberOfPages();
        LOGGER.info("pageCount :" + pageCount);
        doc.close();
        return pageCount;
    }

    public static boolean isPasswordProtected(String filePath) throws IOException {
        PDDocument doc = PDDocument.load(new File(filePath),PASS);
        boolean encrypted = doc.isEncrypted();
        return encrypted;
    }

    public static Map<String, String> getInfo(final String pdfFile) throws IOException {
        return null;
    }
}
