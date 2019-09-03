package com.zbcn.springDemon.aop.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zbcn.springDemon.aop.introduction.other.IOther;
import com.zbcn.springDemon.aop.introduction.some.ISome;
/**
 * @ClassName: Main
 * @Description: 在不改变原有方法的基础上却可以增加新的方法
 * @author Administrator
 * @date 2019-09-03 10:56
 *
 */
public class Main {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("resource/aop/spring-instroduction.xml");
		ISome some=(ISome)context.getBean("proxyFactoryBean");
		 some.doSome();
		 //some 可以转化为IOther 接口，当然也就具备了调用doOther方法的能力
		 ((IOther)some).doOther();

	}
}

