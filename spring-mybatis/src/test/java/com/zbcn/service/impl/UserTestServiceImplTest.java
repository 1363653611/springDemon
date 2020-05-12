package com.zbcn.service.impl;

import com.zbcn.pojo.UserTest;
import com.zbcn.service.IUserTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.sound.midi.Soundbank;
import java.util.List;

@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTestServiceImplTest {

    @Autowired
    private IUserTestService userTestService;
    @Test
    public void list() {
        List<UserTest> list = userTestService.list();
        System.out.println(list);
        Assert.notNull(list,"查询结果不能为空");
    }
}
