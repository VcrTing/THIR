package com.example.config.mq;

public interface MQExchange {

    String user = "test.third.topic.user";

    String order = "test.third.topic.order";

    String orderDelay = "test.third.delay.topic.order";
    String orderDead = "test.third.dead.topic.order";
    String orderDeadSign = "test.third.dead.topic.order.sign";

    String product = "test.third.topic.product";

}
