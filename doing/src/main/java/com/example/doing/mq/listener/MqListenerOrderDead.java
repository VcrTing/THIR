package com.example.doing.mq.listener;

import com.example.config.mq.MQExchange;
import com.example.config.mq.queue.MQueueOrder;
import com.example.doing.moduie.order.MqOrderService;
import com.example.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 死信处理
@Slf4j
@Component
public class MqListenerOrderDead {

    @Autowired
    MqOrderService orderService;

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = MQExchange.orderDead, type = ExchangeTypes.TOPIC),
            value = @Queue(name = MQueueOrder.dead, durable = "true"),
            key = MQueueOrder.keyPayDead
    ))
    public void listenerPayExpire(Order order) {

        log.debug(MQueueOrder.dead + " 死信队列 " + order);
        orderService.payedExpire(order.getId());
    }
}
