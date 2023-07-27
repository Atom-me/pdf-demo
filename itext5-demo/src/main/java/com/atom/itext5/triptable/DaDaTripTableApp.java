package com.atom.itext5.triptable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @author Atom
 */
public class DaDaTripTableApp {
    public static void main(String[] args) throws FileNotFoundException {
        Date date = Date.from(Instant.now());
        String phone = "18588889999";

        List<BaseTripInfo> baseTripInfos = getBaseTripInfos();

        try (OutputStream outputStream = new FileOutputStream("./itext5-demo/output/dada-trip-Table.pdf")) {
            PDFUtil.generateDaDaTripTable(date, phone, baseTripInfos, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<BaseTripInfo> getBaseTripInfos() {
        return List.of(
                new BaseTripInfo(
                        "2136474321523452345234513",
                        1,
                        "滴滴快车",
                        "04-06 21:03 周三",
                        "北京市",
                        "渝北|重庆气象局-西门",
                        "渝中|嘉陵江底",
                        15.1,
                        65.67,
                        ""
                ),new BaseTripInfo(
                        "2136474321523452345234513",
                        1,
                        "滴滴快车",
                        "04-06 21:03 周三",
                        "北京市",
                        "渝北|重庆气象局-西门",
                        "渝中|嘉陵江底",
                        15.1,
                        65.67,
                        ""
                ),new BaseTripInfo(
                        "2136474321523452345234513",
                        1,
                        "滴滴快车",
                        "04-06 21:03 周三",
                        "北京市",
                        "渝北|重庆气象局-西门",
                        "渝中|嘉陵江底",
                        15.1,
                        65.67,
                        ""
                ),new BaseTripInfo(
                        "2136474321523452345234513",
                        1,
                        "滴滴快车",
                        "04-06 21:03 周三",
                        "北京市",
                        "渝北|重庆气象局-西门",
                        "渝中|嘉陵江底",
                        15.1,
                        65.67,
                        ""
                ),new BaseTripInfo(
                        "2136474321523452345234513",
                        1,
                        "滴滴快车",
                        "04-06 21:03 周三",
                        "北京市",
                        "渝北|重庆气象局-西门",
                        "渝中|嘉陵江底",
                        15.1,
                        65.67,
                        ""
                ),new BaseTripInfo(
                        "2136474321523452345234513",
                        1,
                        "滴滴快车",
                        "04-06 21:03 周三",
                        "北京市",
                        "渝北|重庆气象局-西门",
                        "渝中|嘉陵江底",
                        15.1,
                        65.67,
                        ""
                ),new BaseTripInfo(
                        "2136474321523452345234513",
                        1,
                        "滴滴快车",
                        "04-06 21:03 周三",
                        "北京市",
                        "渝北|重庆气象局-西门",
                        "渝中|嘉陵江底",
                        15.1,
                        65.67,
                        ""
                )
        );
    }
}
