package com.zbcn.base.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SunliuListener implements SmartApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		System.out.println("孙六在王五之后收到新的内容：" + event.getSource());  
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		// TODO Auto-generated method stub
		return eventType == ContentEvent.class;
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		// TODO Auto-generated method stub
		return String.class == sourceType;
	}

}
