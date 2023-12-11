package com.example.doing.mq.exm;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import java.util.HashMap;

// @Component
public class MqAnnoCreatDemo {
    /*
    final static String exchangeName = "test.anno.creat.direct";
    final static String queueName = "test.anno.creat.direct.queue.1";

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = queueName, durable = "true"),
            exchange = @Exchange(name = exchangeName, type = ExchangeTypes.DIRECT, delayed = "true"),
            key = { "pink", "skyblue" },
            arguments = @Argument(name = "x-queue-mode", value = "lazy")
    ))
    public void listener(HashMap<String, Object> msg) {

        System.out.println(queueName + " 接收的延迟的MSG = " + msg);

        // throw new MessageConversionException("REJECT");
        throw new RuntimeException("NACK");
    }

     */
}
