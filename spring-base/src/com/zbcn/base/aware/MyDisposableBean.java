package com.zbcn.base.aware;

import java.util.Date;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
/**
 * @ClassName: MyDisposableBean
 * @Description: 若bean实现了DisposableBean接口，spring将调用它的distroy()接口方法。
 * 	同样的，如果bean使用了destroy-method属性声明了销毁方法，则该方法被调用
 * @author Administrator
 * @date 2019-08-15 17:39
 *
 */
@Component
public class MyDisposableBean implements DisposableBean{

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("I'm  init  method  using implements DisposableBean interface...."+new Date());
	}

}
