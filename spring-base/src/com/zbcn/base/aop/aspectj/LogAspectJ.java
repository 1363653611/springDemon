package com.zbcn.base.aop.aspectj;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.time.LocalDate;
import java.util.Objects;

import com.zbcn.base.aop.annotation.ExceptionLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
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


	@Pointcut("@annotation(com.zbcn.base.aop.annotation.ExceptionLog)")
	private void pointCutError(){
	}

	@AfterThrowing(pointcut = "pointCutError()",throwing = "e")
	public void handleThrowing(JoinPoint joinPoint, Exception e){
		Class<?> aClass = joinPoint.getTarget().getClass();
		String name = aClass.getName();
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		//获取异常中的备注信息
		ExceptionLog annotation = method.getAnnotation(ExceptionLog.class);
		//模块名称
		String moduleName = annotation.moduleName();
		Class<? extends Throwable> exception = annotation.exception();
		// 如果指定的异常不为空，且该异常不是抛出异常的父类，则不记录
		if(Objects.nonNull(exception) && !exception.isAssignableFrom(e.getClass()) ){
			return;
		}
		String value = annotation.value();

		// 请求的方法参数名称
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paramNames = u.getParameterNames(method);
		Class<?>[] parameterTypes = method.getParameterTypes();
		//参数不记录，有可能会很大
		//Object[] args = joinPoint.getArgs();
		String params = "";
		if (parameterTypes != null && paramNames != null) {

			for (int i = 0; i < paramNames.length; i++) {
				params += "  [" + parameterTypes[i]+ "] => "+ paramNames[i] ;
			}
		}

		System.out.println("异常类：" + name  + "请求方法：" + method.getName() + "参数及对应的值：" + params + "异常信息：" + getStackTrace(e) + "时间：" + LocalDate.now() + "备注信息：" + value);
	}


	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
}
