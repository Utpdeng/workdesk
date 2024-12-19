package com.dil.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 不同的消息被不同的队列消费
 *      - 队列需要指定路由 key (RoutingKey)
 *      - 生产者也需要指定 RoutingKey
 *
 */
@Slf4j
@Component
public class DirectQueueConsumer {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "direct.queue1"),
                    exchange = @Exchange(value = "directExchange", type = ExchangeTypes.DIRECT),
                    key = "routeKey1"
            )
    )
    public void listener01(String msg) {
        log.info("direct.queue1 的消息：{}", msg);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "direct.queue2"),
                    exchange = @Exchange(value = "directExchange", type = ExchangeTypes.DIRECT),
                    key = "routeKey2"
            )
    )
    public void listener02(String msg) {
        log.info("direct.queue2 的消息：{}", msg);
    }

}
