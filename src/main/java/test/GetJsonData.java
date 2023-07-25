package test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;


//在这里call远程API拿数据
public class GetJsonData {
//    public static void man() throws Exception {
//        String tokenUrl = "http://47.92.241.95:8800/api/BKWeb/ReqToken";
//        String tokenRequestJson = "{\"code\": \"jycs\", \"user\": \"jycs\", \"pass\": \"e10adc3949ba59abbe56e057f20f883e\"}";
//        String token = TokenUtil.getToken(tokenUrl,tokenRequestJson);
//        String dataUrl = "http://47.92.241.95:8800/api/BKWeb/RequestData";
//        //在这里设置想要拿的时间段内的数据wq
//        String dataRequestJson = "{\"token\":\""+ token +"\",\"beginTime\":\"2022-08-31 00:00:00\",\"endTime\":\"2022-08-31 9:04:15\",\"prjId\":\"0\",\"gthNum\":\"\",\"sensor\":null,\"pgPerCnt\":50,\"page\":\"1\"}";
//        GetJsonData getJsonData = new GetJsonData();
//        jsonData = getJsonData.getData(dataUrl, dataRequestJson);
//        System.out.println("是法萨芬撒发生"+jsonData);
//
//
//        //截取所需要的字符段  以dtemper为例
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(jsonData);
//        JsonNode datas = root.get("datas");
//        int i;
//
//        for(i=0;i<datas.size();i++){
//            String dTempr = datas.get(i).get("dTempr").asText();
//            System.out.println(dTempr);
//        }
//        //将截取的字符段整合为数组
//        int length = datas.size();
//        double[] dTemprArray = new double[length]; // 定义一个存放数据的数组
//        int j;
//        for ( j= 0; j < length; j++) {
//            JsonNode data = datas.get(j);
//            String dTempr = data.get("dTempr").asText(); // 获取 dTempr 属性的值
//            double value = Double.parseDouble(dTempr); // 将字符串转换为 double 类型
//            dTemprArray[j] = value; // 将转换后的值放入数组中
//        }
//        System.out.println(Arrays.toString(dTemprArray));
//
//    }
    public String getData(String dataUrl,String requestJson) throws IOException {
        // 创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 POST 请求
        HttpPost httpPost = new HttpPost(dataUrl);

        // 设置请求头信息
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        // 创建 JSON 请求数据
        String requestBody = requestJson;
        HttpEntity entity = new StringEntity(requestBody);

        // 设置请求体
        httpPost.setEntity(entity);

        // 发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 读取响应
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(), "utf-8"))) {
            StringBuilder result = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                result.append(line.trim());
            }
            System.out.println(result.toString());
            return result.toString();
        }finally {
            // 关闭连接
            response.close();
            httpClient.close();
        }
    }



}
