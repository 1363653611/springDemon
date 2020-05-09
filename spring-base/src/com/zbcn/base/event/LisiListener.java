package com.zbcn.base.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 * 
 * @ClassName: LisiListener
 * @Description: 定义无序监听器
 * @author Administrator
 * @date 2019-08-15 14:33
 *
 */
@Component
public class LisiListener implements ApplicationListener<ApplicationEvent>{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if(event instanceof ContentEvent) {
			System.out.println("李四收到内容：" + event.getSource());
		}
	}

}
