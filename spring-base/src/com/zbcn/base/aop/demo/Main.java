package com.zbcn.base.aop.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  @title Main
 *  @Description aspectJ 测试类
 *  @author zbcn8
 *  @Date 2020/5/5 9:12
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("resource/aop/spring-aspectj.xml");
        TestBean testBean = (TestBean)context.getBean("testBean");
        testBean.test();
    }
}
