package com.dil.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "guide-rocketmq-topic", consumerGroup = "guide-rocketmq-consumer-group")
public class MQConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("接收到MQ消息 {}", message);
    }

}
