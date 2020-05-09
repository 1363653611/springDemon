package com.zbcn.base.replacemethod;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class MyBeanReplacer implements MethodReplacer {

	@Override
	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println(method.getName());
		//原来方法调用:此方法调用会进入死循环，应为每次调用原来方法，会用该方法代替
		//method.invoke(obj, args);
		 System.out.println("我替换了原来的方法");
		return null;
	}

}
