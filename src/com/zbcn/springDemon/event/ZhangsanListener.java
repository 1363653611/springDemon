package com.zbcn.springDemon.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ZhangsanListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if (event instanceof ContentEvent) {
			System.out.println("张三收到内容：" + event.getSource());
		}
	}

}
