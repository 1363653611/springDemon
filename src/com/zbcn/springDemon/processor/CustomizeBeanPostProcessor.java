package com.zbcn.springDemon.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.zbcn.common.util.StackUtils;

/**
 * @ClassName: CustomizeBeanPostProcessor：只改变实例化的对象(BeanPostProcessor接口)；
 * @Description: bean 后处理器测试：https://blog.csdn.net/boling_cavalry/article/details/82250986
 * @author Administrator
 * @date 2019-08-30 11:01
 *
 */
@Component
public class CustomizeBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if ("processDemon".equals(beanName)) {
			// 打印当前堆栈
			StackUtils.printStack("do postProcess before initialization");
			ProcessDemon testService = (ProcessDemon) bean;
			// 修改calculateService实例的成员变量serviceDesc的值
			testService.setMsg("desc from " + this.getClass().getSimpleName());
			System.out.println("processor:" + this.getClass().getSimpleName());
		}
		return bean;

	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if ("processDemon".equals(beanName)) {
			// 打印当前堆栈
			StackUtils.printStack("do postProcess before initialization");
			ProcessDemon testService = (ProcessDemon) bean;
			// 修改calculateService实例的成员变量serviceDesc的值
			testService.setMsg("desc from " + testService.getMsg());
			System.out.println("processor:" +  testService.getMsg());
		}
		return bean;
	}

}
