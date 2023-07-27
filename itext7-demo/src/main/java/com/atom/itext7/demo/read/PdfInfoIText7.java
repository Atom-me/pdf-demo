package com.atom.itext7.demo.read;


import com.itextpdf.kernel.exceptions.BadPasswordException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfDocumentInfo;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.ReaderProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Atom
 */
public class PdfInfoIText7 {


    private static final String PASS = "ownerpass";

    public static int getNumberOfPages(final String pdfFile) throws IOException {
        PdfReader reader = new PdfReader(pdfFile, new ReaderProperties().setPassword(PASS.getBytes()));
        PdfDocument document = new PdfDocument(reader);
        int numberOfPages = document.getNumberOfPages();
        document.close();
        reader.close();
        return numberOfPages;
    }

    public static boolean isPasswordRequired(final String pdfFile) throws IOException {
        try (PdfReader reader = new PdfReader(pdfFile, new ReaderProperties().setPassword(PASS.getBytes()))) {
            // 这句别删
            PdfDocument pdfDocument = new PdfDocument(reader);
            // If the reader is encrypted, then the file requires a password
            return reader.isEncrypted();
        } catch (BadPasswordException e) {
            // If the password is incorrect, then the file requires a password
            return true;
        }
    }

    public static Map<String, String> getInfo(final String pdfFile) throws IOException {
        PdfReader reader = new PdfReader(pdfFile, new ReaderProperties().setPassword(PASS.getBytes()));
        PdfDocument document = new PdfDocument(reader);
        PdfDocumentInfo info = document.getDocumentInfo();
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Title", info.getTitle());
        metadata.put("Author", info.getAuthor());
        metadata.put("Subject", info.getSubject());
        metadata.put("Keywords", info.getKeywords());
        metadata.put("Creator", info.getCreator());
        metadata.put("Producer", info.getProducer());
        document.close();
        reader.close();
        return metadata;
    }
}
