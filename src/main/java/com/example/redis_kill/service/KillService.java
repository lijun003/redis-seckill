package com.example.redis_kill.service;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KillService {
    @Autowired
    private RedisTemplate redisTemplate;

    private static String KEY = "inventory";

    @PostConstruct
    public void init() {
        redisTemplate.opsForList().getOperations().delete(KEY);
        for (int i = 0; i < 10; i++) {
            redisTemplate.opsForList().rightPush(KEY, 1);
        }
        log.info("Inventory size: {}", redisTemplate.opsForList().size(KEY));
    }

    public void secKill() {
        String tName = Thread.currentThread().getName();
        System.out.println(tName + "join");
        Object inventory = redisTemplate.opsForList().leftPop(KEY);
        if (null != inventory) {
            String userId = UUID.randomUUID().toString();
            Long result = redisTemplate.opsForSet().add("userIds", userId);
            if (result > 0) {
                log.info("Sec kill success for: {}", userId);
            } else {
                log.warn("Duplicate user id: {}", userId);
            }
        } else {
            log.info("Sec kill failed");
        }
    }
}
