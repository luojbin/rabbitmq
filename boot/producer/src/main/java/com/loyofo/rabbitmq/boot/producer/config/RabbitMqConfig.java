package com.loyofo.rabbitmq.boot.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author yc
 * @title: RabbitMqConfig
 * @projectName qiuqiu
 * @description: TODO
 * @date 2019/10/22  16:31
 */
@Configuration
public class RabbitMqConfig {

    //1.定义一个 交换器 exchange 直连交换器，精确匹配
    @Bean
    public DirectExchange helloExchange(){
        //创建直连交换器 在rabbitmq 服务器上创建
        return new DirectExchange("helloExchange");
    }
    //创建一个队列,存放路由过来的消息,后一个true 消息持久化 内存硬盘都有
    @Bean
    public Queue helloQueue() {
        return new Queue("helloQueue",true);
    }
    //交换机和队列绑定关系
    @Bean
    public Binding bindingDirectExchange2(Queue helloQueue,DirectExchange helloExchange){
        return BindingBuilder.bind(helloQueue).to(helloExchange).with("helloKey");
    }
}
