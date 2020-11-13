package com.lin.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

/**
 * 功能概要：消费接收
 * 
 * @author linbingwen
 * @since  2016年1月15日 
 * 
 * ChannelAwareMessageListener 
 */
//public class MessageConsumer implements MessageListener {
public class MessageConsumer implements ChannelAwareMessageListener {
	
	private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

	/*@Override
	public void onMessage(Message message) {
		logger.info("receive message:{}",message);
	}*/

	@Override
	public void onMessage(Message arg0, Channel arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
