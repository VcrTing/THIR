package com.example.stepone.mq.listener;

import com.example.config.mq.MQExchange;
import com.example.config.mq.MQGlobalRouterKey;
import com.example.config.mq.queue.MQueueOrder;
import com.example.config.mq.queue.MQueueUser;
import com.example.entity.Order;
import com.example.stepone.moduie.sys.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqListenerNotify {

    @Autowired
    UserService userService;

    /**
     * 去除 餘額
     * @params
     * @return
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = MQExchange.user, type = ExchangeTypes.TOPIC),
            value = @Queue(name = MQueueUser.notify, durable = "true"),
            key = MQueueUser.keyAnyNotify
    ))
    public void listenerNotify(Order order) {
        log.debug(MQueueUser.notify + " 接收的，用於提醒 = " + order.getNotify());
        userService.sendEmailMsg(order.getUser_sql_id(), order.getNotify());
    }
}
