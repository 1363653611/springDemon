package com.zbcn.springDemon.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.Data;
/**
 * 
 * @ClassName: MyApplicationContextAware
 * @Description: 可以在Bean中得到Bean所在的应用上下文，从而直接在Bean中使用上下文的服务
 * @author Administrator
 * @date 2019-08-15 15:53
 *
 */
@Component
@Data
public class MyApplicationContextAware implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}

}
