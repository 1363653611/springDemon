package com.zbcn.springDemon.init;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class InitAndDestroySeqBean implements InitializingBean,DisposableBean  {

	public InitAndDestroySeqBean(){
        System.out.println("执行InitAndDestroySeqBean: 构造方法");
    }
	@PostConstruct
    public void postConstruct() {  
       System.out.println("执行InitAndDestroySeqBean: postConstruct");  
    }  
	public void initMethod() {
        System.out.println("执行InitAndDestroySeqBean: init-method");
    }
	
	public void destroyMethod() {
        System.out.println("执行InitAndDestroySeqBean: destroy-method");
    }

	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		 System.out.println("执行InitAndDestroySeqBean: afterPropertiesSet");
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("执行InitAndDestroySeqBean: destroy");
	}
	
	@PreDestroy
    public void preDestroy()  {
        System.out.println("执行InitAndDestroySeqBean: preDestroy");
    }


}
