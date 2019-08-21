package com.zbcn.springDemon.aop.introduction.other;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

public class Other implements IOther, IntroductionInterceptor{

	@Override
	public void doOther() {
		// TODO Auto-generated method stub
		System.out.println("Other对象的功能");
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		if(implementsInterface(invocation.getMethod().getDeclaringClass())) {
			return invocation.getMethod().invoke(this, invocation.getArguments());

		}else {
			return invocation.proceed();
		}
		
	}

	@Override
	public boolean implementsInterface(Class<?> intf) {
		// TODO Auto-generated method stub
		return intf.isAssignableFrom(IOther.class);
	}

}
