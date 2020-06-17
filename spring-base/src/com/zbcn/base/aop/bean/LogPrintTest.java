package com.zbcn.base.aop.bean;

import com.zbcn.base.aop.annotation.ExceptionLog;
import com.zbcn.base.aop.annotation.Log;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LogPrintTest {

	@Log("测试日志打印问题")
	public String doSomething(String text) {
		System.out.println("测试方法调用！");
		return text;
	}
	
	@Log("doOther")
	public int doOther(int i) {
		System.out.println("测试other数据");
		return i;
	}

	@ExceptionLog(moduleName = "数据转换", value = "异常信息测试")
	public void throwException(int i,String test, Map<String, Object> obj){

		throw new RuntimeException("运行时异常：");
	}
}
