package com.zbcn.base.aop;

import java.util.HashMap;
import java.util.Map;

import com.zbcn.base.aop.bean.ILogTest;
import com.zbcn.base.aop.bean.LogTestImpl;
import org.slf4j.ILoggerFactory;
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
		HashMap<String, Object> map = new HashMap<>();
		map.put("key", "123");
		map.put("value", "zhizhi");
//		bean.throwException(2, "aaaaaa", map);
		ILogTest logTestImpl = (ILogTest)context.getBean("logTestImpl");
		logTestImpl.doLog();
		//说明：aop 代理，如果需要代理的类实现了接口，则使用的是 JDK 动态代理，创建的代理对象是基于 接口的，
		// 所以未实现接口的方法不会被增强
		//logTestImpl.doLog2();
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
