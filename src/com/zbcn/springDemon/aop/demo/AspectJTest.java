package com.zbcn.springDemon.aop.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 定义切面,切入点和切入点逻辑
 */
@Aspect
@Component
public class AspectJTest {

    /**
     * 定义切面
     */
    @Pointcut("execution(* *.test(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void beforeTest(){
        System.out.println("方法执行之前...");
    }

    @After("pointCut()")
    public void afterTest(){
        System.out.println("方法执行之后....");
    }

    @Around("pointCut()")
    public Object aroundTest(ProceedingJoinPoint joinPoint){
        System.out.println("around 执行之前");
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around 方法执行完");
        return proceed;
    }


}
