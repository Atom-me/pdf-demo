package com.atom.pdfbox.convert;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * PDF 转图片
 * 不同格式的图片，文件大小不一样，gif文件较小
 *
 * @author Atom
 */
public class PDF2ImageExample {

    private static final String PDF_FILE = "./pdfbox-demo/src/main/resources/pdf.pdf";
    /**
     * 在 PDF 转换为图片时，DPI 是指每英寸渲染的像素数量，通常用于控制生成的图片分辨率和质量。
     * 较高的 DPI 可以生成更清晰、更精细的图片，但会增加文件大小和处理时间。
     * 通常，300 DPI 是一个比较常用的值，可以在保证图片质量的同时控制生成的图片大小。
     */
    private static final int DPI = 300;
    private static final String OUTPUT_DIR = "./pdfbox-demo/output/";

    public static void main(String[] args) {
        try {
//            generateImageFromPDF(PDF_FILE, "png");
//            generateImageFromPDF(PDF_FILE, "jpeg");
            generateImageFromPDF(PDF_FILE, "gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateImageFromPDF(String fileName, String extension) throws IOException {
        PDDocument document = PDDocument.load(new File(fileName));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, DPI, ImageType.RGB);
            String outputFileName = String.format("%s/pdf-%d.%s", OUTPUT_DIR, page + 1, extension);
            ImageIOUtil.writeImage(bim, outputFileName, DPI);
        }
        document.close();
    }

}
