package util;

import boot.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelReader {

    public static Data[] readExcel(String filePath, String startTime, String endTime) {
        List<Data> dataList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (Row row : sheet) {
                Cell timeCell = row.getCell(1);
                if ("dTime".equals(timeCell.getStringCellValue())) {
                    continue;
                }
                if (timeCell != null && timeCell.getCellType() == CellType.STRING) {
                    String dTime = timeCell.getStringCellValue();
                    try {
                        Date time = dateFormat.parse(dTime);
                        if (time.compareTo(dateFormat.parse(startTime)) >= 0 &&
                                time.compareTo(dateFormat.parse(endTime)) <= 0) {
                            String dInstal = row.getCell(0).getStringCellValue();
                            String dTempr = row.getCell(2).getStringCellValue();
                            String dM2 = row.getCell(3).getStringCellValue();

                            Data data = new Data();
                            data.setdInstal(dInstal);
                            data.setdTime(dTime);
                            data.setdTempr(dTempr);
                            data.setdM2(dM2);
                            dataList.add(data);
                        }
                    } catch (ParseException e) {
                        // Handle parsing exception if necessary
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList.toArray(new Data[dataList.size()]);
    }

    public static void main(String[] args) {
        String filePath = "C2-1.xlsx"; // Replace with your Excel file path
        Data[] dataArray = readExcel(filePath, "2023-05-28 00:00:00", "2023-05-29 00:00:00");
        for (Data data : dataArray) {
            System.out.println(data);
        }
    }
}