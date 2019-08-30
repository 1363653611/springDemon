package com.zbcn.springDemon.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
/**
 * 调用栈：AbstractApplicationContext.refresh ->invokeBeanFactoryPostProcessors
 * @ClassName: CustomizeBeanFactoryPostProcessor
 * @Description: 只修改beanDefination：此时的spring容器还没有开始实例化bean，因此自定义的BeanFactoryPostProcessor实现类不要做与bean实例有关的操作，而是做一些与bean定义有关的操作，例如修改某些字段的值，这样后面实例化的bean的就会有相应的改变；
 * @author Administrator
 * @date 2019-08-30 11:42
 *
 */
public class CustomizeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition)beanFactory.getBeanDefinition("beanFactoryProcessDemon");
		MutablePropertyValues pv = beanDefinition.getPropertyValues();
		 pv.addPropertyValue("msg", "Desc is changed from bean factory post processor");
		 beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
	}

}
