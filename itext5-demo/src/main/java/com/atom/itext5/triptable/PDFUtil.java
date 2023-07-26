package com.atom.itext5.triptable;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/**
 * @author Atom
 */
public class PDFUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PDFUtil.class);
    public static Font headFont;
    public static Font keyFont;
    // 标题
    public static Font titleFont;
    // 二级标题
    public static Font secondLevelTitleFont;

    // 三级标题
    public static Font threeLevelTitleFont;

    // 设置字体大小
    public static Font textFont;
    public static final int MAX_WIDTH = 520;

    static {
        // 设置字体路径
        String fontPath = "font/Alibaba-PuHuiTi-Regular.otf";
        try {
            // 创建字体对象
            BaseFont customFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            headFont = new Font(customFont, 10, java.awt.Font.BOLD);
            keyFont = new Font(customFont, 8, java.awt.Font.BOLD);
            textFont = new Font(customFont, 8, Font.NORMAL);
            titleFont = new Font(customFont, 20, Font.BOLD);
            secondLevelTitleFont = new Font(customFont, 8, Font.BOLD);
            threeLevelTitleFont = new Font(customFont, 8, Font.NORMAL);
        } catch (Exception e) {
            LOGGER.error("创建pdf格式失败，异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 为表格添加一个内容
     *
     * @param value 值
     * @param font  字体
     * @param align 对齐方式
     * @return 添加的文本框
     */
    public static PdfPCell createCell(String value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        font.setColor(BaseColor.WHITE);
        cell.setPhrase(new Phrase(value, font));
        cell.setBackgroundColor(BaseColor.GRAY);
        cell.setMinimumHeight(40);
        return cell;
    }

    /**
     * 为表格添加一个内容
     *
     * @param value 值
     * @param font  字体
     * @return 添加的文本框
     */
    public static PdfPCell createCell(String value, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }


    /**
     * 为表格添加一个内容
     *
     * @param value      值
     * @param font       字体
     * @param align      对齐方式
     * @param colspan    占多少列
     * @param borderFlag 是否有有边框
     * @return 添加的文本框
     */
    public static PdfPCell createCell(String value, Font font, int align, int colspan,
                                      boolean borderFlag) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        cell.setPadding(3.0f);
        if (!borderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(4.0f);
            cell.setPaddingBottom(8.0f);
        }
        return cell;
    }

    /**
     * 创建一个表格对象
     *
     * @param colNumber 表格的列数
     * @return 生成的表格对象
     */
    public static PdfPTable createTable(int colNumber) {
        PdfPTable table = new PdfPTable(colNumber);
        table.setTotalWidth(MAX_WIDTH);
        table.setLockedWidth(true);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setBorder(1);
        return table;
    }

    private static PdfPCell createTextCell(String value) {
        return PDFUtil.createCell(value, PDFUtil.textFont);
    }


    /**
     * 生成嗒嗒行程单PDF文件
     *
     * @param date          构建日期
     * @param phone         手机号
     * @param baseTripInfos 行程信息
     * @param outputStream  输出流
     */
    public static void generateDaDaTripTable(Date date, String phone, List<BaseTripInfo> baseTripInfos, OutputStream outputStream) {
        String[] header = new String[]{"序号", "车型", "上车时间", "城市", "起点", "终点", "里程\n\n[公里]", "金额\n\n[元]", "备注"};
        int[] width = new int[]{10, 12, 22, 14, 40, 40, 10, 10, 10};
        Document document = null;
        try {
            // 建立一个Document对象
            document = new Document();
            // 设置页面大小
            document.setPageSize(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfContentByte canvas = writer.getDirectContent();

            // 添加图片
            Path path = Paths.get(ClassLoader.getSystemResource("image/dadachuxing-trip-table.png").toURI());
            Image img = Image.getInstance(path.toAbsolutePath().toString());
            img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight()); // scale the image to fit the page
            img.setAbsolutePosition(0, PageSize.A4.getHeight() - img.getScaledHeight()); // set the position of the image at the top-left corner of the page
            img.setAlignment(Image.ALIGN_TOP);
            document.add(img);

            // 设置列以及列宽度
            PdfPTable table = PDFUtil.createTable(header.length);
            table.setWidths(width);
            table.setWidthPercentage(100); // make sure the table fits the column width
            // 添加标题以及空行
            table.addCell(PDFUtil.createCell("嗒嗒出行—行程单", PDFUtil.titleFont, Element.ALIGN_CENTER, header.length, false));
            table.addCell(PDFUtil.createCell("DADA TRAVEL - TRIP TABLE\n\n\n\n", PDFUtil.secondLevelTitleFont, Element.ALIGN_CENTER, header.length, false));
            table.addCell(PDFUtil.createCell(" 姓名:   ________________________________   工号:   ________________________________   部门:   __________________________________\n\n\n", PDFUtil.threeLevelTitleFont, Element.ALIGN_CENTER, header.length, false));

            // 设置申请日期以及行程起止日期
            table.addCell(PDFUtil.createCell("申请日期:" + DateUtil.format(date, "yyyy-MM-dd"), PDFUtil.textFont, Element.ALIGN_LEFT, 4, false));
            table.addCell(PDFUtil.createCell("行程起止日期:" + "2022-04-06 至 2022-04-06", PDFUtil.textFont, Element.ALIGN_LEFT, header.length, false));

            table.addCell(PDFUtil.createCell("行程人手机号:" + phone, PDFUtil.textFont, Element.ALIGN_LEFT, 4, false));
            // 计算该行程单总金额
            double totalAmount = 0;
            for (BaseTripInfo baseTripInfo : baseTripInfos) {
                totalAmount += baseTripInfo.getAmount();
            }
            table.addCell(PDFUtil.createCell("共" + baseTripInfos.size() + "笔行程, 合计 " + NumberUtil.round(totalAmount, 2).toString() + "元",
                    PDFUtil.textFont, Element.ALIGN_LEFT, header.length, false));


            // 设置表头
            for (String headName : header) {
                table.addCell(PDFUtil.createCell(headName, PDFUtil.keyFont, Element.ALIGN_CENTER));
            }

            //填充表哥内容
            for (int i = 0; i < baseTripInfos.size(); i++) {
                // 序号
                table.addCell(createTextCell(String.valueOf(i + 1)));
                // 车型
                table.addCell(createTextCell(baseTripInfos.get(i).getVehicleType() == null ? "" :
                        baseTripInfos.get(i).getVehicleType()));
                // 上车时间
                table.addCell(createTextCell(baseTripInfos.get(i).getPickUpTime() == null ? "" :
                        baseTripInfos.get(i).getPickUpTime()));
                // 城市
                table.addCell(createTextCell(baseTripInfos.get(i).getCity() == null ? "" :
                        baseTripInfos.get(i).getCity()));
                // 起点
                table.addCell(createTextCell(baseTripInfos.get(i).getPickUpPoint() == null ? "" :
                        baseTripInfos.get(i).getPickUpPoint()));
                // 终点
                table.addCell(createTextCell(baseTripInfos.get(i).getDropOffPlace() == null ? "" :
                        baseTripInfos.get(i).getDropOffPlace()));
                // 里程
                table.addCell(createTextCell(baseTripInfos.get(i).getMileage() == null ? "" :
                        String.valueOf(baseTripInfos.get(i).getMileage())));
                // 金额
                double amount = baseTripInfos.get(i).getAmount();
                table.addCell(createTextCell(NumberUtil.round(amount, 2).toString()));
                // 备注
                table.addCell(createTextCell(baseTripInfos.get(i).getComment() == null ? "" :
                        baseTripInfos.get(i).getComment()));
            }

            // 创建ColumnText对象并添加表格
            ColumnText column = new ColumnText(canvas);
            column.addElement(table);

            // 设置ColumnText对象的位置
            float left = 36;
            float bottom = 36;
            float right = PageSize.A4.getWidth() - 36;
            float top = PageSize.A4.getHeight() / 2 + 170; // set the top to the middle of the page
            column.setSimpleColumn(left, bottom, right, top);
            column.go();
//            document.add(table);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //关闭流
            if (document != null) {
                document.close();
            }
        }
    }


}
