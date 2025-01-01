package com.dil.glm;
import java.util.Arrays;
import java.lang.System;
import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;
/**
 * @author: "xx"
 * @Date: 2024/12/25
 */
public class TongyiDashscopeTest {

    // 建议dashscope SDK的版本 >= 2.12.0
        public static GenerationResult callWithMessage() throws ApiException, NoApiKeyException, InputRequiredException {
            Generation gen = new Generation();
            Message systemMsg = Message.builder()
                    .role(Role.SYSTEM.getValue())
                    .content("You are a helpful assistant.")
                    .build();
            Message userMsg = Message.builder()
                    .role(Role.USER.getValue())
                    .content("你是谁？")
                    .build();
            GenerationParam param = GenerationParam.builder()
                    // 若没有配置环境变量，请用百炼API Key将下行替换为：.apiKey("sk-xxx")
                    .apiKey("sk-289dd5194a604b29a015c6e86e477c75")
                    .model("qwen-plus")
                    .messages(Arrays.asList(systemMsg, userMsg))
                    .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                    .build();
            return gen.call(param);
        }
        public static void main(String[] args) {
            try {
                GenerationResult result = callWithMessage();
                System.out.println(JsonUtils.toJson(result));
            } catch (ApiException | NoApiKeyException | InputRequiredException e) {
                // 使用日志框架记录异常信息
                System.err.println("An error occurred while calling the generation service: " + e.getMessage());
            }
            System.exit(0);
        }
}
