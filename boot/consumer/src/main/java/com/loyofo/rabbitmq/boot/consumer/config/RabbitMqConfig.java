package com.loyofo.rabbitmq.boot.consumer.config;

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
    public DirectExchange directExchange2(){
        //创建直连交换器 在rabbitmq 服务器上创建
        return new DirectExchange("x2");
    }
    //创建一个队列,存放路由过来的消息,后一个true 消息持久化 内存硬盘都有
    @Bean
    public Queue kinsonQueue2() {
        return new Queue("helloRabbit2",true);
    }
    //交换机和队列绑定关系
    @Bean
    public Binding bindingDirectExchange2(Queue kinsonQueue2,DirectExchange directExchange2){
        return BindingBuilder.bind(kinsonQueue2).to(directExchange2).with("routkey2");
    }


//     @Bean
//     public DirectExchange sendDynamicExchange(){
//         //创建直连交换器 在rabbitmq 服务器上创建
//         return new DirectExchange(RabbitConstant.SendDynamicExchange);
//     }
//     //创建一个队列,存放路由过来的消息,后一个true 消息持久化 内存硬盘都有
//     @Bean
//     public Queue sendDynamicQuene() {
//         return new Queue(RabbitConstant.SendDynamicQuene,true);
//     }
//     //交换机和队列绑定关系
//     @Bean
//     public Binding bindingDirectSendDynamic(Queue sendDynamicQuene,DirectExchange sendDynamicExchange){
//         return BindingBuilder.bind(sendDynamicQuene).to(sendDynamicExchange).with(RabbitConstant.SendDynamicRout);
//     }
//
//
//     //3.不需要路由匹配，创建一个交换器，两个队列,并把它们绑定
//     //==========================================================================
//     @Bean
//     public FanoutExchange fanoutExchange(){
//         return new FanoutExchange("fanoutExchange2");
//     }
//     @Bean
//     public Queue q2() {
//         return new Queue("q2",true);
//     }
//     @Bean
//     public Queue q3() {
//         return new Queue("q3",true);
//     }
//     @Bean
//     public Binding bindingFanoutExchange(Queue q2,FanoutExchange fanoutExchange){
//         return  BindingBuilder.bind(q2).to(fanoutExchange);
//     }
//     @Bean
//     public Binding bindingFanoutExchange2(Queue q3,FanoutExchange fanoutExchange){
//         return  BindingBuilder.bind(q3).to(fanoutExchange);
//     }
//
//
// //=============================================================================
// //3.模糊匹配
//     @Bean
//     public TopicExchange topicExchange(){
//         return new TopicExchange("exchange3");
//     }
//     @Bean
//     public Queue q4() {
//         return new Queue("q4",true);
//     }
//     @Bean
//     public Queue q5() {
//         return new Queue("q5",true);
//     }
//     //# 匹配多个  * 匹配单个
//     @Bean
//     public Binding bindingTopicExchange(Queue q4,TopicExchange topicExchange){
//         return  BindingBuilder.bind(q4).to(topicExchange).with("*.Q.*");
//     }


}
