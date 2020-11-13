package com.confirm;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Address;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;


/**
 * 功能概要：消息产生,提交到队列中去
 * 
 * @author linbingwen
 * @since 2016年1月15日
 */
@Service
public class MsgConfirmProducer {

	private Logger logger = LoggerFactory.getLogger(MsgConfirmProducer.class);

	// @Resource
	// private AmqpTemplate amqpTemplate;

	@Resource
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(TestPojo msg) {
		// logger.info("to send message:{}",message);
		
		/**
		 *  设置为真，交换机发现不能发送到队列时回调ReturnCallback；
		 *  设置为假，交换机发现不能发送到队列时抛弃消息，不回调ReturnCallback；
		 */
		//this.rabbitTemplate.setMandatory(true);

		/**
		 * 标记消息是否需要持久化
		 */
		
		//String msgId = UUID.randomUUID().toString();  
		String msgId="A1111";
		
		//BasicProperties bb=new AMQP.BasicProperties.Builder().correlationId("AA").build();
		
		//设置消息应答信息，参数：交换机名称，routeKey
		Address replyTo =new Address("retExchange","ret1");
		
			
        Message message = MessageBuilder.withBody(TestPojo.toByteArray(msg))
        		//设置消息类型为普通文本
              //  .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)  
        		.setContentType(MessageProperties.CONTENT_TYPE_SERIALIZED_OBJECT)
                //设置消息字符集为GBK
                .setContentEncoding("GBK")
                //设置消息应答地址
                .setReplyToAddress(replyTo)
                //设置发送和接收消息的配对id
                .setCorrelationId(msgId.getBytes())
                //设置消息持久化
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .setReceivedRoutingKey("aaa")
                .build();  
        
        /**
		 * confirm模式需要在confirm回调函数中通过CorrelationData的id来匹配数据
		 */
        CorrelationData date = new CorrelationData(msgId);  

        //rabbitTemplate.correlationConvertAndSend(message, date);
		rabbitTemplate.convertAndSend("aaa", message,date);
	}
}
