package com.zbcn.springDemon.aop.demo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 测试实例
 */
@Data
@Component
public class TestBean {
    private String testStr = "testStr";

    public void test(){
        System.out.println("test");
    }

}
