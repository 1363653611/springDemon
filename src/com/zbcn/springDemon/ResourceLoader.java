package com.zbcn.springDemon;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * @ClassName: ResourceLoader
 * @Description: 资源加载类测试
 * @author Administrator
 * @date 2019-06-13 18:50
 *
 */
public class ResourceLoader {
	
	public static void main(String[] args) {
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource("classpath:spring-application.xml");
		String description = resource.getDescription();
		System.out.println(description);
	}
}
