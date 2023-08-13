package boot;

import bean.CGetDataParam;
import bean.CMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//C1-1：370.93  基点：120.81            284E622A21220972   北一区
//        C4-1；369.97  基点：118.45    289B0F040D000026    北二区
//        C6-1:438.53  基点：117.58    280A5CD524220662  北三区
//        C10-2:485.32 基点：92.48   2816B9040D000055  南区
// 用于将所有存活传感器的数据导入到本地Excel
public class GetDataToLocal {
    static Map<String, Data[]> cache = new HashMap<>();
    // 如果想更新本地的Excel中SW,沉降基点，以及沉降点的数据，那么执行一次main函数即可，默认保存在该类同一级目录下
    public static void main(String[] args) throws IOException {
        String beginTime = "2022-08-03 00:00:00";
        String endTime = "2023-08-09 00:00:00";
        GetDataToLocal getDataToLocal = new GetDataToLocal();
        // 北一
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C1-1");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C1-2");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C1-3");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C2-1");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C2-2");
        getDataToLocal.getSWDataToLocal(beginTime,endTime,"SW1");
        // 北二
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C3-1");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C3-2");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C3-3");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C4-1");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C5-1");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C5-2");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C5-3");
        getDataToLocal.getSWDataToLocal(beginTime,endTime,"SW-2");
        // 北三
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C6-1");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C7-1");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C8-1");
        // 水位坏掉了，就不写入了

        // 南区
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C9-1");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C9-2");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C9-3");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C10-1");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C10-2");
        getDataToLocal.getSettlementDataToLocal(beginTime,endTime,"C10-3");
        getDataToLocal.getSWDataToLocal(beginTime,endTime,"SW5");
    }
    /*
    例子：
    String beginTime = "2022-08-03 00:00:00";
    String endTime = "2023-08-09 00:00:00";
    String dInstalJ = "基点";
    String dInstalSW = "SW5"; //改  水位传感器安装位置，不拿水位传感器的时候传null
    String dSenNum = "2816B9040D000055"; // 改基点 拿地表沉降传感器的时候对应基点的编号
    String dInstal = "C10-2"; // 改测点 除水位外地表沉降传感器安装位置 要水位的数据传则null
    */
    // 拿沉降数据的话需要传入基点跟沉降点的信息；拿水位点则只需要传入水位点的信息（水位不需要基点进行校准）
    // 每个表面沉降的数据又需要通过基准值的设定来校准，也就是dM2减去平台给的基准值
    public void getSettlementDataToLocal(String beginTime, String endTime,String dInstal) throws IOException {
        // 根据传入的传感器通道比如C1-1拿到获取数据所需的参数
        CGetDataParam param = CMapper.dInstalToParam.get(dInstal);
        String dInstalJ = param.getdInstalJ();
        String dSenNum = param.getdSenNum();
        GetJsonData getJsonData = new GetJsonData();
        // 根据传感器安装位置拿到对应表面沉降传感器的监测数据
        Data[] dataArr = getJsonData.getData(beginTime, endTime, dInstal);
        // 找到对应基点的数据
        Data[] dataArrJ = cache.containsKey(dSenNum) ? cache.get(dSenNum) : getJsonData.getData(beginTime, endTime, dInstalJ, dSenNum);
        // 由于存在缺失值，做差的话以较小值来进行
        Data[] dataArrActual = new Data[Math.min(dataArrJ.length, dataArr.length)];
        for (int i = 0; i < dataArrActual.length; i++) {
            Data data = new Data();
            dataArrJ[i].setdM2(String.valueOf(Double.parseDouble(dataArrJ[i].getdM2()) - param.getjBase()));
            data.setdM2(
                    String.valueOf(
                            Double.parseDouble(dataArr[i].getdM2()) - param.getcBase() -
                                    Double.parseDouble(dataArrJ[i].getdM2()))
            );
            data.setdTempr(dataArr[i].getdTempr());
            data.setdTime(dataArr[i].getdTime());
            data.setdInstal(dataArr[i].getdInstal());
            dataArrActual[i] = data;
            System.out.println(data);
        }
        // 第一次获取基点需要写入一次Excel，后续则不用写入
        if(!cache.containsKey(dSenNum)) {
            writeExcelToLocal(CMapper.dInstalToExcelJName.get(dInstal) + ".xlsx",dataArrJ);
        }
        cache.put(dSenNum,dataArrJ);
        writeExcelToLocal(dInstal + ".xlsx",dataArrActual);
    }

    // 写入水位Excel,注意只有SW1,SW5,SW-2三个
    public void getSWDataToLocal(String beginTime, String endTime,String dInstalSW) throws IOException {
        GetJsonData getJsonData = new GetJsonData();
        Data[] dataArrSW = getJsonData.getData(beginTime, endTime, dInstalSW);
        writeExcelToLocal(dInstalSW + ".xlsx",dataArrSW);
    }

    // String filePath = "dataArrActual.xlsx"; // Excel文件路径
    private void writeExcelToLocal(String filePath, Data[] dataArr) {
        // 这里开始往后是写入Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data"); // 创建工作表
            // 创建标题行，也就是表中的列名
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("dInstal");
            headerRow.createCell(1).setCellValue("dTime");
            headerRow.createCell(2).setCellValue("dTempr");
            headerRow.createCell(3).setCellValue("dM2");
            // 写入数据行，也就是每条数据每一列写入什么
            int rowNum = 1;
            // dataArr是需要转化为Excel的基点或者沉降点或者水位点的数组
            for (Data data : dataArr) {
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
