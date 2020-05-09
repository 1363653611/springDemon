package com.zbcn.base;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.alibaba.fastjson.JSON;
import com.zbcn.bean.test.TestThread;
import com.zbcn.factorybean.adapter.Base64Adapter;
import com.zbcn.factorybean.adapter.DecodeAdapter;
import com.zbcn.factorybean.adapter.UrlBase64Adapter;
import com.zbcn.factorybean.entity.Base64Entity;
import com.zbcn.factorybean.entity.Text;
import com.zbcn.factorybean.entity.UrlBase64Entity;
import com.zbcn.factorybean.factory.Base64AdapterFacory;
import com.zbcn.factorybean.factory.UrlBase64AdapterFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = loadSpringContext();
//    	Student student = (Student)context.getBean("student");
//    	Object bean = context.getBean("book");
//    	System.out.println(JSON.toJSONString(student));
//    	BeanFactory beanFactory = getBeanFactory();
//    	Teacher teacher = (Teacher)beanFactory.getBean("teacher");
//    	XmlBeanFactory xmlBeanFactory = getXmlBeanFactory();
//    	Teacher teacher = (Teacher)xmlBeanFactory.getBean("teacher");

//    	ApplicationContext fileXmlApplicationContext = getFileXmlApplicationContext();
//    	Teacher teacher = (Teacher)fileXmlApplicationContext.getBean("teacher");
    	
    	//Object bean = context.getBean("&personFactorybean");//返回工厂本身
    	testthread(context);
    }
    
    public static void testthread(ApplicationContext context) {
    	TestThread bean = context.getBean(TestThread.class);
    	bean.testBB();
    }
    
    public static void testFactoryBean(ApplicationContext context) {
    	Text bean = context.getBean(Base64Entity.class);
    	bean = context.getBean(UrlBase64Entity.class);
    	FactoryBean factory = null;
    	DecodeAdapter adapter = null;
    	switch (bean.getType()) {
		case Base64:
			adapter = (Base64Adapter)context.getBean("base64AdapterFactory");
			factory = context.getBean("&base64AdapterFactory",Base64AdapterFacory.class);
			break;
		case UrlBase64:
			adapter = (UrlBase64Adapter)context.getBean("urlBase64AdapterFactory");
			factory = context.getBean("&urlBase64AdapterFactory",UrlBase64AdapterFactory.class);
			break;
		default:
			break;
		}
    	System.out.println(JSON.toJSONString(adapter));
    	System.out.println(factory.getClass().getName());
    }
    
    /**
     * @Title: loadSpringContext
     * @Description: 容器的初始化
     * @return
     */
    public static ApplicationContext loadSpringContext() {
    	ApplicationContext context = new ClassPathXmlApplicationContext("spring-application.xml");
    	return context;
    }

    
    public static XmlBeanFactory getXmlBeanFactory() {
    	//根据xml配置文件创建resource资源对象，该对象中包含了beanDefinition信息
    	Resource resource = new ClassPathResource("spring-application.xml");
    	return new XmlBeanFactory(resource);
    }
    
    // 该方法为 XmlBeanFactory 加载的整个流程
    //BeanFactory 的三个子类：
    //Bean的集合（ListableBeanFactory）-》 可列表、
    //Bean之间的关系（ListableBeanFactory）-》可继承、
    //以及Bean行为（AutowireCapableBeanFactory）-》自动装配规则
    public static BeanFactory getBeanFactory() {
    	//根据xml配置文件创建resource资源对象，该对象中包含了beanDefinition信息
    	Resource resource = new ClassPathResource("spring-application.xml");
    	//创建工厂
    	DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
    	//创建资源读取器。用于载入beanDefinition信息
    	//使用beanFactory作为参数，会将读取的信息回调配置给factory
    	XmlBeanDefinitionReader definition = new XmlBeanDefinitionReader(factory);
    	//进行beanDefinition 的载入，最后会完成bean 的载入和注册
    	//完成后，就成功的将bean 放置在ioc 容器中，以后我们就可以取bean了
    	int count = definition.loadBeanDefinitions(resource);
    	System.out.println("加载的bean 数量：" + count);
    	return factory;
    }
    
    /**
     * @Title: getFileXmlApplicationContext
     * @Description: 加载bean，配置文件可以自由指定：ResourcePatternResolver中的几种格式："classpath*:"/"classpath:"
     * @return
     */
    public static ApplicationContext getFileXmlApplicationContext() {
    	return new FileSystemXmlApplicationContext("classpath:spring-application.xml");
    }
    
    
}
