package com.atom.itext7.convert;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

/**
 * 使用 openhtmltopdf-pdfbox 转换HTML 至 PDF
 * openhtmltopdf-pdfbox 内置了 pdfbox
 */
public class Html2PdfUsingOpenHtmlExample {

    private static final String HTML_INPUT = "./itext7-demo/src/main/resources/html.html";
    private static final String PDF_OUTPUT = "./itext7-demo/output/html.pdf";


    public static void main(String[] args) {
        try {
            Html2PdfUsingOpenHtmlExample htmlToPdf = new Html2PdfUsingOpenHtmlExample();
            htmlToPdf.generateHtmlToPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateHtmlToPdf() throws IOException {
        File inputHTML = new File(HTML_INPUT);
        Document doc = createWellFormedHtml(inputHTML);
        xhtmlToPdf(doc, PDF_OUTPUT);
    }

    private Document createWellFormedHtml(File inputHTML) throws IOException {
        Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document;
    }

    private void xhtmlToPdf(Document doc, String outputPdf) throws IOException {
        try (OutputStream os = new FileOutputStream(outputPdf)) {
            String baseUri = FileSystems.getDefault().getPath("src/main/resources/").toUri().toString();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri(outputPdf);
            builder.toStream(os);
            builder.withW3cDocument(new W3CDom().fromJsoup(doc), baseUri);
            builder.run();
        }
    }
}
