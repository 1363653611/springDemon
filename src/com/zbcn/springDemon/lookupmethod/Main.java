package com.zbcn.springDemon.lookupmethod;

import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * @ClassName: Main
 * @Description: TODO
 * @author Administrator
 * @date 2019-08-14 14:40
 *
 */
public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
	
		FruitPlate bean1 = context.getBean("fruitPlate1",FruitPlate.class);
		FruitPlate bean2 = context.getBean("fruitPlate2",FruitPlate.class);
		//获取苹果
		System.out.println(bean1.getFruit());
		//获取香蕉
		System.out.println(bean2.getFruit());
	}
}
