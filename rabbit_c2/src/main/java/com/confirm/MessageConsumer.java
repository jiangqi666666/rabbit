package com.confirm;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Address;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

/**
 * 从MQ接收消息<p>
 * @author jiangqi
 */

public class MessageConsumer implements ChannelAwareMessageListener {
	private static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

	@Override
	public void onMessage(Message msg, Channel channel) throws IOException  {
		// TODO Auto-generated method stub
		//logger.info(msg.getBody().toString());
	
		Address aa=msg.getMessageProperties().getReplyToAddress();
		
		System.out.println("address= "+aa.toString());
		
		TestPojo bbb=TestPojo.toObject(msg.getBody());
		System.out.println(TestPojo.toObject(msg.getBody()).toString());
		
		channel.basicReject( msg.getMessageProperties().getDeliveryTag(),  false);
		
		/**
		 * 采用手动ack方式回复
		 * basicAck参数： 该消息的index，是否批量.true:将一次性ack所有小于deliveryTag的消息。
		 * basicReject参数：该消息的index，被拒绝的是否重新入队列 (如果拒绝重新入队，则会被丢弃到死信队列)
		 * basicNack参数：该消息的index，是否批量.true:将一次性拒绝所有小于deliveryTag的消息，被拒绝的是否重新入队列(如果拒绝重新入队，则会被丢弃到死信队列)
		 */
		
		//msg.getMessageProperties().setReplyTo(replyTo);
		
	/*	try {
			channel.basicAck( msg.getMessageProperties().getDeliveryTag(),false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
		}*/
		//arg1.basicReject(arg0, arg1);
		//arg1.basicNack(arg0, arg1, arg2);
		//throw new Exception("AAAA");
	} 
	
	
}
