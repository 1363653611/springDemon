package com.zbcn;

import com.zbcn.pojo.UserTest;
import com.zbcn.service.IUserTestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        IUserTestService bean = context.getBean(IUserTestService.class);
        List<UserTest> list = bean.list();
        System.out.println(list);
    }
}
