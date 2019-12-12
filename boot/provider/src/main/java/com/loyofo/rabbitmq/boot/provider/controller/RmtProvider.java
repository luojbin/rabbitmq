package com.loyofo.rabbitmq.boot.provider.controller;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("rmt")
@RestController
public class RmtProvider {
    @Autowired
    private RabbitMessagingTemplate messagingTemplate;

    @PostMapping("sendDemo")
    public String send(String message) {
        messagingTemplate.convertAndSend("demoQueue", message);
        return "手动消息 发送成功";
    }

    @PostMapping("sendStr")
    public String sendStr(String message) {
        messagingTemplate.convertAndSend("strQueue", message);
        return "str 发送成功";
    }
    @PostMapping("sendMap")
    public String sendMap(String message) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("str", message);
        map.put("integer", 123);
        map.put("boolean", false);
        messagingTemplate.convertAndSend("mapQueue", map);
        return "map 发送成功";
    }

    // @PostMapping("sendMapAndReply")
    // public String sendMapAndReply() {
    //     Map<String, Object> header = new HashMap<>();
    //     header.put("jms_replyTo", new Queue("reply"));
    //     messagingTemplate.convertAndSend("replyQueue", "来自 jmt 的消息", header);
    //     return "map 发送成功";
    // }

    @PostMapping("sendDlq")
    public String sendDlq(String message) {
        messagingTemplate.convertAndSend("retry", message);
        return "jt 消息重发与死信 发送成功";
    }

}
