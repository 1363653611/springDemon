package com.zbcn.base.task;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: Main
 * @Description: 定时任务的4种实现 (两种配置方式，继承QuartzJobBean 和非继承)
 * @author Administrator
 * @date 2019-08-15 09:40
 *
 */
public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		//quartzTest(context);
		springTask(context);
	}
	
	
	private static void springTask(ClassPathXmlApplicationContext context) {
		context.setConfigLocation("resource/springtask/spring-task.xml");
		context.refresh();
	}
	/**
	 * @Title: quartzTest
	 * @Description: quartz
	 * @param context
	 */
	private static void quartzTest(ClassPathXmlApplicationContext context) {
		context.setConfigLocation("resource/quartz/spring-quartz.xml");
		context.refresh();
	}
	
	
}
	