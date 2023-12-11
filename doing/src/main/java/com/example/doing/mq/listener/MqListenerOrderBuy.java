package com.example.doing.mq.listener;

import com.example.config.mq.MQExchange;
import com.example.config.mq.queue.MQueueOrder;
import com.example.doing.moduie.order.MqOrderService;
import com.example.entity.Order;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqListenerOrderBuy {

    @Autowired
    MqOrderService orderService;

    // 更改 狀態
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = MQExchange.order, type = ExchangeTypes.TOPIC),
            value = @Queue(name = MQueueOrder.buy), // , durable = "true"
            key = MQueueOrder.keyStatusPayed
    ))
    public void listenerPayedStatus(Order order) {
        System.out.println(MQueueOrder.buy + " 接收的，用於處理訂單狀態 已付款 = " + order);
        orderService.statusPayed(order);
    }
}
