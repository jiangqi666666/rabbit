package com.confirm;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RcvMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		context.start();
	}

}
