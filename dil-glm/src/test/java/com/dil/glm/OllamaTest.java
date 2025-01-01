package com.dil.glm;

import com.alibaba.fastjson2.JSON;
import com.dil.glm.domain.ai.ChatCompletionSyncResponse;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author: "xx"
 * @Date: 2024/12/26
 */
public class OllamaTest {

    @Test
    public void test_http() throws IOException {
        try {
            // 请求URL
            URL url = new URL("http://localhost:11434/v1/chat/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为POST
            connection.setRequestMethod("POST");
            // 设置请求头
            connection.setRequestProperty("Content-Type", "application/json");
            // 启用输入输出流
            connection.setDoOutput(true);
            // 请求体内容
            String jsonInputString = "{"
                    + "\"model\": \"qwen\", "
                    + "\"messages\": ["
                    + "  {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"}, "
                    + "  {\"role\": \"user\", \"content\": \"Java实现冒泡排序\"}"
                    + "]"
                    + "}";

            // 发送请求体
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // 获取响应码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
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

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
