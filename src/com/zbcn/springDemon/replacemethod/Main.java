package com.zbcn.springDemon.replacemethod;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @ClassName: Main
 * @Description: replace-method 方法测试:replacer 对象要实现MethodReplacer类，复写其中的方法
 * @author Administrator
 * @date 2019-08-14 15:18
 *
 */
public class Main {

	public static void main(String[] args) {
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("resource/replacemethod/replace-method.xml"));
		MyBean bean = (MyBean)factory.getBean("myBean");
		bean.doSomeThing();
	}
}
