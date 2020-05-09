package com.zbcn.base.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @ClassName: MyBeanNameAware
 * @Description: BeanNameAware，可以在Bean中得到它在IOC容器中的Bean的实例的名字。
 * @author Administrator
 * @date 2019-08-15 15:24
 *
 */
@Component
@Data
public class MyBeanNameAware implements BeanNameAware{

	private String name;
	@Override
	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	
	public void run() {
		System.out.println("容器被感知--BeanNameAware:Bean name is'" + this.name + "'.>>"+name);
	}


}
