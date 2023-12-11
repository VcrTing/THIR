package com.example.doing.mq.exm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
// @Component
public class CompRabbitListener {

    /*
    @RabbitListener(queues = "hello")
    public void listenerHello(String msg) {
        System.out.println(msg);
        log.info("HELLO 消費者 收到消息 = " + msg);
    }

    //
    @RabbitListener(queues = "from.fanout.one")
    public void listenerOne(String msg) {
        System.out.println("FANOUT 1 = " + msg);
        log.info("FANOUT 1 消費者 收到消息 = " + msg);
    }

    @RabbitListener(queues = "from.fanout.two")
    public void listenerTwo(String msg) {
        System.out.println("FANOUT 2 = " + msg);
        log.info("FANOUT 2 消費者 收到消息 = " + msg);
    }
     */
}
