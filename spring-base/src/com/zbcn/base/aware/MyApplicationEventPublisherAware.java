package com.zbcn.base.aware;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import lombok.Data;
/**
 * @ClassName: MyApplicationEventPublisherAware
 * @Description: 在bean中可以得到应用上下文的事件发布器，从而可以在Bean中发布应用上下文的事件
 * @author Administrator
 * @date 2019-08-15 16:07
 *
 */
@Component
@Data
public class MyApplicationEventPublisherAware implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		// TODO Auto-generated method stub
		this.applicationEventPublisher = applicationEventPublisher;
	}

}
