package com.atom.itext7.demo.write;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.pdfcleanup.PdfCleaner;
import com.itextpdf.pdfcleanup.autosweep.CompositeCleanupStrategy;
import com.itextpdf.pdfcleanup.autosweep.RegexBasedCleanupStrategy;

import java.io.IOException;

/**
 * 文字清理覆盖，添加遮罩
 */
public class PdfTextReplacementDemo {

    private static final String SOURCE = "./itext7-demo/src/main/resources/pdf4replacement.pdf";
    private static final String DESTINATION = "./itext7-demo/output/pdf4replacement-result.pdf";

    public static void main(String[] args) throws IOException {
        PdfReader reader = new PdfReader(SOURCE);
        PdfWriter writer = new PdfWriter(DESTINATION);
        PdfDocument pdfDocument = new PdfDocument(reader, writer);
        replaceTextContentFromDocument(pdfDocument);
        pdfDocument.close();
    }

    private static void replaceTextContentFromDocument(PdfDocument pdfDocument) throws IOException {
        CompositeCleanupStrategy strategy = new CompositeCleanupStrategy();
        strategy.add(new RegexBasedCleanupStrategy("atom").setRedactionColor(ColorConstants.RED));
        strategy.add(new RegexBasedCleanupStrategy("中").setRedactionColor(ColorConstants.BLACK));
        PdfCleaner.autoSweepCleanUp(pdfDocument, strategy);

        for (IPdfTextLocation location : strategy.getResultantLocations()) {
            PdfPage page = pdfDocument.getPage(location.getPageNumber() + 1);
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), page.getDocument());
            Canvas canvas = new Canvas(pdfCanvas, location.getRectangle());
            canvas.add(new Paragraph("HIDDEN")
                    .setFontSize(8)
                    .setMarginTop(0f));
        }
    }

}
