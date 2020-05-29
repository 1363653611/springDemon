package com.zbcn.web.pub.intercepter;

import com.zbcn.common.constants.HeaderConstants;
import com.zbcn.common.enums.ResultCode;
import com.zbcn.common.exceptions.BusinessException;
import com.zbcn.web.entity.dto.User;
import com.zbcn.web.pub.annotations.LoginAuth;
import com.zbcn.web.pub.helper.LoginHelper;
import com.zbcn.web.service.auth.LoginTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 *  @title LoginInterceptor
 *  @Description 登录校验
 *  @author zbcn8
 *  @Date 2020/5/29 14:27
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginTokenService loginTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       if(handler instanceof HandlerMethod){
           final HandlerMethod handlerMethod = (HandlerMethod)handler;
           Class<?> beanType = handlerMethod.getBeanType();
           Method method = handlerMethod.getMethod();
           if(beanType.isAnnotationPresent(LoginAuth.class) || method.isAnnotationPresent(LoginAuth.class)){
               //直接获取登录用户（防止请求转发时，第二次查询）
               User loginUser = LoginHelper.getLoginUserFromRequest(request);
               if(Objects.nonNull(loginUser)){
                   return true;
               }

               //获取登录账号名
               String loginAccount = LoginHelper.getLoginAccount(request);
               if(StringUtils.isBlank(loginAccount)){
                   throw new  BusinessException(ResultCode.USER_NOT_LOGGED_IN);
               }
               //获取登录人信息
               loginUser = loginTokenService.getLoginUser(loginAccount);
               if (Objects.isNull(loginUser)){
                   throw new BusinessException(ResultCode.USER_NOT_EXIST);
               }
               //登录用户信息放入请求对象，方便后续controller中获取

               LoginHelper.setLoginUser2Request(request,loginUser);
           }
           return true;
       }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
