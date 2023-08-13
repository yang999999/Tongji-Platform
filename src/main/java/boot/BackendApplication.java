package boot;

import Service.CSettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.CorsConfig;

import java.text.SimpleDateFormat;

@SpringBootApplication
@Import(CorsConfig.class)
public class BackendApplication {
    private static String jsonData;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BackendApplication.class, args);
        while (true) {
            Thread.sleep(10000);
        }
    }

    public String timerCuartz() {
        while (true) {

        }
    }
}

@RestController
@ComponentScan(basePackages = "Service")
@CrossOrigin(origins = "http://localhost:8080") // 设置允许跨域请求的前端源
class DataController {

    @Autowired
    CSettlementService cSettlementService;

    @GetMapping("/api/data1") // data1 data2分别是起始时间
    public String getData(@RequestParam("date1") String date1, @RequestParam("date2") String date2) {
        String message = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String t = sdf.format(System.currentTimeMillis());
            boot.GetJsonData getJsonData = new GetJsonData();
            String beginTime = "2023-05-28 00:00:00";
            String endTime = "2023-07-01 00:00:00";
            String dInstal = "C1-1";
            date1 = date1 + " 00:00:00";
            date2 = date2 + " 00:00:00";

            Data[] dataArr = cSettlementService.readExcelToDataArray(dInstal, date1, date2);
            System.out.println("---");
            System.out.println(dataArr.length);
            System.out.println("---");
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (Data data : dataArr) {
                sb.append(data.getdM2());
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(']');
            System.out.println(sb);
            message = message + sb.toString();
        } catch (Exception e) {
        }
        System.out.println(date1 + date2);
        return message;
    }

}