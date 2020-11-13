package com.convict.rabbitmq.spring.sync;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

public class MsgHandler implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("consumer receive massage:" + new String(message.getBody())+"______routing key : "+message.getMessageProperties().getReceivedRoutingKey());
        //channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


    /**
     * MessageListenerAdapter：
     *
     * 如果返回的不是null，就把返回的值当着一个消息发送到接收到消息的exchange，并使用消息的replyTo属性作为routing key。
     * 原文如下：
     * If a target listener method returns a non-null object (typically of a message content type such as
     * <code>String</code> or byte array), it will get wrapped in a Rabbit <code>Message</code> and sent to the exchange of
     * the incoming message with the routingKey that comes from the Rabbit ReplyTo property or via
     * {setResponseRoutingKey(String) specified routingKey}).
     *
     */
    public void handleMassage(String msg){
        System.out.println("consumer receive massage:" + msg);
    }


}
