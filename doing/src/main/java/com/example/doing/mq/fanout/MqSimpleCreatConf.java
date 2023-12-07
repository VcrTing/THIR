package com.example.doing.mq.fanout;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class MqSimpleCreatConf {
    String fanoutName = "test.fanout.1";
    String directKey = "rk";
    @Bean
    public DirectExchange exchange() {
        return ExchangeBuilder.directExchange(fanoutName).build();
    }

    @Bean
    public Queue queue() {
        // durable 持久化
        // nondurable 非持久化
        return QueueBuilder.durable(fanoutName + ".queue.1").build();
    }

    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(directKey);
    }
}
