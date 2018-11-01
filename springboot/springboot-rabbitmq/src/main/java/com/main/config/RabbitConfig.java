package com.main.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置在RabbitMQ队列,如果你的Rabbit里面没有这个名称的队列就必须配置这个Bean
 */
@Configuration
public class RabbitConfig {

    public static final String JACK_QUEUE = "simple-demo";

    @Bean
    public Queue queue(){
        //队列名称
        return new Queue(JACK_QUEUE);
    }
}
