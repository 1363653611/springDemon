package com.zbcn.base.processor.demon;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringValueResolver;

import java.util.HashSet;
import java.util.Set;

/**
 *  @title ObscenityRemovingBeanFactoryPostProcessor
 *  @Description 自定义processor ,替换 SimplePostProcessor中的属性
 *  @author zbcn8
 *  @Date 2020/4/11 9:05
 */
public class ObscenityRemovingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	private Set<String> obscenities;

	public ObscenityRemovingBeanFactoryPostProcessor() {
		this.obscenities = new HashSet<>();
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String name:beanDefinitionNames){
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
			StringValueResolver stringValueResolver = new StringValueResolver() {

				@Override
				public String resolveStringValue(String strVal) {
					if (isObscene(strVal)) {
						return "*****";
					}
					return strVal;
				}
			};
			BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(stringValueResolver);
			visitor.visitBeanDefinition(beanDefinition);
		}
	}

	public boolean isObscene(Object value){
		String potentialObscenity = value.toString();
		return this.obscenities.contains(potentialObscenity);
	}

	public void setObscenities(Set<String> obscenities){
		this.obscenities.clear();
		for(String obscenity : obscenities){
			this.obscenities.add(obscenity);
		}
	}
}
