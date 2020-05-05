package com.zbcn.springDemon.processor.demon;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class PropertyConfigurerDemo {

	public static void main(String[] args) {
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("resource/processor/property-configurer-demo.xml"));
		ObscenityRemovingBeanFactoryPostProcessor bfpp = (ObscenityRemovingBeanFactoryPostProcessor)xmlBeanFactory.getBean("bfpp");
		bfpp.postProcessBeanFactory(xmlBeanFactory);
		System.out.println(xmlBeanFactory.getBean("simpleBean"));
	}
}
