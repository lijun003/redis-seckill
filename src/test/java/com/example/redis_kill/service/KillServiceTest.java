package com.example.redis_kill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class KillServiceTest {
    @Autowired
    private KillService killService;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Test
    public void testSecKill() {

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    killService.secKill();
                }
            } ).start();
        }

        Long size = redisTemplate.opsForSet().size("userIds");
        log.info("Sec kill user count: {}", size);
    }


}