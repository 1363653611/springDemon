package com.zbcn.base.aop.aspectj;

import java.lang.reflect.Method;
import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import com.zbcn.base.aop.annotation.Log;

@Aspect
@Component
public class LogAspectJ {

	/**
	 * @Title: pointCut
	 * @Description: 切入点
	 */
	@Pointcut("@annotation(com.zbcn.base.aop.annotation.Log)")
	public void pointCut() {
		
	}
	
	/**
	 * @Title: around
	 * @Description: 切入点要执行的逻辑
	 * @param point
	 */
	@Around("pointCut()")
	public Object around(ProceedingJoinPoint point) {
		//开始时间
		long beginTime = System.currentTimeMillis();
		Object proceed = null;
		try {
			//执行方法
			proceed = point.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long time = System.currentTimeMillis() - beginTime;
		
		printInfo(point,time);
		return proceed;
	}

	private void printInfo(ProceedingJoinPoint point, long time) {
		// TODO Auto-generated method stub
		MethodSignature signature = (MethodSignature)point.getSignature();
		Method method = signature.getMethod();
		
		Log annotation = method.getAnnotation(Log.class);
		if(Objects.nonNull(annotation)) {
			String desc = annotation.value();
		}
		// 请求的方法名
		String className = point.getTarget().getClass().getName();
		String methodName = signature.getName();
		String fullName = className + "." + methodName + "()";
		// 请求的方法参数值
		Object[] args = point.getArgs();
		
		// 请求的方法参数名称
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paramNames = u.getParameterNames(method);
		String params = "";
		if (args != null && paramNames != null) {
			
			for (int i = 0; i < args.length; i++) {
				params += "  " + paramNames[i] + ": " + args[i];
			}
		}
		
		System.out.println("方法名：" + fullName + "; 方法参数：" + params + " ;调用时间：" + time);
	}
}
