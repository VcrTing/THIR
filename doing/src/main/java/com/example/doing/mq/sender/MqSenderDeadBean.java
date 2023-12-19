package com.example.doing.mq.sender;

import com.example.config.mq.MQExchange;
import com.example.config.mq.queue.MQueueOrder;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// 这个 队列 没人 消费,
// 就会 变成 死信
@Configuration
public class MqSenderDeadBean {

    // 30 s 后 死信，订单付款 30 s 之后未付款则 取消订单
    //
    int TTL = 30_000;

    @Bean
    public DirectExchange ttlExc() {
        return new DirectExchange(MQExchange.orderDeadSign, true, false);
    }

    @Bean
    public Queue ttlQueue() {
        return QueueBuilder.durable(MQueueOrder.deadSign)
                .ttl(TTL)
                .deadLetterExchange(MQExchange.orderDead)
                .deadLetterRoutingKey(MQueueOrder.keyPayDead)
                .build();
    }

    @Bean
    public Binding ttlBinding() {
        System.out.println("死信 队列 生成");
        return BindingBuilder.bind(ttlQueue()).to(ttlExc()).with(MQueueOrder.keyPayDeadSign);
    }

}
