package com.zbcn.springDemon.processor;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

/**
 * DeanPostProcessor :https://blog.csdn.net/boling_cavalry/article/details/82250986
 * @ClassName: Main
 * @Description: TODO
 * @author Administrator
 * @date 2019-08-30 11:10
 *
 */
public class Main {

	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
		context.addBeanFactoryPostProcessor(new CustomizeBeanFactoryPostProcessor());
		context.refresh();
		ProcessDemon bean = context.getBean(ProcessDemon.class);
		BeanFactoryProcessDemon bean2 = context.getBean(BeanFactoryProcessDemon.class);
		System.out.println("测试:"+ JSON.toJSONString(bean2));
		context.stop();
	}
	
	
}
	