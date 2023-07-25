package boot;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//在这里call远程API拿数据
public class GetJsonData {
    public static void main(String[] args) throws Exception {

        //在这里设置想要拿的时间段内的数据wq
        GetJsonData getJsonData = new GetJsonData();
        String beginTime = "2023-04-28 00:00:00";
        String endTime = "2023-07-01 00:00:00";
        String dInstalJ = "基点";
        String dSenNum = "284E622A21220972";
        String dInstal = "C1-1";
        Data[] dataArr = getJsonData.getData(beginTime, endTime, dInstal);
        Data[] dataArrJ = getJsonData.getData(beginTime, endTime, dInstalJ,dSenNum);
        Data[] dataArrAcyual = new Data[Math.min(dataArrJ.length, dataArr.length) - 1];
        for (int i = 0;i < dataArrAcyual.length;i++) {
            Data data = new Data();
            data.setdM2(
                    String.valueOf(
                            Double.parseDouble(dataArr[i + 1].getdM2()) - Double.parseDouble(dataArr[i].getdM2()) -
                                    (Double.parseDouble(dataArrJ[i + 1].getdM2()) - Double.parseDouble(dataArrJ[i].getdM2()))
                    )
            );
            data.setdTempr(dataArr[i + 1].getdTempr());
            data.setdTime(dataArr[i + 1].getdTime());
            dataArrAcyual[i] = data;
            System.out.println(data);
        }
        System.out.println("长度对比-------------------------------------");
        System.out.println(dataArr.length);
        System.out.println(dataArrJ.length);
        System.out.println(dataArrAcyual.length);
    }
    /*
    *关于请求参数的说明
    *｛
        token: “abcef21jska912saa0122skkk11”  //身份令牌
        beginTime: “2022-01-01 00:00:00”,      //开始时间
        endTime: “2022-01-31 23:59:59”,       //结束时间
        prjId: 1,       //项目工程ID，0表示所有项目工程
        gthNum: “tetsNum” , //采集器编号， 空字符串表示所有采集器
        sensor:[9812,2289,2331], //传感器编号，空数组表示所有传感器
        page:0,     //0:第一获取表示未知页数，>0表示对应页数
        pgPerCnt：50  //每页多少记录数，最大不超过1000
        ｝
     */
    public static List<String> getDateTimeRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<String> dateTimeList = new ArrayList<>();

        while (!startDate.isAfter(endDate)) {
            dateTimeList.add(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            startDate = startDate.plusDays(1);
        }

        return dateTimeList;
    }
    public Data[] getData(String beginTime, String endTime, String dInstal) throws IOException {
        List<Data> totalData = new ArrayList<>();
        // 获得时间切片，把时间段切分成一天一天去请求
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startDate = LocalDateTime.parse(beginTime, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endTime, formatter);

        List<String> dateTimeList = getDateTimeRange(startDate, endDate);

        // 创建 HttpClient 对象,并在后续每天一次的请求中复用这条链接，避免重复创建，提升响应速度
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 POST 请求，同样也可以复用
        String dataUrl = "http://47.92.241.95:8800/api/BKWeb/RequestData";
        HttpPost httpPost = new HttpPost(dataUrl);

        // 设置请求头信息
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        // 获取Token
        String tokenUrl = "http://47.92.241.95:8800/api/BKWeb/ReqToken";
        String tokenRequestJson = "{\"code\": \"jycs\", \"user\": \"jycs\", \"pass\": \"e10adc3949ba59abbe56e057f20f883e\"}";
        String token = TokenUtil.getToken(tokenUrl,tokenRequestJson);

        // 构造参数对象，同样可以复用
        RequestBody requestBody = new RequestBody();
        requestBody.setToken(token);
        requestBody.setPgPerCnt(1000);
        requestBody.setPage("0");
        try {
            for (int i = 0;i < dateTimeList.size()-1;i++){
                List<Data> list = getOneDayData(dateTimeList.get(i),dateTimeList.get(i + 1),dInstal,httpClient,requestBody,httpPost);
                for (Data data : list) {
                    totalData.add(data);
                }
            }
        } finally { //务必关闭资源
            httpClient.close();
        }
        Collections.sort(totalData, Comparator.comparing(Data::getdTime));
        Data[] ans = new Data[totalData.size()];
        for (int i = 0;i < ans.length;i++) {
            ans[i] = totalData.get(i);
        }
        return ans;
    }

