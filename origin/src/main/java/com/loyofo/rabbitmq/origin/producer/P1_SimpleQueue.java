package com.loyofo.rabbitmq.origin.producer;

import com.loyofo.rabbitmq.constant.RabbitConstant;
import com.loyofo.rabbitmq.util.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class P1_SimpleQueue {


    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(RabbitConstant.SIMPLE_QUEUE, false, false, false, null);
        // 消息内容
        String message = "Hello World!";
        channel.basicPublish("", RabbitConstant.SIMPLE_QUEUE, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
