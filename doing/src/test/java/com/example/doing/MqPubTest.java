package com.example.doing;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class MqPubTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void hello() {
        String name = "hello";
        String msg = "HELLO MQ.";
        rabbitTemplate.convertAndSend(name, msg);
    }

    @Test
    void fanout() {
        // 交換機 名字
        String exchangeName = "test.fanout";
        String msg = "HELLO MQ TO FANOUT.";
        rabbitTemplate.convertAndSend(exchangeName, "", msg);
    }

    // Direct 发送 Map
    // 记得 要 覆盖 MessageConverter
    // 推荐使用 JSON
    @Test
    void directMap() {
        HashMap<String, Object> src = new HashMap<>();
        src.put("name", "沙粒质");
        String exchange = "test.anno.creat.direct";
        String key = "pink";
        rabbitTemplate.convertAndSend(exchange, key, src);
    }
}
