package com.example.config.mq.queue;

import com.example.config.mq.MQExchange;

public interface MQueueOrder {

    /**
     * 购买 业务 监听
     * @params
     * @return
     */
    String buy = MQExchange.order + ".queue.order.buy";

    String expire = MQExchange.order + ".queue.order.expire";

    /**
    * 路由
    * @params
    * @return
    */
    String keyStatusPayed = "status.payed";

    String keyPayExpire = "pay.expire";
}
