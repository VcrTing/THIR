package com.example.stepone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling // 定时任务
@EnableTransactionManagement
public class SteponeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SteponeApplication.class, args);
    }

}
