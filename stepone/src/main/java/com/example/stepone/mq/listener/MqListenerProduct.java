package com.example.stepone.mq.listener;

import com.example.config.mq.MQExchange;
import com.example.config.mq.MQGlobalRouterKey;
import com.example.config.mq.queue.MQueueProduct;
import com.example.entity.Order;
import com.example.stepone.moduie.product.ProductService;
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
public class MqListenerProduct {

    @Autowired
    ProductService productService;

    /**
     * 去除 庫存
     * @params
     * @return
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MQueueProduct.quantity, durable = "true"),
            exchange = @Exchange(name = MQExchange.product, type = ExchangeTypes.TOPIC),
            key = MQueueProduct.keyQuantityRemove
    ))
    public void listenerQuantity(Order order) {
        log.debug(MQueueProduct.quantity + " 接收的，用於處理訂單庫存 = " + order.getBuy_quantity());
        productService.removeQuantity(order.getProduct_sql_id(), order.getBuy_quantity());
    }
}
