package com.zbcn.web.pub.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  处理映射或者业务处理期间发生异常
 *  <br/>
 *  @author zbcn8
 *  @since  2020/7/20 16:32
 */
@Component
public class MyExceptionHandler implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        request.setAttribute("exception", ex.toString()) ;
        request.setAttribute ("exceptionStack", ex) ;
        log.error ( ex.toString(), ex) ;
        return new ModelAndView( "error/exception");
    }
}
