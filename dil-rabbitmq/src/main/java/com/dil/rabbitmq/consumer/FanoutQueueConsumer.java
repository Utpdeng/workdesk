package com.dil.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 发布订阅模型中：
 *      publisher -->  exchange  --> queue  --> consumer
 *    Exchange:  负责转发消息
 *          Fanout: 广播，将消息交给所有绑定到交换机的队列
 *          Direct: 定向，把消息交给符合指定 routingKey 的队列
 *          Topic: 通配符，把消息交给符合 routing pattern（路由模式） 的队列
 * 广播模型
 *      - 可以有多个队列，每个队列都绑定 Exchange
 *      - 一条消息会被所有订阅的队列都消费
 */
@Slf4j
@Component
public class FanoutQueueConsumer {

    // 消费者1  或 消费者1-1 仅有一个消费者能够消费 queue1 中的消息

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) {
        log.info("消费者1-接收到 Fanout 消息: {}", msg);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue3(String msg) {
        log.info("消费者1-1-接收到 Fanout 消息: {}", msg);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) {
        log.info("消费者2-接收到 Fanout 消息: {}", msg);
    }

    /**
     * 采用注解形式代替配置类
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "fanout.queue3"),
                    exchange = @Exchange(
                            value = "fanoutExchange",
                            type = ExchangeTypes.FANOUT
                    )
            )
    )
    public void listener(String msg) {
        log.info("消费者3-接收到 Fanout 消息: {}", msg);
    }
}
