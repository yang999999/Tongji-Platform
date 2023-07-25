package test;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import java.io.*;

import java.util.HashMap;
import com.alibaba.fastjson.JSON;


public class SendPost {
    public static void main(String[] args) throws Exception {
        // 创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 POST 请求
        HttpPost httpPost = new HttpPost("http://47.92.241.95:8800/api/BKWeb/ReqToken");

        // 设置请求头信息
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        // 创建 JSON 请求数据
        String requestBody = "{\"code\": \"jycs\", \"user\": \"jycs\", \"pass\": \"e10adc3949ba59abbe56e057f20f883e\"}";
        HttpEntity entity = new StringEntity(requestBody);

        // 设置请求体
        httpPost.setEntity(entity);

        // 发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 读取响应
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(), "utf-8"))) {
            StringBuilder result = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                result.append(line.trim());
            }
            System.out.println(result.toString());
            String token = result.substring(26,result.length()-2);
            System.out.println(token);
        }

        // 关闭连接
        response.close();
        httpClient.close();
    }


//    public String sendPostWithJson(String url, String jsonStr, HashMap<String,String> headers) {
//        // 返回的结果
//        String jsonResult = "";
//        try {
//            HttpClient client = new HttpClient();
//            // 连接超时
//            client.getHttpConnectionManager().getParams().setConnectionTimeout(3*1000);
//            // 读取数据超时
//            client.getHttpConnectionManager().getParams().setSoTimeout(3*60*1000);
//            client.getParams().setContentCharset("UTF-8");
//            PostMethod postMethod = new PostMethod(url);
//
//            postMethod.setRequestHeader("content-type", headers.get("content-type"));
//
//            // 非空
//            if (null != jsonStr && !"".equals(jsonStr)) {
//                StringRequestEntity requestEntity = new StringRequestEntity(jsonStr, headers.get("content-type"), "UTF-8");
//                postMethod.setRequestEntity(requestEntity);
//            }
//            int status = client.executeMethod(postMethod);
//            if (status == HttpStatus.SC_OK) {
//                jsonResult = postMethod.getResponseBodyAsString();
//            } else {
//                throw new RuntimeException("接口连接失败！");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("接口连接失败！");
//        }
//        return jsonResult;
//    }
}
