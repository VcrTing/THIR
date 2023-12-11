package com.example.doing.mq.exm;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

// @Configuration
public class MqSimpleCreatConf {
    /*
    String fanoutName = "test.fanout.1";
    String directKey = "rk";

    @Bean
    public DirectExchange exchange() {
        return ExchangeBuilder.directExchange(fanoutName).delayed().build();
    }

    @Bean
    public Queue queue() {
        // durable 持久化
        // nondurable 非持久化
        // lazy = LazyQueue
        return QueueBuilder.durable(fanoutName + ".queue.1").lazy().build();
    }

    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(directKey);
    }
     */
}
