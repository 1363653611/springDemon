package com.zbcn.web.env.prod;

import com.zbcn.web.env.EnvService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:dispatcher-servlet.xml"})

@WebAppConfiguration
/*
 * 使用注册来完成对profile的激活,
 * 传入对应的profile名字即可,可以传入produce或者dev
 */
@ActiveProfiles("prod")
public class ProdServiceDemonTest {

    @Resource
    private EnvService envService;

    @Test
    public void getEnv() {
        System.out.println(envService.getEnv());
    }
}