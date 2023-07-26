package com.atom.itext7.convert;

import com.itextpdf.text.pdf.BaseFont;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 使用 flying-saucer-pdf 转换 HTML 至PDF
 *
 * @author Atom
 */
public class Html2PdfUsingFlyingSaucerExample {
    private static final String HTML_INPUT = "./itext7-demo/src/main/resources/html.html";
    private static final String PDF_OUTPUT = "./itext7-demo/html.pdf";

    private static final Logger LOGGER = LoggerFactory.getLogger(Html2PdfUsingFlyingSaucerExample.class);

    public static void main(String[] args) {
        try {
            Html2PdfUsingFlyingSaucerExample htmlToPdf = new Html2PdfUsingFlyingSaucerExample();
            htmlToPdf.generateHtmlToPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateHtmlToPdf() throws Exception {
        File inputHTML = new File(HTML_INPUT);
        Document inputHtml = createWellFormedHtml(inputHTML);
        File outputPdf = new File(PDF_OUTPUT);
        xhtmlToPdf(inputHtml, outputPdf);
    }

    private Document createWellFormedHtml(File inputHTML) throws IOException {
        Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.outputSettings()
                .syntax(Document.OutputSettings.Syntax.xml);
        return document;
    }

    private void xhtmlToPdf(Document xhtml, File outputPdf) throws Exception {
        try (OutputStream outputStream = new FileOutputStream(outputPdf)) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            sharedContext.setReplacedElementFactory(new CustomElementFactoryImpl());
            renderer.setDocumentFromString(xhtml.html());

            renderer.getSharedContext().getTextRenderer().setSmoothingThreshold(0);


            LOGGER.info("html content is [{}]", xhtml.html());

            //todo 中文不显示问题处理


            renderer.layout();
            renderer.createPDF(outputStream);
        }
    }
}
