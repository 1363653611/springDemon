package com.zbcn.springDemon.aware;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import lombok.Data;
@Component
@Data
public class MyEmbeddedValueResolverAware implements EmbeddedValueResolverAware{

	private StringValueResolver resolver;
	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		// TODO Auto-generated method stub
		this.resolver =resolver;
	}

}
