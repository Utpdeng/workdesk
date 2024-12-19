package com.dil.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 简单队列消费
 */
@Slf4j
@Component
public class BasicQueueConsumer {

    /**
     * queuesToDeclare 有则使用队列，没有则创建队列；若直接使用 queues，若队列不存在则出错
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "basicQueue"))
    public void listener(String msg) {
        log.info("接收队列 [simpleQueue] 消息: {}", msg);
    }

}
