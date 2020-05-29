package com.zbcn.web.pub.annotations;

import java.lang.annotation.*;

/**
 * 登陆校验
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginAuth {
}
