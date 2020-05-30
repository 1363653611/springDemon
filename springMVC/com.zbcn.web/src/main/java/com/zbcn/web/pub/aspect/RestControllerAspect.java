package com.zbcn.web.pub.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zbcn.common.constants.HeaderConstants;
import com.zbcn.common.utils.IpUtils;
import com.zbcn.web.entity.dto.User;
import com.zbcn.web.pub.handle.GlobalExceptionHandler;
import com.zbcn.web.pub.helper.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 *  @title RestControllerAspect
 *  @Description 请求参数、响应体统一日志打印
 *  @author zbcn8
 *  @Date 2020/5/29 17:35
 */
@Aspect
@Component
@Slf4j
public class RestControllerAspect {

    @Around("@within(org.springframework.web.bind.annotation.RestController) || @annotation(org.springframework.web.bind.annotation.RestController)")
    public Object apiLog(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature  =  (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        if(!need2Log(method)){
            return joinPoint;
        }

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        User user = LoginHelper.getLoginUserFromRequest(request);
        String realIp = IpUtils.getRealIp(request);
        String methodName = getMethodName(joinPoint);
        String params = getParamsJson(joinPoint);
        String requester = Objects.isNull(user)? "unknown" : String.valueOf(user.getId());
        //请求头信息
        String callSource = request.getHeader(HeaderConstants.CALL_SOURCE);
        String apiStyle = request.getHeader(HeaderConstants.API_STYLE);
        String apiVersion = request.getHeader(HeaderConstants.API_VERSION);
        String appVersion = request.getHeader(HeaderConstants.APP_VERSION);
        String userAgent = request.getHeader("user-agent");
        log.info("Started request requester [{}] method [{}] params [{}] IP [{}] callSource [{}] apiStyle [{}] appVersion [{}] apiVersion [{}] userAgent [{}]",
                                        requester, methodName, params, realIp, callSource, apiStyle, appVersion, apiVersion, userAgent);

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        log.info("Ended request requester [{}] method [{}] params [{}] IP [{}] callSource [{}] apiStyle [{}] appVersion [{}] apiVersion [{}] userAgent [{}] response [{}] cost [{}] millis ",
                requester, methodName, params, realIp, callSource, apiStyle, appVersion, apiVersion, userAgent, proceed, System.currentTimeMillis() - start);
        return proceed;
    }

    /**
     * 获取方法名称
     * @param joinPoint
     * @return
     */
    private String getMethodName(ProceedingJoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();

        String SHORT_METHOD_NAME_SUFFIX = "(..)";
        if(methodName.endsWith(SHORT_METHOD_NAME_SUFFIX)){
            methodName = methodName.substring(0,methodName.length() -SHORT_METHOD_NAME_SUFFIX.length());
        }
        return methodName;
    }

    public String getParamsJson(ProceedingJoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        StringBuilder builder = new StringBuilder();
        for (Object arg : args){
            //解除敏感信息
            String paramStr = null;
            if(arg instanceof HttpServletResponse){
                paramStr = HttpServletResponse.class.getSimpleName();
            }else if(arg instanceof  HttpServletRequest){
                paramStr = HttpServletRequest.class.getSimpleName();
            }else if(arg instanceof MultipartFile){
                long size = ((MultipartFile) arg).getSize();
                paramStr = MultipartFile.class.getSimpleName() + "size:" + size;
            }else{
                //删除敏感信息
                paramStr = this.deleteSensitiveContent(arg);
            }
            builder.append(paramStr).append(",");
        }
        return builder.deleteCharAt(builder.length()-1).toString();
    }

    /**
     * 删除参数中的敏感信息
     * @param arg
     * @return
     */
    private String deleteSensitiveContent(Object arg) {
        JSONObject json = new JSONObject();
        if(Objects.isNull(arg) || arg instanceof Exception){
                return  json.toJSONString();
        }
        try {
            String param = JSON.toJSONString(arg);
            json = JSONObject.parseObject(param);
            List<String> sensitiveFieldList = getSensitiveFieldList();
            for (String sensitiveField :sensitiveFieldList){
                if(json.containsKey(sensitiveField)){
                    json.replace(sensitiveField, "***");
                }
            }
        } catch (Exception e) {
           log.error("参数处理异常", e);
           return String.valueOf(arg);
        }
        return json.toJSONString();
    }

    /**
     * 敏感字段列表（当然这里你可以更改为可配置的）
     * @return
     */
    private List<String> getSensitiveFieldList() {
        List<String> sensitiveFieldList = Lists.newArrayList();
        sensitiveFieldList.add("pwd");
        sensitiveFieldList.add("password");
        return sensitiveFieldList;
    }


    /**
     * 判断是否需要记录日志
     * @param method
     * @return
     */
    private boolean need2Log(Method method){
        return (Objects.isNull(method.getAnnotation(GetMapping.class))
                || Objects.isNull(method.getAnnotation(PostMapping.class))
                || Objects.isNull(method.getAnnotation(PutMapping.class))
                || Objects.isNull(method.getAnnotation(DeleteMapping.class))
                || Objects.isNull(method.getAnnotation(PatchMapping.class))
        ) && !method.getDeclaringClass().equals(GlobalExceptionHandler.class);
    }
}
