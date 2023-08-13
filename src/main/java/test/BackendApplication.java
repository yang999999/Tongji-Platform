package test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@Import(CorsConfig.class)
public class BackendApplication {
    private static String jsonData;
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BackendApplication.class, args);
        while (true){
            Thread.sleep(10000);
        }
    }
    public static String G(String time1,String time) throws Exception {
        String tokenUrl = "http://47.92.241.95:8800/api/BKWeb/ReqToken";
        String tokenRequestJson = "{\"code\": \"jycs\", \"user\": \"jycs\", \"pass\": \"e10adc3949ba59abbe56e057f20f883e\"}";
        String token = TokenUtil.getToken(tokenUrl,tokenRequestJson);
        String dataUrl = "http://47.92.241.95:8800/api/BKWeb/RequestData";
        //在这里设置想要拿的时间段内的数据wq
        String dataRequestJson = "{\"token\":\""+ token +"\",\"beginTime\":\""+time1+"\",\"endTime\":\""+time+"\",\"prjId\":\"0\",\"gthNum\":\"\",\"sensor\":null,\"pgPerCnt\":50,\"page\":\"1\"}";
        GetJsonData getJsonData = new GetJsonData();
        jsonData = getJsonData.getData(dataUrl, dataRequestJson);
        //截取所需要的字符段  以dtemper为例
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonData);
        JsonNode datas = root.get("datas");
        int i;
        for(i=0;i<datas.size();i++){
            String dTempr = datas.get(i).get("dTempr").asText();
            //System.out.println(dTempr);
        }
        //将截取的字符段整合为数组
        int length = datas.size();
        double[] dTemprArray = new double[length]; // 定义一个存放数据的数组
        int j;
        for ( j= 0; j < length; j++) {
            JsonNode data = datas.get(j);
            String dTempr = data.get("dTempr").asText(); // 获取 dTempr 属性的值
            double value = Double.parseDouble(dTempr); // 将字符串转换为 double 类型
            dTemprArray[j] = value; // 将转换后的值放入数组中
        }
        System.out.println(Arrays.toString(dTemprArray));
        System.out.println(jsonData);
        System.out.println(jsonData.length());
        Gson gson = new Gson();
        String DATA = gson.toJson(dTemprArray);
        return DATA;
    }
    public String timerCuartz(){
        while (true){

        }
    }
}

@RestController
@CrossOrigin(origins = "http://localhost:8080") // 设置允许跨域请求的前端源
class DataController {
    @GetMapping("/api/data")
    public String getData(@RequestParam("date1") String date1, @RequestParam("date2") String date2) {
        String message ="" ;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String t=sdf.format(System.currentTimeMillis());
            message = BackendApplication.G(date2,t);
        } catch (Exception e) {

        }
        System.out.println(date1);
        System.out.println(date2);
        return message;

    }

}