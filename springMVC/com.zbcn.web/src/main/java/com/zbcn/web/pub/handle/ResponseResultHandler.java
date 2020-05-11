package com.zbcn.web.pub.handle;

import com.zbcn.common.constants.HeaderConstants;
import com.zbcn.common.enums.ApiStyleEnum;
import com.zbcn.common.response.DefaultErrorResult;
import com.zbcn.common.response.PlatformResult;
import com.zbcn.common.response.Result;
import com.zbcn.common.utils.JsonUtil;
import com.zbcn.common.utils.RequestContextHolderUtil;
import com.zbcn.web.pub.annotations.ResponseResult;
import com.zbcn.web.pub.intercepter.ResponseResultInterceptor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口响应体处理器：
 * @ControllerAdvice是在类上声明的注解，其用法主要有三点：其抽象级别应该是用于对Controller进行“切面”环绕的
 *
 * 结合方法型注解@ExceptionHandler，用于捕获Controller中抛出的指定类型的异常，从而达到不同类型的异常区别处理的目的；
 * 结合方法型注解@InitBinder，用于request中自定义参数解析方式进行注册，从而达到自定义指定格式参数的目的；
 * 结合方法型注解@ModelAttribute，表示其标注的方法将会在目标Controller方法执行之前执行。
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        HttpServletRequest request = RequestContextHolderUtil.getRequest();
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);
        return responseResultAnn != null && !ApiStyleEnum.NONE.name().equalsIgnoreCase(request.getHeader(HeaderConstants.API_STYLE));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextHolderUtil.getRequest().getAttribute(ResponseResultInterceptor.RESPONSE_RESULT);

        Class<? extends Result> resultClazz = responseResultAnn.value();

        if (resultClazz.isAssignableFrom(PlatformResult.class)) {
            if (body instanceof DefaultErrorResult) {
                DefaultErrorResult defaultErrorResult = (DefaultErrorResult) body;
                return PlatformResult.builder()
                        .code(defaultErrorResult.getCode())
                        .msg(defaultErrorResult.getMessage())
                        .data(defaultErrorResult.getErrors())
                        .build();
            } else if (body instanceof String) {
                //数据格式处理
                return JsonUtil.object2Json(PlatformResult.success(body));
            }

            return PlatformResult.success(body);
        }

        return body;
    }
}
