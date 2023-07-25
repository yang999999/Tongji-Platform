package test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonParser {
    public static void main(String[] args) throws IOException {
        String jsonStr = "{\"datas\":[{\"dDtu\":\"15822217721\",\"dGthNum\":\"20220705\",\"dGthType\":\"总线型采集器\",\"dId\":35318,\"dInstal\":\"13米FC4\",\"dM1\":\"86.63\",\"dM2\":\"86.63\",\"dPrjId\":202,\"dPrjName\":\"萧山机场L滑\",\"dSenAddr\":10,\"dSenNum\":\"287E43580D0000E6\",\"dSenType\":\"沉降计\",\"dStruct\":\"边坡\",\"dTempr\":\"12.81\",\"dTime\":\"2022-08-31 09:04:13\",\"dUnit\":\"mm  \"}]}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonStr);
        JsonNode datas = root.get("datas");
        String dTempr = datas.get(0).get("dTempr").asText();
        System.out.println(dTempr); // 输出 12.81
    }
}