package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@SpringBootApplication
@Import(CorsConfig.class)
public class BackendApplication1 {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BackendApplication.class, args);



    }
    public static String g(String date1, String date2) {
        String result = date1 + date2;

        MyObject obj = new MyObject();
        obj.setResult(result);

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            // handle exception here
        }

        return jsonStr;
    }
}
