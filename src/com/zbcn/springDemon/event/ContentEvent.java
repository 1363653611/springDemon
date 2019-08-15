package com.zbcn.springDemon.event;

import org.springframework.context.ApplicationEvent;

/**
 * @ClassName: ContentEvent
 * @Description: 定义事件；如果用户发送内容，只需要通过构造器传入内容，然后通过getSource即可获取
 * @author Administrator
 * @date 2019-08-15 14:29
 *
 */
public class ContentEvent extends ApplicationEvent {	
	/**
	 * @Fields field:field:{todo}
	 */
	
	private static final long serialVersionUID = -2015032875894397397L;

	public ContentEvent(Object source) {
		super(source);
	}

}
