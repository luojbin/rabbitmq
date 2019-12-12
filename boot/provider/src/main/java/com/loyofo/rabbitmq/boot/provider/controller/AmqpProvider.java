package com.loyofo.rabbitmq.boot.provider.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("jt")
@RestController
public class AmqpProvider {
    @Autowired
    private AmqpTemplate template;

    @PostMapping("sendDemo")
    public String send(String message) {
        template.convertAndSend("demoQueue", message);
        return "手动消息 发送成功";
    }

    @PostMapping("sendStr")
    public String sendStr(String message) {
        template.convertAndSend("strQueue", message);
        return "str 发送成功";
    }
    @PostMapping("sendMap")
    public String sendMap(String message) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("str", message);
        map.put("integer", 123);
        map.put("boolean", false);
        template.convertAndSend("mapQueue", map);
        return "map 发送成功";
    }

    // @PostMapping("sendMapAndReply")
    // public String sendMapAndReply() {
    //     template.convertAndSend("replyQueue", "来自 jt 的消息", message1 -> {
    //         message1.setJMSReplyTo(new ActiveMQQueue("reply"));
    //         return message1;
    //     });
    //     return "map 发送成功";
    // }

    @PostMapping("sendDlq")
    public String sendDlq(String message) {
        template.convertAndSend("retry", message);
        return "jt 消息重发与死信 发送成功";
    }

}
