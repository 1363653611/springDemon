package com.zbcn.base.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MyEnvironmentAware implements EnvironmentAware{

	private Environment environment;
	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		this.environment = environment;
	}

}
