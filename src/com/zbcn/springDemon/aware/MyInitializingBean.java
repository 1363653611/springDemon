package com.zbcn.springDemon.aware;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyInitializingBean
 * @Description: spring将调用它的afterPropertiesSet接口方法，
 * 	类似的如果bean使用了init-method属性声明了初始化方法，该方法也会被调用
 * @author Administrator
 * @date 2019-08-15 17:37
 *
 */
@Component
public class MyInitializingBean implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("I'm  init  method  using implements InitializingBean interface...."+new Date());
	}

}
