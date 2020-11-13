package com.convict.rabbitmq.spring.sync;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Recv {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("applicationContext-consumer-sync.xml");
    }

}
