package com.zbcn.springDemon.global;

import java.util.GregorianCalendar;
import java.util.Locale;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resource/global/spring-global.xml");
		
		Object[] param = {"john",new GregorianCalendar().getTime()};
	
		String pattern = context.getMessage("test", param, Locale.US);
		String pattern2 = context.getMessage("test", param, Locale.CHINA);
		String pattern3 = context.getMessage("test", param,Locale.getDefault());
		
		System.out.println(pattern);
		
		System.out.println(pattern2);
		System.out.println(pattern3);
	}
}
