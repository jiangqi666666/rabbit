package com.convict.rabbitmq.spring.sync.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * Created by Jamin on 2016/9/5.
 */
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息确认成功");
        } else {
            //处理丢失的消息
            System.out.println("消息确认失败,"+cause);
        }
    }
}
