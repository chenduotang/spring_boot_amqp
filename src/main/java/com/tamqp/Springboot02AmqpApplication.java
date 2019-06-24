package com.tamqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 * 1、RabbitAutoConfiguration
 * 2、连接工厂配置了，获取获取连接ConnectionFactory
 * 3、RabbitPropreties 封装了RabbitMQ的配置
 * 4、RabbitTemplate：给MQ发送和接收消息
 * 5、AMqpAdmin：是RabbitMQ系统管理组件
 * 6、@EnableRabbit+ @RabbitListener实现监听内容
 */
@SpringBootApplication
@EnableRabbit//开启基于注解的RabbitMQ
public class Springboot02AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02AmqpApplication.class, args);
    }

}
