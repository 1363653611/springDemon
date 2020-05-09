package com.zbcn.base.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import lombok.Data;
/**
 * @ClassName: MyBeanFactoryAware
 * @Description: 可以在Bean中得到Bean所在的IOC容器，从而直接在Bean中使用IOC容器的服务。
 * @author Administrator
 * @date 2019-08-15 15:36
 *
 */
@Component
@Data
public class MyBeanFactoryAware implements BeanFactoryAware{
	
	private ConfigurableListableBeanFactory beanFactory;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		if(ConfigurableListableBeanFactory.class.isInstance(beanFactory)) {
			this.beanFactory = (ConfigurableListableBeanFactory)beanFactory;
		}
	}
	
	public void run() {
		if(this.beanFactory != null) {
			System.out.println("容器被感知--BeanFactory-Destroying singletons.>>"+beanFactory);
			//获取对象名称
		}
	}

}
