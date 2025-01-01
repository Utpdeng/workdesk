package com.dil.glm;

import com.alibaba.fastjson2.JSON;
import com.dil.glm.domain.ai.ChatCompletionSyncResponse;
import com.dil.glm.service.ZhiPuAi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Resource
    private ZhiPuAi zhiPuAi;

    @Test
    public void test() throws IOException {
        String req = "写一个50字的关于生活的日记";
        String res = zhiPuAi.ai(req);
        log.info(res);
    }

    @Test
    public void test_http() throws IOException {
        String apiKeySecret = "0e16e77453ab321e8cc46af3afcfc58a.7REJlHDMimEZ2Yiw";
        String token = apiKeySecret;

        URL url = new URL("https://open.bigmodel.cn/api/paas/v4/chat/completions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        connection.setDoOutput(true);

        String request = "Java写一个冒泡排序";

        String jsonInpuString = "{"
                + "\"model\":\"glm-4-flash\","
                + "\"messages\": ["
                + "    {"
                + "        \"role\": \"user\","
                + "        \"content\": \" " + request + "\""
                + "    }"
                + "]"
                + "}";


        try(OutputStream os = connection.getOutputStream()){
            byte[] input = jsonInpuString.getBytes(StandardCharsets.UTF_8);
            os.write(input);
        }

        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        ChatCompletionSyncResponse response = JSON.parseObject(content.toString(), ChatCompletionSyncResponse.class);
        System.out.println(response.getChoices().get(0).getMessage().getContent());

    }

}
