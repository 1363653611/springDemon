package com.zbcn.annotation.aop.selfscope;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @ClassName: AopAppConfig
 * @Description: 注册scope
 * @author Administrator
 * @date 2019-08-21 17:05
 */
@Configuration
@ComponentScan
public class AopAppConfig {
	
	@Bean
	public static CustomScopeConfigurer customScopeConfigurer() {
		
		CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("threadScope", new ThreadScope());
		customScopeConfigurer.setScopes(map);
		
		return customScopeConfigurer;
	} 

}
