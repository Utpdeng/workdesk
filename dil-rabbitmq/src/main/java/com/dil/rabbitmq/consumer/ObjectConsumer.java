package com.dil.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: "xx"
 * @Date: 2024/12/19
 */
@Component
@Slf4j
public class ObjectConsumer {

    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String, Object> msg) {
        log.info("object.queue的消息：{}", msg);
    }
}
