package com.example.redis_kill.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis_kill.service.KillService;

@RestController
public class SecKillController {

    @Autowired
    private KillService killService;

    @GetMapping("kill")
    public void secKill() {
        killService.secKill();
    }
}