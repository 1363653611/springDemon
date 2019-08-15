package com.zbcn.springDemon.aware;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.zbcn.springDemon.event.ContentEvent;
/**
 * @ClassName: Main
 * @Description: 使用spring容器中的特定对象的方式：对spring 容器的感知
 * @author Administrator
 * @date 2019-08-15 15:48
 *
 */
public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		
		context.setConfigLocation("resource/aware/spring-aware.xml");
		context.refresh();
		//testNameAware(context);
		
		//testMyBeanFactoryAware(context);
		
		//testApplicationContextAware(context);
		
		//testMessageSourceWare(context);
		
		//testApplicationEventPublisherAware(context);
		
		testMyResourceLoaderAware(context);
		
		testMyBeanPostProcessor(context);
	}

	private static void testMyBeanPostProcessor(ClassPathXmlApplicationContext context) {
		// TODO Auto-generated method stub
		MyBeanNameAware bean = context.getBean(MyBeanNameAware.class);
		System.out.println("获取bean名称" + bean.getName());
		context.destroy();
	}

	private static void testMyResourceLoaderAware(ClassPathXmlApplicationContext context) {
		// TODO Auto-generated method stub
		MyResourceLoaderAware bean = context.getBean(MyResourceLoaderAware.class);
		ResourceLoader resourceLoader = bean.getResourceLoader();
		Resource resource = resourceLoader.getResource("spring-application.xml");
		System.out.println(resource.getDescription());
		try {
			readContent(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	   public static void readContent(Resource resource) throws IOException {
	        InputStream ins = resource.getInputStream();
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        int i;
	        while ((i = ins.read()) != -1) {
	            bos.write(i);
	        }
	        System.out.println("读取的文件:" + resource.getFilename() + ",/n内容:/n" + bos.toString());
	    }

	private static void testApplicationEventPublisherAware(ClassPathXmlApplicationContext context) {
		// TODO Auto-generated method stub
		MyApplicationEventPublisherAware bean = context.getBean(MyApplicationEventPublisherAware.class);
		ApplicationEventPublisher publisher = bean.getApplicationEventPublisher();
		publisher.publishEvent(new ContentEvent("测试获取publisherEvent"));
	}

	/**
	 * @Title: testMessageSourceWare
	 * @Description: 国际化支持
	 * @param context
	 */
	private static void testMessageSourceWare(ClassPathXmlApplicationContext context) {
		// TODO Auto-generated method stub
		MyMessageSourceAware bean = context.getBean(MyMessageSourceAware.class);
		//处理国际化
		MessageSource messageSource = bean.getMessageSource();
		System.out.println(messageSource.getMessage("appName", null, null));
	
	}

	private static void testApplicationContextAware(ClassPathXmlApplicationContext context) {
		// TODO Auto-generated method stub
		MyBeanNameAware bean = context.getBean(MyBeanNameAware.class);
		System.out.println("获取bean名称" + bean.getName());
	}

	private static void testMyBeanFactoryAware(ClassPathXmlApplicationContext context) {
		// TODO Auto-generated method stub
		MyBeanFactoryAware bean = context.getBean(MyBeanFactoryAware.class);
		bean.run();
		//获取beanFactory
		ConfigurableListableBeanFactory beanFactory = bean.getBeanFactory();
		MyBeanNameAware nameAware = beanFactory.getBean(MyBeanNameAware.class);
		System.out.println("测试获取对象：" + nameAware.getName());
		beanFactory.destroySingletons();
	}

	private static void testNameAware(ClassPathXmlApplicationContext context) {
		// TODO Auto-generated method stub
		MyBeanNameAware bean = context.getBean(MyBeanNameAware.class);
		bean.run();
	}
}
