package com.zbcn.springDemon.aware;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MyMessageSourceAware implements MessageSourceAware{

	private MessageSource messageSource;
	@Override
	public void setMessageSource(MessageSource messageSource) {
		// TODO Auto-generated method stub
		this.messageSource = messageSource;
	}

}
