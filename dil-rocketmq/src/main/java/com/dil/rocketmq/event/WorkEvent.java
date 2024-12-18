package com.dil.rocketmq.event;


import lombok.Data;

import java.util.Date;

@Data
public class WorkEvent extends BaseEvent<String> {

    public static String TOPIC = "guide-rocketmq-topic";

    public static WorkEvent create(String message) {
        WorkEvent workEvent = new WorkEvent();
        workEvent.setId("10001");
        workEvent.setTimestamp(new Date());
        workEvent.setData(message);
        return workEvent;
    }

}
