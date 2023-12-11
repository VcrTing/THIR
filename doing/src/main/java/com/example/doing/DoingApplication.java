package com.example.doing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DoingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoingApplication.class, args);
    }

}
