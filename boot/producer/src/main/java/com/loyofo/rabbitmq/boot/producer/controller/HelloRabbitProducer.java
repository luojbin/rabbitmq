package com.loyofo.rabbitmq.boot.producer.controller;

import com.loyofo.rabbitmq.constant.RabbitConstant;
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

    /**
     * 简单队列/工作模式, 不指定交换机
     * @return
     */
    @GetMapping("/sendSq")
    public String sendSqT() {
        amqpTemplate.convertAndSend(RabbitConstant.SIMPLE_QUEUE,  "直连1 " + new Date().toString());
        amqpTemplate.convertAndSend("directQueue1",  "直连1 " + new Date().toString());
        amqpTemplate.convertAndSend("fanoutQueue3",  "直连1 " + new Date().toString());
        return "简单队列, amqpTemplate直接发送到队列";
    }

    /**
     * 直连模式 发送到直连交换机及指定队列
     * @return
     */
    @GetMapping("/sendDx")
    public String sendDx() {
        for (int i = 0; i < 10; i++) {
            amqpTemplate.convertAndSend("directExchange", "dedq1", "直连1 " + new Date().toString());
            amqpTemplate.convertAndSend("directExchange", "dedq2", "直连2 " + new Date().toString());
            amqpTemplate.convertAndSend("directExchange", "dedq3", "直连3 " + new Date().toString());
        }
        return "直连模式, 三个队列各发10条消息";
    }

    /**
     * 订阅模式 发送到扇形交换机, 不指定路由键
     * @return
     */
    @GetMapping("/sendFx")
    public String sendFx() {
        for (int i = 0; i < 10; i++) {
            amqpTemplate.convertAndSend("fanoutExchange", null, "订阅1 " + new Date().toString());
            amqpTemplate.convertAndSend("fanoutExchange", null, "订阅2 " + new Date().toString());
            amqpTemplate.convertAndSend("fanoutExchange", null, "订阅3 " + new Date().toString());
        }
        return "发送成功";
    }

    @GetMapping("/sendTp")
    public String sendTp() {
        // *.news, 匹配1个
        amqpTemplate.convertAndSend("topicExchange", "usa.news", "美国新闻联播");
        amqpTemplate.convertAndSend("topicExchange", "china.news", "中国新闻联播");
        amqpTemplate.convertAndSend("topicExchange", "usa.NewYork.news", "美国纽约新闻联播");
        amqpTemplate.convertAndSend("topicExchange", "china.Shenzhen.news", "中国深圳新闻联播");

        // #.weather, 匹配任意多个
        amqpTemplate.convertAndSend("topicExchange", "usa.weather", "美国天气预报");
        amqpTemplate.convertAndSend("topicExchange", "china.weather", "中国天气预报");
        amqpTemplate.convertAndSend("topicExchange", "usa.NewYork.weather", "美国纽约天气预报");
        amqpTemplate.convertAndSend("topicExchange", "china.Shenzhen.weather", "中国深圳天气预报");

        return "发送成功";
    }


}