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
    String dead = MQExchange.order + ".queue.order.dead";
    String deadSign = MQExchange.order + ".queue.order.dead.sign";

    /**
    * 路由
    * @params
    * @return
    */
    String keyStatusPayed = "status.payed";

    String keyPayExpire = "pay.expire";
    String keyPayDead = "pay.dead";
    String keyPayDeadSign = "pay.dead.sign";
}
