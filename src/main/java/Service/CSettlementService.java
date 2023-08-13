package Service;

import boot.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import util.ExcelReader;

@Component
public class CSettlementService {
    public Data[] readExcelToDataArray(String dInstal, String startTime, String endTime) {
        String filePath = dInstal + ".xlsx";
       return ExcelReader.readExcel(filePath,startTime,endTime);
    }
}
