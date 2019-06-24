package com.tamqp;

import com.tamqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired

    private AmqpAdmin amqpAdmin;

    @Test
    public void contextLoads() {
        /**
         * 单播：发送消息
         */
        //message需要自己定义
        //rabbitTemplate.send(exchange,routeKey,message);
        //讲话并且发送
        //默认被当成消息体，值需要传入要发送的对象，自动序列化保存发送给Rabbit
        Map<String,Object> map=new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("hellow",123,true));
        //对象被默认序列化发送出去
        rabbitTemplate.convertAndSend("exchange.direct","gulixueyuan.news",new Book("聊斋","蒲松龄"));
    }
    /***
     * 接收数据:一旦接收了，queues中的数据就没了
     * 如何将数据转化为json发送出去:配置MyAMQPConfig
     */
    @Test
    public void test(){
        Object receive = rabbitTemplate.receive("gulixueyuan.news");
        System.out.println(receive.getClass());
        System.out.println(receive);
    }
    /***
     * 广播的模式
     */
    @Test
    public void test1(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
    }

    @Test
    public void test5(){
        //创建exchanges
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("创建完成");
        //创建队列
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
        //创建绑定规则:目的地、类型、路由建、内容
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));
        amqpAdmin.deleteQueue("amqpadmin.queue"); //删除绑定
    }
}
