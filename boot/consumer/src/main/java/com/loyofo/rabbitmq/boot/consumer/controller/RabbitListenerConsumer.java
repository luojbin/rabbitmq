package com.loyofo.rabbitmq.boot.consumer.controller;

import com.loyofo.rabbitmq.constant.RabbitConstant;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消费者直接关注队列, 不需考虑交换器
 */
@Component
public class RabbitListenerConsumer {

    @RabbitHandler
    @RabbitListener(queues = RabbitConstant.SIMPLE_QUEUE)
    public void sqConsumer(Message message, Channel channel) throws Exception {
        try {
            System.out.println("简单队列" + Thread.currentThread() + "sqConsumer 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    //region 直连队列消费者
    @RabbitHandler
    @RabbitListener(queues = "directQueue1")
    public void dqConsumer1(Message message, Channel channel) throws Exception {
        try {
            System.out.println(">>>>>>>>111111111" + Thread.currentThread() + "dqConsumer1 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
    @RabbitHandler
    @RabbitListener(queues = "directQueue2")
    public void dqConsumer2(Message message, Channel channel) throws Exception {
        try {
            System.out.println(">>>>>>>>22222222" + Thread.currentThread() + "dqConsumer2 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
    @RabbitHandler
    @RabbitListener(queues = "directQueue3")
    public void dqConsumer3(Message message, Channel channel) throws Exception {
        try {
            System.out.println(">>>>>>>>33333333" + Thread.currentThread() + "dqConsumer3 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
    //endregion

    //region 订阅模式消费者
    @RabbitHandler
    @RabbitListener(queues = "fanoutQueue1")
    public void fqConsumer1(Message message, Channel channel) throws Exception {
        try {
            System.out.println(">>>>>>>>111111111" + Thread.currentThread() + "fqConsumer1 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
    @RabbitHandler
    @RabbitListener(queues = "fanoutQueue2")
    public void fqConsumer2(Message message, Channel channel) throws Exception {
        try {
            System.out.println(">>>>>>>>22222222222" + Thread.currentThread() + "fqConsumer2 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
    @RabbitHandler
    @RabbitListener(queues = "fanoutQueue3")
    public void fqConsumer3(Message message, Channel channel) throws Exception {
        try {
            System.out.println(">>>>>>>>333333333333" + Thread.currentThread() + "fqConsumer3 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
    //endregion

    //region 主题匹配模式消费者
    @RabbitHandler
    @RabbitListener(queues = "usaQueue")
    public void usaQueue(Message message, Channel channel) throws Exception {
        try {
            System.out.println(">>>>>>>>>>>>>>" + Thread.currentThread() + "usaQueue 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
    @RabbitHandler
    @RabbitListener(queues = "chinaQueue")
    public void chinaQueue(Message message, Channel channel) throws Exception {
        try {
            System.out.println(">>>>>>>>>>>>>>" + Thread.currentThread() + "chinaQueue 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
    @RabbitHandler
    @RabbitListener(queues = "newsQueue")
    public void newsQueue(Message message, Channel channel) throws Exception {
        try {
            System.out.println(">>>>>>>>>>>>>>" + Thread.currentThread() + "newsQueue 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
    @RabbitHandler
    @RabbitListener(queues = "weatherQueue")
    public void weatherQueue(Message message, Channel channel) throws Exception {
        try {
            System.out.println(">>>>>>>>>>>>>>" + Thread.currentThread() + "weatherQueue 收到消息:" +message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
    //endregion
}