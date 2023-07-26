package com.atom.pdfbox.demo.write;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 创建一个新的PDF文件，并往里面插入图片
 *
 * @author Atom
 */
public class InsertImageInPDFDemo {
    public static void main(String[] args) throws IOException, URISyntaxException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        Path path = Paths.get(ClassLoader.getSystemResource("demo001.png").toURI());
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PDImageXObject image = PDImageXObject.createFromFile(path.toAbsolutePath().toString(), document);
        contentStream.drawImage(image, 80, 80);
        contentStream.close();

        document.save("./pdfbox-demo/pdfBoxImage.pdf");
        document.close();

    }
}
