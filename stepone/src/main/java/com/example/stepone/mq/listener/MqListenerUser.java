package com.example.stepone.mq.listener;

import com.example.config.mq.MQExchange;
import com.example.config.mq.MQGlobalRouterKey;
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
public class MqListenerUser {

    @Autowired
    UserService userService;

    /**
     * 去除 餘額
     * @params
     * @return
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = MQExchange.user, type = ExchangeTypes.TOPIC),
            value = @Queue(name = MQueueUser.money, durable = "true"),
            key = MQueueUser.keyMoneyRemove
    ))
    public void listenerMoney(Order order) {
        log.debug(MQueueUser.money + " 接收的，用於處理訂單金額 = " + order.getPrice());
        userService.removeMoney(order.getUser_sql_id(), order.getPrice());
    }
}
