package com.main.controller;

import com.main.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {


    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send(){
        rabbitTemplate.convertAndSend(RabbitConfig.JACK_QUEUE,"HelloWorld");
        return "Success";
    }

    @RabbitListener(queues = {RabbitConfig.JACK_QUEUE})
    public void receive(String msg){
        log.info("Message : " + msg);
    }
}
