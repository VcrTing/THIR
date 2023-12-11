package com.example.doing.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MqConf {

    @Bean
    public MessageConverter jsonConverter() {

        log.debug("Jackson2JsonMessageConverter 配置 成功");
        Jackson2JsonMessageConverter jjm = new Jackson2JsonMessageConverter();

        // 给 消息 生成 唯一 ID
        // MessageProperties 接口，底层用的 uuid

        // 用 唯一 ID 确保 消息 不会 重复 提交
        jjm.setCreateMessageIds(true);
        return jjm;
    }

    /*
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 提醒 對接 與 路由 是否 成功的
        rabbitTemplate.setReturnsCallback((ReturnedMessage msg) -> {
            log.debug("Rabbit 消息 callback => exchange:{}, key:{}, code:{}, msg:{}",
                    msg.getExchange(), msg.getRoutingKey(), msg.getReplyCode(), msg.getMessage());
        });
    }
     */
}