    private List<Data> getOneDayData(String beginTime, String endTime, String dInstal, CloseableHttpClient httpClient, RequestBody requestBody, HttpPost httpPost) throws IOException {

        requestBody.setBeginTime(beginTime);
        requestBody.setEndTime(endTime);

        // 使用 Jackson 库将参数对象转换为 JSON 字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBodyJson = objectMapper.writeValueAsString(requestBody);

        // 构造请求实体
        HttpEntity entity = new StringEntity(requestBodyJson);

        // 设置请求体
        httpPost.setEntity(entity);

        // 发送请求
        CloseableHttpResponse resp = httpClient.execute(httpPost);

        // 读取响应
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(resp.getEntity().getContent(), "utf-8"))) {
            StringBuilder result = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                result.append(line.trim());
            }
            // 使用 Jackson 库将 JSON 字符串解析为字符串数组
            objectMapper = new ObjectMapper();
            Response response = objectMapper.readValue(result.toString(), Response.class);
            // 遍历字符串数组，将每个 JSON 字符串映射为对象
            Data[] objectArray = new Data[response.getDatas().size()];
            for (int i = 0; i < objectArray.length; i++) {
                objectArray[i] = response.getDatas().get(i);
            }
            List<Data> list = getAscDataArray(dInstal, objectArray);
            return list;
        }finally {
            // 关闭连接
            resp.close();
        }
    }
    private List<Data> getAscDataArray(String dInstal, Data[] objectArray) {
        List<Data> list = new ArrayList<>();
        for (Data data : objectArray) {
            if (Objects.equals(data.getdInstal(), dInstal)) list.add(data);
        }
        return list;
    }





    // 为了找到基点进行重写
    public Data[] getData(String beginTime, String endTime, String dInstal, String dSenNum) throws IOException {
        List<Data> totalData = new ArrayList<>();
        // 获得时间切片，把时间段切分成一天一天去请求
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startDate = LocalDateTime.parse(beginTime, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endTime, formatter);

        List<String> dateTimeList = getDateTimeRange(startDate, endDate);

        // 创建 HttpClient 对象,并在后续每天一次的请求中复用这条链接，避免重复创建，提升响应速度
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 POST 请求，同样也可以复用
        String dataUrl = "http://47.92.241.95:8800/api/BKWeb/RequestData";
        HttpPost httpPost = new HttpPost(dataUrl);

        // 设置请求头信息
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        // 获取Token
        String tokenUrl = "http://47.92.241.95:8800/api/BKWeb/ReqToken";
        String tokenRequestJson = "{\"code\": \"jycs\", \"user\": \"jycs\", \"pass\": \"e10adc3949ba59abbe56e057f20f883e\"}";
        String token = TokenUtil.getToken(tokenUrl,tokenRequestJson);

        // 构造参数对象，同样可以复用
        RequestBody requestBody = new RequestBody();
        requestBody.setToken(token);
        requestBody.setPgPerCnt(1000);
        requestBody.setPage("0");
        try {
            for (int i = 0;i < dateTimeList.size()-1;i++){
                List<Data> list = getOneDayData(dateTimeList.get(i),dateTimeList.get(i + 1),dInstal, dSenNum,httpClient,requestBody,httpPost);
                for (Data data : list) {
                    totalData.add(data);
                }
            }
        } finally { //务必关闭资源
            httpClient.close();
        }
        Collections.sort(totalData, Comparator.comparing(Data::getdTime));
        Data[] ans = new Data[totalData.size()];
        for (int i = 0;i < ans.length;i++) {
            ans[i] = totalData.get(i);
        }
        return ans;
    }

    private List<Data> getOneDayData(String beginTime, String endTime, String dInstal, String dSenNum,CloseableHttpClient httpClient, RequestBody requestBody, HttpPost httpPost) throws IOException {

        requestBody.setBeginTime(beginTime);
        requestBody.setEndTime(endTime);

        // 使用 Jackson 库将参数对象转换为 JSON 字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBodyJson = objectMapper.writeValueAsString(requestBody);

        // 构造请求实体
        HttpEntity entity = new StringEntity(requestBodyJson);

        // 设置请求体
        httpPost.setEntity(entity);

        // 发送请求
        CloseableHttpResponse resp = httpClient.execute(httpPost);

        // 读取响应
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(resp.getEntity().getContent(), "utf-8"))) {
            StringBuilder result = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                result.append(line.trim());
            }
            // 使用 Jackson 库将 JSON 字符串解析为字符串数组
            objectMapper = new ObjectMapper();
            Response response = objectMapper.readValue(result.toString(), Response.class);
            // 遍历字符串数组，将每个 JSON 字符串映射为对象
            Data[] objectArray = new Data[response.getDatas().size()];
            for (int i = 0; i < objectArray.length; i++) {
                objectArray[i] = response.getDatas().get(i);
            }
            List<Data> list = getAscDataArray(dInstal, dSenNum,objectArray);
            return list;
        }finally {
            // 关闭连接
            resp.close();
        }
    }

    private List<Data> getAscDataArray(String dInstal, String dSenNum, Data[] objectArray) {
        List<Data> list = new ArrayList<>();
        for (Data data : objectArray) {
            if (Objects.equals(data.getdInstal(), dInstal) && Objects.equals(data.getdSenNum(), dSenNum)) list.add(data);
        }
        return list;
    }

}




