package com.zbcn.springDemon.aop.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zbcn.springDemon.aop.introduction.other.IOther;
import com.zbcn.springDemon.aop.introduction.some.ISome;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("resource/aop/spring-instroduction.xml");
		ISome some=(ISome)context.getBean("proxyFactoryBean");
		 some.doSome();
		 ((IOther)some).doOther();

	}
}

