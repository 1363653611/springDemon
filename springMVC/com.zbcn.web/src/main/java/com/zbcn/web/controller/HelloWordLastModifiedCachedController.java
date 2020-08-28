package com.zbcn.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.LastModified;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *  Spring 提供的对 `Last-Modified` 机制的支持，只需要实现LastModified 接口
 *  <br/>
 *  @author zbcn8
 *  @since  2020/7/21 9:48
 */
public class HelloWordLastModifiedCachedController extends AbstractController implements LastModified {

    private long lastModified;
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
       //点击后再次请求当前页面
        response.getWriter ().write ("<a href=''>this</a>") ;
        return null;
    }

    @Override
    public long getLastModified(HttpServletRequest request) {
        if ( lastModified == 01 ) {
            //第一次或者逻辑有变化的时候， 应该重新返回内容最新修改的时间戳
            lastModified = System.currentTimeMillis();
        }
        return lastModified;
    }
}
