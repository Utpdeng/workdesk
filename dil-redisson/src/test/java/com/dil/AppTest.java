package com.dil;


import com.dil.redisson.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest {

    @Autowired
    private RedissonClient redisson;

    @Resource
    private IRedisService redisService;

    @Test
    public void test() {
        redisson.getBucket("key").set("v1");
        Object o = redisson.getBucket("key").get();
        log.info("测试结果：{}", o.toString());
    }

    @Test
    public void testSetVal() {
        redisService.setValue("k1", "v1");

    }
}
