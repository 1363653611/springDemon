package com.zbcn.base.aop;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.StandardEnvironment;

import com.alibaba.fastjson.JSON;
import com.zbcn.base.aop.bean.LogPrintTest;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
		
		LogPrintTest bean = (LogPrintTest)context.getBean("logPrintTest");
		
		bean.doSomething("打印测试呀");
		bean.doOther(123);
		
		testEnvironment();
	}
	
	public static void testEnvironment() {
		StandardEnvironment standardEnvironment = new StandardEnvironment();
		Map<String, Object> systemEnvironment = standardEnvironment.getSystemEnvironment();
		Map<String, Object> systemProperties = standardEnvironment.getSystemProperties();
		System.out.println(JSON.toJSONString(systemEnvironment));
		System.out.println(JSON.toJSONString(systemProperties));
	}
	
}
