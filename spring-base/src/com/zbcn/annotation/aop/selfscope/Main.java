package com.zbcn.annotation.aop.selfscope;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName: Main
 * @Description: 定义应用入口
 * @author Administrator
 * @date 2019-08-21 17:14
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AopAppConfig.class);
		//testSameThread(context);
		testAsync(context);
		
	}

	private static void testAsync(ApplicationContext context) throws InterruptedException, ExecutionException {
		 CompletableFuture.runAsync(() -> {
			 //模拟执行耗时任务
	           MessageService messageService = context.getBean(MessageService.class);
				messageService.getMessage();
		 
				MessageService messageService2 = context.getBean(MessageService.class);
				messageService2.getMessage();
				//return "task1";
		});
		//System.out.println("任务一执行完成："+supplyAsync.get());
		 CompletableFuture.runAsync(() -> {
			//模拟执行耗时任务
            MessageService messageService = context.getBean(MessageService.class);
			messageService.getMessage();
	 
			MessageService messageService2 = context.getBean(MessageService.class);
			messageService2.getMessage();
			//return "task2 ";
		});
		
		//System.out.println("任务一执行完成："+supplyAsync2.get());
	}

	private static void testSameThread(ApplicationContext context) {
		MessageServiceImpl bean = context.getBean(MessageServiceImpl.class);
		bean.getMessage();
		MessageServiceImpl bean2 = context.getBean(MessageServiceImpl.class);
		bean2.getMessage();
	}
}
