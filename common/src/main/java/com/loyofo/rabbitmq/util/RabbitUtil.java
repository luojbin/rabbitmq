package com.loyofo.rabbitmq.util;

import com.loyofo.rabbitmq.constant.RabbitConstant;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitUtil {
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost(RabbitConstant.HOST);
        //端口
        factory.setPort(RabbitConstant.PORT);
        //设置账号信息，用户名、密码、vhost
        // factory.setVirtualHost("testhost");
        factory.setUsername(RabbitConstant.USERNAME);
        factory.setPassword(RabbitConstant.PASSWORD);
        // 通过工程获取连接
        return factory.newConnection();
    }
}
