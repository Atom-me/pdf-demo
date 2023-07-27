package com.atom.itext5.demo.read;

import com.itextpdf.text.exceptions.BadPasswordException;
import com.itextpdf.text.pdf.PdfReader;

import java.io.IOException;
import java.util.Map;

/**
 * @author Atom
 */
public class PdfInfoIText5 {

    private static final String PASS = "ownerpass";


    public static int getNumberOfPages(final String pdfFile) throws IOException {
        PdfReader reader = new PdfReader(pdfFile, PASS.getBytes());
        int pages = reader.getNumberOfPages();
        reader.close();
        return pages;
    }

    public static boolean isPasswordProtected(String filePath) {
        try {
            PdfReader reader = new PdfReader(filePath, PASS.getBytes());
            return reader.isEncrypted();
        } catch (BadPasswordException e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> getInfo(final String pdfFile) throws IOException {
        PdfReader reader = new PdfReader(pdfFile, PASS.getBytes());
        Map<String, String> info = reader.getInfo();
        reader.close();
        return info;
    }
}
