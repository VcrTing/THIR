package com.example.doing.mq.fanout;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MqAnnoCreatDemo {

    final static String exchangeName = "test.anno.creat.direct";
    final static String queueName = "test.anno.creat.direct.queue.1";

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = queueName, durable = "true"),
            exchange = @Exchange(name = exchangeName, type = ExchangeTypes.DIRECT),
            key = { "pink", "skyblue" }
    ))
    public void listener(HashMap<String, Object> msg) {
        System.out.println(queueName + " 接收的 = " + msg);
    }
}
