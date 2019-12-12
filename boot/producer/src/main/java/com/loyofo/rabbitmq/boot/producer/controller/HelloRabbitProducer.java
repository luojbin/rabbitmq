package com.loyofo.rabbitmq.boot.producer.controller;

import com.loyofo.rabbitmq.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("producer")
public class HelloRabbitProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/send")
    public String send() {
        String context = "hello" + new Date();
        System.out.println("producer: " + context);
        User user = new User();
        user.setAge(11);
        user.setLeader(false);
        user.setName("张三");
        amqpTemplate.convertAndSend("helloExchange","helloKey", user);
        return "发送成功";
    }
}