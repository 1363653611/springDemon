package com.zbcn.base.propertyeditor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zbcn.base.propertyeditor.bean.Boss;
import com.zbcn.base.propertyeditor.bean.Entity;

/**
 * @ClassName: Main
 * @Description: propertyEditor 做类型转换，bean  的ref 引用之外的另外一种方式
 * @author Administrator
 * @date 2019-08-14 16:03
 *
 */
public class Main {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resource/propertyeditor/property-editor.xml");
		
		Boss bean = context.getBean("boss",Boss.class);
		
		System.out.println(bean.getCar());
		
		registDataConvertTest(context);
	}
	
	
	public static void registDataConvertTest(ApplicationContext context) {

		Entity e = (Entity)context.getBean("entity");
        System.out.println(e.getDate());

	}

}
