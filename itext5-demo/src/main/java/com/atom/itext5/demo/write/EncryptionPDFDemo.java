package com.atom.itext5.demo.write;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * PDF文件加密，设置（打印）权限
 * 两个密码：
 * 1. 普通用户密码
 * 2. 拥有者密码，使用拥有者密码打开已加密的PDF文件拥有所有权限，包含打印权限。
 *
 * @author Atom
 */
public class EncryptionPDFDemo {
    public static void main(String[] args) throws IOException, DocumentException {
        PdfReader pdfReader = new PdfReader("./itext5-demo/src/main/resources/iTextHelloWorld4Encryption.pdf");
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("./itext5-demo/output/encryptedITextHelloWorld.pdf"));

        /**
         * we encrypted the file with two passwords:
         * the user password (“userpass”),where a user has read-only rights with no possibility to print it,
         * and the owner password (“ownerpass”), which is used as a master key to allow a person full access to the pdf.
         */
        pdfStamper.setEncryption(
                "userpass".getBytes(),
                "ownerpass".getBytes(),
                0,
                PdfWriter.ENCRYPTION_AES_256
        );

        //If we want to allow the user to print the pdf, then instead of 0 (third parameter of setEncryption), we can pass:
//        PdfWriter.ALLOW_PRINTING
//        PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_COPY
        /**
         * Keep in mind that when using iText to set access permissions,
         * we're also creating a temporary pdf, which should be deleted.
         * If we don't delete it, it could be fully accessible to anyone.
         */


        pdfStamper.close();
    }
}
