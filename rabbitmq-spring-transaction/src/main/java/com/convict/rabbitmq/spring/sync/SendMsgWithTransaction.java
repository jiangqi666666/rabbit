package com.convict.rabbitmq.spring.sync;

import com.convict.rabbitmq.spring.sync.callback.MsgSendReturnCallback;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 发送消息确认
 */
public class SendMsgWithTransaction {

    public static void main(String[] args) throws Exception{
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-producer-sync.xml");
        final RabbitTemplate template = ctx.getBean(RabbitTemplate.class);


        RabbitTransactionManager transactionManager = new RabbitTransactionManager(ctx.getBean(CachingConnectionFactory.class));
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);


        //由于有spring的事务参与，而发送操作在提交事务时，是不允许除template的事务有其他事务的参与，所以这里不会提交
        //队列中就没有消息，所以在channel.basicGet时命令返回的是basic.get-empty(队列中没有消息时),而有消息时，返回basic.get-ok
        String result = transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus status) {
                template.convertAndSend("hello world");
                return null;
            }
        });

       // ctx.destroy();
    }
}
