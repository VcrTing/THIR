package com.example.doing.mq.listener;

import com.example.config.mq.MQExchange;
import com.example.config.mq.queue.MQueueOrder;
import com.example.doing.moduie.order.MqOrderService;
import com.example.entity.Order;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqListenerOrderExpired {

    @Autowired
    MqOrderService orderService;

    // 更改 狀態
    @RabbitListener(bindings = @QueueBinding(
        exchange = @Exchange(name = MQExchange.order, type = ExchangeTypes.TOPIC),
        value = @Queue(name = MQueueOrder.expire, durable = "true"),
        key = MQueueOrder.keyPayExpire,
        arguments = @Argument(name = "x-queue-mode", value = "lazy")
    ))
    public void listenerPayExpire(Order order) {
        System.out.println(MQueueOrder.expire + " 接收的，用於處理訂單过期 = " + order);
        orderService.payedExpire(order.getId());
    }
}
