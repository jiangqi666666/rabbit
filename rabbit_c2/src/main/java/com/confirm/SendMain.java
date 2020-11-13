package com.confirm;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SendMain implements Callable<Integer> {
	
	public static ClassPathXmlApplicationContext context;
	public static MsgConfirmProducer messageProducer;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 context = new ClassPathXmlApplicationContext("application.xml");
		 messageProducer = (MsgConfirmProducer) context.getBean("msgConfirmProducer");
		 
		 ExecutorService service = Executors.newFixedThreadPool(2);
		 
		 List<SendMain> list=new LinkedList<SendMain>();
		 for(int i=0;i<1;i++){
			 list.add(new SendMain());
		 }
		 
		 List<Future<Integer>> fus=service.invokeAll(list);
		 
		 for(Future<Integer> obj : fus){
			 try {
				System.out.println(obj.get());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 Thread.sleep(10000);
		 service.shutdownNow();
		 context.close();
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		TestPojo aa=new TestPojo();
		aa.setAmt(100.22);
		aa.setName("AAAA");
	
		for (int i = 0; i < 1; i++){
			//messageProducer.sendMessage("Jiang qi:  " + i);
			aa.setId(i);
			messageProducer.sendMessage(aa);
		}

		return 0;
	}

}
