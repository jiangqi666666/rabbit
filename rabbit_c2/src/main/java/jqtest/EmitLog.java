package jqtest;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {

	private final static String EXCHANGE_NAME = "ex_log";  
	  
    public static void main(String[] args) throws IOException, TimeoutException  
    {  
        // 创建连接和频道  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.174.101");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        // 声明转发器和类型  
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout" );  
          
        String message = new Date().toLocaleString()+" : log something";  
        // 往转发器上发送消息  
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());  
  
        System.out.println(" [x] Sent '" + message + "'");  
  
        channel.close();  
        connection.close();  
  
    }  
}
