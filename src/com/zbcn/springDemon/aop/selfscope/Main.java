package com.zbcn.springDemon.aop.selfscope;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zbcn.springDemon.aop.selfscope.bean.Student;
import com.zbcn.springDemon.aop.selfscope.bean.Teacher;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		annotationScope(context);
		// configScope(context);
	}

	private static void annotationScope(ClassPathXmlApplicationContext context) {
		context.setConfigLocation("spring-application.xml");
		context.refresh();
		context.getBeanFactory().registerScope("myScope", new MyScope());
		Teacher bean = context.getBean("myteacher", Teacher.class);
		System.out.println(bean);
	}

	/**
	 * @Title: configScope
	 * @Description:配置文件的scope
	 * @param context
	 */
	private static void configScope(ClassPathXmlApplicationContext context) {
		context.setConfigLocation("resource/aop/spring-selfscope.xml");
		context.refresh();
		Student bean = context.getBean(Student.class);
		System.out.println(bean);
	}
}
