package com.dil.rocketmq;


import com.dil.rocketmq.service.WorkService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private WorkService workService;

    @Test
    public void testMQ() throws Exception {
        workService.work("Dil-RocketMQ-01");
        log.info("msg send success");
        Thread.sleep(5000);
    }

}
