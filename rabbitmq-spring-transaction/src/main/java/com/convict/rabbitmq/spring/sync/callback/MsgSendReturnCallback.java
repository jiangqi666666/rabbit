package com.convict.rabbitmq.spring.sync.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 *
 */
public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String msgJson  = new String(message.getBody());
        System.out.println("Returned Message："+msgJson);
    }
}
