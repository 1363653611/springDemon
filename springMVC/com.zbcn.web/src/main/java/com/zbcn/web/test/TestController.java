package com.zbcn.web.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("index")
    public String index(){

        return "welcome to SpringMVC";
    }

    @GetMapping("log")
    public String log(){
       log.error("测试日志。。");

       return "success";
    }
}
