package com.confirm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

public class MsgSendReturnCallback implements ReturnCallback {  
	
	private Logger logger = LoggerFactory.getLogger(MsgSendReturnCallback.class);
	
    @Override  
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {  
        //String msgJson  = new String(message.getBody());  
    	String head=new String(message.getMessageProperties().getCorrelationId());
        logger.info("消息路由失败："+head);
    }  
}  