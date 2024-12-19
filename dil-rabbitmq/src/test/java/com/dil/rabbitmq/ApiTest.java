package com.dil.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testBasicQueue() throws InterruptedException {
        rabbitTemplate.convertAndSend("basicQueue", "basic message");
        new CountDownLatch(1).await();
    }

    @Test
    public void testWorkQueue() throws InterruptedException {
        rabbitTemplate.convertAndSend("workQueue", "work message");
        new CountDownLatch(1).await();
    }

    @Test
    public void testFanoutQueue() throws InterruptedException {
        String exchangeName = "fanoutExchange";
        rabbitTemplate.convertAndSend(exchangeName, "", "fanout message");
        new CountDownLatch(1).await();
    }

    @Test
    public void testDirectQueue() throws InterruptedException {
        String exchangeName = "directExchange";
        String routingKey = "routeKey2";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, "direct message");
        new CountDownLatch(1).await();
    }

    @Test
    public void testTopicQueue() throws InterruptedException {
        rabbitTemplate.convertAndSend("topicExchange", "topic.x", "通配符模式，消息1");
        rabbitTemplate.convertAndSend("topicExchange", "topic.y.z", "通配符模式，消息2");
        new CountDownLatch(1).await();
    }

    @Test
    public void testSendMap() throws InterruptedException {
        Map<String,Object> msg = new HashMap<>();
        msg.put("name", "Jack");
        msg.put("age", 21);
        rabbitTemplate.convertAndSend("object.queue", msg);
        new CountDownLatch(1).await();
    }
}
