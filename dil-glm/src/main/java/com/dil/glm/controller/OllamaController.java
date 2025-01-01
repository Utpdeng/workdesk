package com.dil.glm.controller;


import com.dil.glm.service.OllamaTongyiAi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class OllamaController {

    @Autowired
    private OllamaTongyiAi ollamaTongyiAi;

    @GetMapping("ollama")
    public String testOllama() throws IOException {
        String res = ollamaTongyiAi.ai("java写一个冒泡排序");
        return res;
    }

}