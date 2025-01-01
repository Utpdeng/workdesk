package com.dil.glm.service;

import com.alibaba.fastjson2.JSON;
import com.dil.glm.domain.ai.ChatCompletionSyncResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class ZhiPuAi {

    @Value("${zhipu.apiKeySecret}")
    private String apiKeySecret;

    @Value("${zhipu.apiUrl}")
    private String apiUrl;

    public String ai(String request) throws IOException {
        String token = apiKeySecret;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        connection.setDoOutput(true);

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

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        ChatCompletionSyncResponse response = JSON.parseObject(content.toString(), ChatCompletionSyncResponse.class);
        String res = response.getChoices().get(0).getMessage().getContent();
        System.out.println("生成完毕");
        return res;
    }

}
