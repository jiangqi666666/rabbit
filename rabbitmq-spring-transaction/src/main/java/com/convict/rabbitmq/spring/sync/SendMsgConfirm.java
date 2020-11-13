package com.convict.rabbitmq.spring.sync;

import com.convict.rabbitmq.spring.sync.callback.MsgSendConfirmCallBack;
import com.convict.rabbitmq.spring.sync.callback.MsgSendReturnCallback;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 发送消息确认
 */
public class SendMsgConfirm {

    public static void main(String[] args) throws Exception{
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-producer-sync.xml");
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);

        // 每个rabbitTemplate只能有一个confirm-callback和return-callback
        //发送消息到一个不存在的exchange
        template.setRoutingKey("adfadsfad");
        template.setExchange("adfadsfadsfads");

        //template.setConfirmCallback(new MsgSendConfirmCallBack());
        template.setReturnCallback(new MsgSendReturnCallback());

        String msg;
        for(int i=0;i<3;i++){
            msg = "Hello World!"+i;

            try {
                template.convertAndSend(msg);
                System.out.println(msg);
            } catch (AmqpException e) {

            }

            Thread.sleep(1000);
        }
       // ctx.destroy();
    }
}
