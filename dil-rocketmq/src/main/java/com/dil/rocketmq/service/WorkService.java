package com.dil.rocketmq.service;

import com.dil.rocketmq.event.WorkEvent;
import com.dil.rocketmq.producer.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class WorkService {

    @Resource
    private EventPublisher eventPublisher;

    public void work(String msg) {
        log.info("service work msg: {}", msg);
        eventPublisher.publish(WorkEvent.TOPIC, WorkEvent.create(msg));
    }

}
