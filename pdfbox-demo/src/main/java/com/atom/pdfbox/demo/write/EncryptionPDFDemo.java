package com.atom.pdfbox.demo.write;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

/**
 * PDF文件加密，设置（打印）权限
 * 两个密码：
 * 1. 普通用户密码
 * 2. 拥有者密码，使用拥有者密码打开已加密的PDF文件拥有所有权限，包含打印权限。
 *
 * @author Atom
 */
public class EncryptionPDFDemo {
    public static void main(String[] args) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.COURIER, 18);
        // 添加文本
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 660);
        contentStream.showText("hello line3");
        contentStream.endText();

        contentStream.close();

        AccessPermission accessPermission = new AccessPermission();
        accessPermission.setCanPrint(false);
        accessPermission.setCanModify(false);

        StandardProtectionPolicy standardProtectionPolicy
                = new StandardProtectionPolicy("ownerpass", "userpass", accessPermission);
        document.protect(standardProtectionPolicy);
        document.save("./pdfbox-demo/pdfBoxEncryption.pdf");
        document.close();

    }
}
