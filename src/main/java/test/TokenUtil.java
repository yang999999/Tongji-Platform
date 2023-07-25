package test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import java.io.*;


public class TokenUtil {
    public static String getToken (String url, String requestJson) throws IOException {
        String token = null;
        // 创建 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 POST 请求
        HttpPost httpPost = new HttpPost(url);

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
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(), "utf-8"))) {
            StringBuilder result = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                result.append(line.trim());
            }
            System.out.println(result.toString());
            token = result.substring(26,result.length()-2);
            System.out.println(token);
        }

        // 关闭连接
        response.close();
        httpClient.close();
        return token;
    }

}

