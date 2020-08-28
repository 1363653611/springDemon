package com.zbcn.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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

    /**
     * 测试自定义监听器
     * @param request
     * @return
     */
    @GetMapping("listener")
    public String listener(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Object myData = servletContext.getAttribute("myData");
        log.info("测试自定义监听器"+ myData);
        return (String)myData;
    }
}
