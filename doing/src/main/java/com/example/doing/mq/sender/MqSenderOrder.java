package com.example.doing.mq.sender;

import com.example.config.mq.MQExchange;
import com.example.config.mq.MQGlobalRouterKey;
import com.example.config.mq.queue.MQueueOrder;
import com.example.config.mq.queue.MQueueProduct;
import com.example.config.mq.queue.MQueueUser;
import com.example.entity.Order;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqSenderOrder {

    @Autowired
    RabbitTemplate rabbitTemplate;

    // 移除 庫存
    public void orderRemoveQuantity(Order order) {
        rabbitTemplate.convertAndSend(MQExchange.product, MQueueProduct.keyQuantityRemove, order);
    }

    // 移除 餘額
    public void orderRemoveMoney(Order order) {
        rabbitTemplate.convertAndSend(MQExchange.user, MQueueUser.keyMoneyRemove, order);
    }

    // 修改 狀態 已付款
    public void orderPayedStatus(Order order) {
        rabbitTemplate.convertAndSend(MQExchange.order, MQueueOrder.keyStatusPayed, order);
    }

    // 检查 未付款 过期 订单
    public void orderCheckExpire(Order order) {
        rabbitTemplate.convertAndSend(MQExchange.order, MQueueOrder.keyPayExpire, order, message -> {
            message.getMessageProperties().setDelay(1000 * 30); // 30 秒
            return message;
        });
    }
}
