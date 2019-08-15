package com.zbcn.springDemon.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
/**
 * @ClassName: MyBeanPostProcessor
 * @Description: 如果bean实现了BeanPostProcessor接口，它的postProcessBeforeInitialization方法将被调用
 * @author Administrator
 * @date 2019-08-15 17:31
 *
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("对象初始化之前：" + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("对象初始化之后：" + beanName);
		return bean;
	}

}
