package com.dil.guava.listener;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ListenerTest {

    @Test
    public void test_eventbus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new Listener());

        eventBus.post("消息总线，订单号：100001");
    }

    static class Listener {
        @Subscribe
        public void handleEvent(String orderId) {
            log.info("测试结果：{}", orderId);
        }
    }

}
