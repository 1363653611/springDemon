package com.zbcn.base.aop.bean;

import com.zbcn.base.aop.annotation.Log;
import org.springframework.stereotype.Component;

@Component
public class LogTestImpl implements ILogTest {

    @Log
    @Override
    public void doLog() {
        System.out.println("接口方法");
    }

    @Log
    public void doLog2(){
        System.out.println("非接口饭噶霏");
    }
}
