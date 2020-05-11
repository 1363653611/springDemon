package com.zbcn.web.pub.annotations;

import com.zbcn.common.response.PlatformResult;
import com.zbcn.common.response.Result;

import java.lang.annotation.*;

/**
 *  接口返回结果增强  会通过拦截器拦截后放入标记，在WebResponseBodyHandler进行结果处理
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  ResponseResult {

    Class<? extends Result> value() default PlatformResult.class;
}
