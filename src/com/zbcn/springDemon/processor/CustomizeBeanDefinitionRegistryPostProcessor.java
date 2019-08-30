package com.zbcn.springDemon.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
/**
 * 
 * @ClassName: CustomizeBeanDefinitionRegistryPostProcessor
 * @Description: spring还支持我们通过代码来将指定的类注册到spring容器中
 * @author Administrator
 * @date 2019-08-30 13:54
 *
 */
public class CustomizeBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("execute postProcessBeanFactory");
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("beanDefinitionRegistryDemon");
		MutablePropertyValues pv = beanDefinition.getPropertyValues();
		pv.addPropertyValue("msg", "execute postProcessBeanDefinitionRegistry");
		
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("execute postProcessBeanDefinitionRegistry");
		//创建一个bean的定义类的对象，bean类型是CalculateServiceImpl
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(BeanDefinitionRegistryDemon.class);
		//bean的定义注册到spring环境
		registry.registerBeanDefinition("beanDefinitionRegistryDemon", rootBeanDefinition);
	}

}
