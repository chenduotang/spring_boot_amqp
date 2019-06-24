package com.tamqp.service;

import com.tamqp.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @RabbitListener(queues = "gulixueyuan.news")//在启动类开始注解，监听它的消息
    public void receive(Book book){
        System.out.println("收到消息:"+book);
    }
}
