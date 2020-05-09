package com.zbcn.base.event.other;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import lombok.Data;
/**
 * @ClassName: CustomizePublisher
 * @Description: 自定义的广播发送器
 * @author Administrator
 * @date 2019-08-30 15:53
 *
 */
@Data
@Component
public class CustomizePublisher implements ApplicationEventPublisherAware,ApplicationContextAware {

	 private ApplicationContext applicationContext;
	 
	 private ApplicationEventPublisher applicationEventPublisher;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		// TODO Auto-generated method stub
		this.applicationEventPublisher = applicationEventPublisher;
	}
	
	/**
	 * 
	 * @Title: publishEvent
	 * @Description: 发送一条广播
	 */
	public void publishEvent() {
		applicationEventPublisher.publishEvent(new CustomizeEvent(applicationContext));
	}

}
