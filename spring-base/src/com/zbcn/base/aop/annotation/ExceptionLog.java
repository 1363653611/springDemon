package com.zbcn.base.aop.annotation;

import java.lang.annotation.*;

/**
 *  @title ExceptionLog
 * 说明：（ 该注解使用spring aop功能（代理模式））
 * 1. 该注解只能使用在spring 容器锁管理的bean上
 * 2. 如果类内部方法之间调用，被调用的方法的注解不生效 。eg: bean.A 调用 bean.B 方法，则该注解在B方法上不起作用
 * 3. 私有方法不生效
 * 4. 该类实现接口，则非接口声明的方法不生效
 *
 *  @author zbcn8
 *  @Date 2020/6/17 10:10
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionLog {

    /**
     *
     * @return
     */
    String moduleName();

    /**
     * 默认值
     */
    String value() default "";

    /**
     * 异常信息
     * @return
     */
    Class<? extends Throwable> exception() default Exception.class;
}
