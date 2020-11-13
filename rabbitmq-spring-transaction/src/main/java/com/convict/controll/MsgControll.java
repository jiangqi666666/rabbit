package com.convict.controll;


import com.convict.busi.service.MsgService;
import com.convict.busi.model.Msg;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MsgControll {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:applicationContext.xml"});

        //发送消息
        MsgService msgService = context.getBean(MsgService.class);
        Msg msg = new Msg();
        msg.setId(Long.valueOf(1));
        msg.setTag(1);
        msg.setTxt("test");
        msgService.addMsg(msg);

//        msg.setId(Long.valueOf(2));
//        msgService.addMsg(msg);


    }
}
