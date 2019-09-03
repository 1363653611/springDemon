package com.zbcn.springDemon.init;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	/**
	 * Bean在实例化的过程中：Coumnstructor > @PostConstruct >InitializingBean > init-method
	 * Bean在销毁的过程中：@PreDestroy > DisposableBean > destroy-method
	 * @Title: main
	 * @Description: TODO
	 * @param args
	 */
	public static void main(String[] args) {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("resource/init/spring-init.xml");
		 InitAndDestroySeqBean bean = context.getBean(InitAndDestroySeqBean.class);
		 context.close();
	}
}
