package com.dil.redis.api;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 使用步骤：
     *  引入 spring-boot-starter-data-redis 依赖
     *  在 application.yml 配置 Redis 信息
     *  注入RedisTemplate
     * 此方式会采用默认的 JDK 序列化方式，会序列化字节
     *     可引入 fasterxml 依赖进行 json 序列化，或者 springboot-web（包含此依赖）
     */
    @Test
    public void test() {
        redisTemplate.opsForValue().set("name", "zhangsan");
        Object name = redisTemplate.opsForValue().get("name");
        log.info("测试结果：{}", name);
    }

    @Test
    public void testSerialization() {
        redisTemplate.opsForValue().set("key", "lisi");
        Object key = redisTemplate.opsForValue().get("key");
        log.info("测试结果: {}", key);
    }
}
