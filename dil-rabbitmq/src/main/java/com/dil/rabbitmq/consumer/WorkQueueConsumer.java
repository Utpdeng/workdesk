package com.dil.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 任务队列
 *      多个消费者消费一个队列（同一条消息只会被一个消费者处理）
 */
@Slf4j
@Component
public class WorkQueueConsumer {

    // 仅有一个消费者能消费消息

    @RabbitListener(queues = "workQueue")
    public void listener(String msg) {
        log.info("消费者1 - 接收队列 [workQueue] 消息: {}", msg);
    }

    @RabbitListener(queues = "workQueue")
    public void listener2(String msg) {
        log.info("消费者2 - 接收队列 [workQueue] 消息: {}", msg);
    }

}
