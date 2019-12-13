package com.loyofo.rabbitmq.boot.consumer.config;

import com.loyofo.rabbitmq.constant.RabbitConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author luojbin
 * @version 1.0
 * @date 2019/12/13 18:27
 */
@Configuration
public class RabbitMqConfig {

    // 1. 简单队列 1-1
    // 2. 工作队列 1-n
    @Bean
    public Queue simpleQueue() {
        return new Queue(RabbitConstant.SIMPLE_QUEUE,true);
    }

    //region 3.直连模式 p-de-nq-c
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }
    @Bean
    public Queue directQueue1() {
        return new Queue("directQueue1",true);
    }
    @Bean
    public Queue directQueue2() {
        return new Queue("directQueue2",true);
    }
    @Bean
    public Queue directQueue3() {
        return new Queue("directQueue3",true);
    }
    @Bean
    public Binding bindingDirectQueue1(@Qualifier("directQueue1") Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("dedq1");
    }
    @Bean
    public Binding bindingDirectQueue2(@Qualifier("directQueue2") Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("dedq2");
    }
    @Bean
    public Binding bindingDirectQueue3(@Qualifier("directQueue3") Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("dedq3");
    }
    //endregion

    //region 4.订阅模式 p-fe-nq-c
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutQueue1",true);
    }
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanoutQueue2",true);
    }
    @Bean
    public Queue fanoutQueue3() {
        return new Queue("fanoutQueue3",true);
    }
    @Bean
    public Binding bindingFanoutQueue1(@Qualifier("fanoutQueue1") Queue queue, FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }
    @Bean
    public Binding bindingFanoutQueue2(@Qualifier("fanoutQueue2") Queue queue, FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }
    @Bean
    public Binding bindingFanoutQueue3(@Qualifier("fanoutQueue3") Queue queue, FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }
    //endregion

    //region 5.主题匹配模式 p-te-nq-c
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }
    @Bean
    public Queue usaQueue() {
        return new Queue("usaQueue",true);
    }
    @Bean
    public Queue chinaQueue() {
        return new Queue("chinaQueue",true);
    }
    @Bean
    public Queue newsQueue() {
        return new Queue("newsQueue",true);
    }
    @Bean
    public Queue weatherQueue() {
        return new Queue("weatherQueue",true);
    }
    @Bean
    public Binding bindingUsaQueue1(@Qualifier("usaQueue") Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("usa.*");
    }
    @Bean
    public Binding bindingTopicQueue1(@Qualifier("chinaQueue") Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("china.#");
    }
    @Bean
    public Binding bindingTopicQueue2(@Qualifier("newsQueue") Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.news");
    }
    @Bean
    public Binding bindingTopicQueue3(@Qualifier("weatherQueue") Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("#.weather");
    }
    //endregion
}
