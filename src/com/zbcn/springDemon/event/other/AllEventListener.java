package com.zbcn.springDemon.event.other;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
/**
 * 
 * @ClassName: AllEventListener
 * @Description: 自定义的系统广播监听器，接收所有类型的消息
 * @author Administrator
 * @date 2019-08-30 16:05
 *
 */
@Service
public class AllEventListener implements ApplicationListener<ApplicationEvent>{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		System.out.println(JSON.toJSON(event));
	}

}
