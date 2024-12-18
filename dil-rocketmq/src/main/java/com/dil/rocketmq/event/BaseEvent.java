package com.dil.rocketmq.event;

import lombok.Data;

import java.util.Date;


@Data
public class BaseEvent<T> {

    private String id;
    private Date timestamp;
    private T data;

}

