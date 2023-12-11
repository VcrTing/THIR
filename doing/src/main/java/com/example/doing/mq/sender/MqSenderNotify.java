package com.example.doing.mq.sender;

import com.example.config.mq.MQExchange;
import com.example.config.mq.queue.MQueueUser;
import com.example.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqSenderNotify {

    @Autowired
    RabbitTemplate rabbitTemplate;

    // 通知
    public void notify(Order order) {
        rabbitTemplate.convertAndSend(MQExchange.user, MQueueUser.keyAnyNotify, order);
    }
}
