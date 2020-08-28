package com.zbcn.web.pub.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *  自定义拦截器：记录请求的执行时间
 *  <br/>
 *  @author zbcn8
 *  @since  2020/7/21 10:16
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        //true 表示允许DispatcherServlet 继续处理请求。否则，DispatcherServlet 会认为这个方法已经处理了请求，直接将响应返回给用户
        return true ;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime= System.currentTimeMillis( );
        modelAndView.addObject("handleTime", endTime-startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
