package com.dil.redisson.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class OrderService {

    @Resource
    private IRedisService redissonService;

    public void createOrder(String sku) {
        long decrCount = redissonService.decr(sku);
        if (decrCount < 0) {
            return;
        }
        String lockKey = sku.concat("_lock").concat(String.valueOf(decrCount));
        RLock lock = redissonService.getLock(lockKey);
        if (lock.isLocked()) {
            log.info("Lock already exists");
        }
        lock.lock();
        try {
            log.info("do service");
        } finally {
            lock.unlock();
        }
    }

}
