package boot;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//C1-1：370.93  基点：120.81            284E622A21220972
//        C4-1；369.97  基点：118.45    289B0F040D000026
//        C6-1:438.53  基点：117.58    280A5CD524220662
//        C10-2:485.32 基点：92.48   2816B9040D000055

public class GetDataToLocal {
    public static void main(String[] args) throws IOException {
        GetJsonData getJsonData = new GetJsonData();
        String beginTime = "2022-08-03 00:00:00";
        String endTime = "2023-07-01 00:00:00";
        String dInstalJ = "基点";
        String dInstalSW = "SW5"; //改
        String dSenNum = "2816B9040D000055"; // 改基点
        String dInstal = "C10-2"; // 改测点
        Data[] dataArr = getJsonData.getData(beginTime, endTime, dInstal);
//        Data[] dataArrSW = getJsonData.getData(beginTime, endTime, dInstalSW);
        Data[] dataArrJ = getJsonData.getData(beginTime, endTime, dInstalJ,dSenNum);
        Data[] dataArrAcyual = new Data[Math.min(dataArrJ.length, dataArr.length)];
        for (int i = 0;i < dataArrAcyual.length;i++) {
            Data data = new Data();
            dataArrJ[i].setdM2(String.valueOf(Double.parseDouble(dataArrJ[i].getdM2()) - 92.48));
            data.setdM2(
                    String.valueOf(
                            Double.parseDouble(dataArr[i].getdM2()) - 485.32 -
                                    Double.parseDouble(dataArrJ[i].getdM2()))
            );
            data.setdTempr(dataArr[i].getdTempr());
            data.setdTime(dataArr[i].getdTime());
            data.setdInstal(dataArr[i].getdInstal());
            dataArrAcyual[i] = data;
            System.out.println(data);
        }

        String filePath = "dataArrAcyual.xlsx"; // Excel文件路径

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data"); // 创建工作表

            // 创建标题行
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("dInstal");
            headerRow.createCell(1).setCellValue("dTime");
            headerRow.createCell(2).setCellValue("dTempr");
            headerRow.createCell(3).setCellValue("dM2");
            // 写入数据行
            int rowNum = 1;
            for (Data data : dataArrAcyual) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(data.getdInstal());
                row.createCell(1).setCellValue(data.getdTime());
                row.createCell(2).setCellValue(data.getdTempr());
                row.createCell(3).setCellValue(data.getdM2());
            }

            // 将工作簿写入到文件
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }

            System.out.println("Excel文件已成功写入：" + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
