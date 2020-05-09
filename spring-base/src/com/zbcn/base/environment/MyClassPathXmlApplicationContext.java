package com.zbcn.base.environment;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;

/**
 * 从集合requiredProperties中获取环境变量的key，调用getProperty(key)通过key获取环境变量的值，只要有一个key对应的值为空
 * @ClassName: MyClassPathXmlApplicationContext
 * @Description: TODO
 * @author Administrator
 * @date 2019-08-30 17:34
 *
 */
public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {
	
	public MyClassPathXmlApplicationContext(String... configLocations) throws BeansException {
        super(configLocations);
    }

	
	public static void main(String[] args) {
        ApplicationContext applicationContext=new MyClassPathXmlApplicationContext("resource/env/spring-env.xml");
        EvnDemonBean bean = applicationContext.getBean(EvnDemonBean.class);
        bean.setName("123");
        System.out.println(JSON.toJSONString(bean));
    }

	/**
	 * 设置一个必须的环境变量
	 */
	@Override
    protected void initPropertySources() {
        getEnvironment().setRequiredProperties("VAR");
    }

}
