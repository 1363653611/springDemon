package com.zbcn.springDemon.event.other;

import org.springframework.context.ApplicationEvent;

public class CustomizeEvent extends ApplicationEvent{
	
	
	/**
	 * @Fields field:field:{todo}
	 */
	
	private static final long serialVersionUID = 1L;
	
	public CustomizeEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
