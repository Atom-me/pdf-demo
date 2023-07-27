package com.atom.itext7.demo.write;


import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.pdfcleanup.CleanUpProperties;
import com.itextpdf.pdfcleanup.PdfCleanUpLocation;
import com.itextpdf.pdfcleanup.PdfCleanUpTool;
import com.itextpdf.pdfcleanup.PdfCleaner;
import com.itextpdf.pdfcleanup.autosweep.CompositeCleanupStrategy;
import com.itextpdf.pdfcleanup.autosweep.RegexBasedCleanupStrategy;

import java.io.IOException;
import java.util.List;

/**
 * 覆盖指定文本，或者区域
 */
public class PdfContentRemoverDemo {

    private static final String SOURCE = "./itext7-demo/src/main/resources/pdf4replacement.pdf";
    private static final String DESTINATION = "./itext7-demo/pdf4RemoveContent-result.pdf";

    public static void main(String[] args) throws IOException {
        PdfReader reader = new PdfReader(SOURCE);
        PdfWriter writer = new PdfWriter(DESTINATION);
        PdfDocument pdfDocument = new PdfDocument(reader, writer);
        removeContentFromDocument(pdfDocument);
        pdfDocument.close();
    }

    private static void removeContentFromDocument(PdfDocument pdfDocument) throws IOException {
        // 5.1. remove text
        CompositeCleanupStrategy strategy = new CompositeCleanupStrategy();
        // 默认颜色为黑色
        strategy.add(new RegexBasedCleanupStrategy("atom"));
//        strategy.add(new RegexBasedCleanupStrategy("atom").setRedactionColor(ColorConstants.RED));

        PdfCleaner.autoSweepCleanUp(pdfDocument, strategy);

        // 5.2. remove other areas
        List<PdfCleanUpLocation> cleanUpLocations = List.of(
                new PdfCleanUpLocation(1, new Rectangle(10, 50, 90, 70)),
                new PdfCleanUpLocation(2, new Rectangle(10, 732, 70, 60))
        );
        PdfCleanUpTool cleaner = new PdfCleanUpTool(pdfDocument, cleanUpLocations, new CleanUpProperties());
        cleaner.cleanUp();
    }

}