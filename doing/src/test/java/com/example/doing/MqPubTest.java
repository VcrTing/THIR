package com.example.doing;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;


@Slf4j
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

    // 發送 非 持久 化的 消息
    // SPRING 默認 消息 持久化
    // 而且还是 延迟的 消息
    @Test
    void msg() {
        Message message = MessageBuilder
                .withBody("HELLO".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                .build();
        String exchangeName = "test.fanout";
        rabbitTemplate.convertAndSend(exchangeName, "", message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(5000); // 延迟 5 s
                // 延迟 消息 只 适用于 延迟 时间短的
                // 因为 他内部会 维护 一个 时间 ，对于 高 并发场景 有点 吃 性能
                // 延迟 信息 没人 消费，会堆积，所以 MQ 很快 就 满了
                return message;
            }
        });
    }

    // 回調
    @Test
    void bk() throws InterruptedException {

        CorrelationData cd = new CorrelationData();
        // 回調
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("消息回調 失敗", ex);
            }

            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                log.error("收到 CorrelationData.Confirm =>");

                if (result.isAck()) {
                    log.error("消息 成功， ACK");
                } else {
                    log.error("消息 發送 失敗 ，收到 NACK => {}", result.getReason());
                }
            }
        });

        HashMap<String, Object> src = new HashMap<>();
        src.put("name", "沙粒质");
        String exchange = "test.anno.creat.direct";
        String key = "pink";
        rabbitTemplate.convertAndSend(exchange, key, src, cd);

        Thread.sleep(2000);
    }
}
