package com.convict.busi.service;

import com.convict.busi.dao.MsgDao;
import com.convict.busi.model.Msg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MsgService {

    @Autowired
    private MsgDao msgDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void addMsg(Msg msg) throws Exception {
        //模拟业务操作
        Thread.sleep(1000);
        //消息保存到数据库，和业务操作为同一个事物
        msgDao.insert(msg);
        //发送消息
        rabbitTemplate.convertAndSend(msg.getTxt());
        if (msg.getId() == null || msg.getId() % 2 == 0) {//事物，偶数的回滚
            throw new Exception();
        }
    }
}
