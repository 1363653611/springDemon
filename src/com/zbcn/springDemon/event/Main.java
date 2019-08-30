package com.zbcn.springDemon.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zbcn.springDemon.event.other.CustomizePublisher;

public class Main {

	public static void main(String[] args) {
		ApplicationContext resource = new ClassPathXmlApplicationContext("resource/event/spring-event.xml");
		resource.publishEvent(new ContentEvent("测试测试。。。。"));
		testOtherPublish(resource);
	}
	
	public static void testOtherPublish(ApplicationContext resource) {
		CustomizePublisher bean = resource.getBean(CustomizePublisher.class);
		bean.publishEvent();
	}
}
