package com.loyofo.rabbitmq.boot.consumer.controller;

import org.springframework.stereotype.Component;

@Component
public class HelloRabbitConsumer {


    // @RabbitHandler
    // @RabbitListener(queues = "helloRabbit2")
    // public void process2(User user, Message message, Channel channel) throws Exception {
    //     try {
    //         //告诉服务器消费完成 可以删除
    //         System.out.println(user);
    //         channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    //     } catch (IOException e) {
    //         channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    //     }
    //
    // }
}