package com.convict.rabbitmq.spring.sync;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Send {

    public static void main(String[] args) throws Exception{
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-producer-sync.xml");
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        //可以设置发送消息的routing key
//        template.setRoutingKey("user.error");
        String msg;
        for(int i=0;i<3;i++){
            msg = "Hello World!"+i;
            template.convertAndSend(msg);
            System.out.println(msg);
            Thread.sleep(1000);
        }
        ctx.destroy();
    }
}
