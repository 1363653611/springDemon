package com.zbcn.annotation.postconstruct;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PostConstructAppConfig.class);
		TestService bean = context.getBean(TestService.class);
		bean.doSomeThing();
	}
}
