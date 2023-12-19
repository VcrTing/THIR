package com.example.stepone.scheduler.classic;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskClassic {

    // 每 15 秒 執行 一次
    @Scheduled(fixedDelay = 15_000)
    public void classicTask() {
        System.out.println("每15秒的 定時任務。");
    }
}
