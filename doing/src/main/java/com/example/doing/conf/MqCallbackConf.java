package com.example.doing.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MqCallbackConf implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        RabbitTemplate rt = applicationContext.getBean(RabbitTemplate.class);

        // 提醒 對接 與 路由 是否 成功的
        rt.setReturnsCallback((ReturnedMessage msg) -> {
            log.debug("Rabbit 消息 callback => exchange:{}, key:{}, code:{}, msg:{}",
                    msg.getExchange(), msg.getRoutingKey(), msg.getReplyCode(), msg.getMessage());
        });

        log.debug("setReturnsCallback success.");
    }
}
