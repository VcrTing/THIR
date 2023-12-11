package com.example.config.mq.queue;

import com.example.config.mq.MQExchange;

public interface MQueueProduct {

    /**
     * 队列
     * @params
     * @return
     */
    String quantity = MQExchange.product + ".queue.product.quantity";

    /**
    * 路由
    * @params
    * @return
    */
    String keyQuantityRemove = "quantity.remove";
}
