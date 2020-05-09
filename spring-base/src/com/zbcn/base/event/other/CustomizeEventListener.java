package com.zbcn.base.event.other;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import lombok.Data;
/**
 * 
 * @ClassName: CustomizeEventListener
 * @Description: 自定义的系统广播监听器，只接受CustomizeEvent类型的消息
 * @author Administrator
 * @date 2019-08-30 16:06
 *
 */
@Data
@Service
public class CustomizeEventListener  implements ApplicationListener<CustomizeEvent> {

	private String msg;
	
	@Override
	public void onApplicationEvent(CustomizeEvent event) {
		// TODO Auto-generated method stub
		System.out.println(JSON.toJSON(event));
		this.msg = event.getClass().getName();
	}

}
