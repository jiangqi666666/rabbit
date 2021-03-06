package com.confirm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class MsgSendConfirmCallBack implements ConfirmCallback {  
	
	private Logger logger = LoggerFactory.getLogger(MsgSendConfirmCallBack.class);
	
    @Override  
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {  
        if (ack) {  
            logger.info("消息确认成功"+cause);
        } else {  
            //处理丢失的消息  
            logger.info("消息确认失败,"+cause);
        }  

        System.out.println("confirm--:correlationData:"+correlationData+",ack:"+ack+",cause:"+cause); 
    }  
} 
