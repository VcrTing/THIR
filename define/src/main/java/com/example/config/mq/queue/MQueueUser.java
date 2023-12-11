package com.example.config.mq.queue;

import com.example.config.mq.MQExchange;

public interface MQueueUser {

    /**
     * 队列
     * @params
     * @return
     */
    // 金钱
    String money = MQExchange.user + ".queue.user.money";

    // 提醒
    String notify = MQExchange.user + ".queue.user.notify";

    /**
    * 路由
    * @params
    * @return
    */

    // 移除 金钱
    String keyMoneyRemove = "money.remove";

    // 各类 提醒
    String keyAnyNotify = "#.notify";
}
