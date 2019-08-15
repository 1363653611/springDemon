package com.zbcn.springDemon.aop.bean;

import org.springframework.stereotype.Component;

import com.zbcn.springDemon.aop.annotation.Log;

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
}
