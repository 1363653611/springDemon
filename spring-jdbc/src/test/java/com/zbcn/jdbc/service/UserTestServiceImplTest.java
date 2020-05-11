package com.zbcn.jdbc.service;

import com.zbcn.jdbc.entity.UserTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
/**
 *  @title UserTestServiceImplTest
 *  @Description junit 测试
 *  @author zbcn8
 *  @Date 2020/5/11 9:54
 */
@ContextConfiguration(locations = {"classpath:spring-jdbc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTestServiceImplTest {

    @Autowired
    private IUserTestService userTestServiceImpl;

    @Test
    public void save() {
        UserTest zhangsan = new UserTest(1, "zhangsan", 22, "1");
        userTestServiceImpl.save(zhangsan);
        Assert.assertTrue(true);
    }

    @Test
    public void getUsers() {
        List<UserTest> users = userTestServiceImpl.getUsers();
        System.out.println(users);
        Assert.assertNotNull("查询结果并能为空", users);
    }
}