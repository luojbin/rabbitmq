package com.loyofo.rabbitmq.origin.consumer;

import com.loyofo.rabbitmq.constant.RabbitConstant;
import com.loyofo.rabbitmq.util.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class C1_SimpleQueue {
    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(RabbitConstant.SIMPLE_QUEUE, false, false, false, null);


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(RabbitConstant.SIMPLE_QUEUE, true, deliverCallback, consumerTag -> {});

    }
}
