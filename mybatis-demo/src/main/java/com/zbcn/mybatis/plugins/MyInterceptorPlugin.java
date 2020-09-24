package com.zbcn.mybatis.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * `@Intercepts`：表明那个类是拦截器
 * `@Signature`：表明那类接口那个方法要拦截
 * type：要拦截的接口 只允许拦截Executor  ParameterHandler ResultSetHandler StatementHandler
 * method：拦截的方法
 * args：区分拦重载方法
 */
@Intercepts({
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MyInterceptorPlugin implements Interceptor {
    /**
     * 处理代理类方法的执行
     * intercept 方法的参数Invocation是类方法的封装。Invocation里面有target 调用的代理对象，method 调用的方法，args调用的参数
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("query 方法调用之前");
        Object result = invocation.proceed();
        System.out.println("query 方法调用之后.");
        return result;
    }

    /**
     * 确定是否要进行封装对象返回一个代理对象
     * plugin方法，obj要拦截的对象。Plugin.wrap(obj, this)判断是否要返回一个代理对象。
     * @param target
     * @returnr
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("拦截目标对象："+ target.getClass().getName());
        return Plugin.wrap(target, this);
    }

    /**
     * 设置参数获取plugin元素里面的properties的值
     * setProperties,就是获取plugin元素下定义的属性值
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        String name = properties.getProperty("important");
        System.out.println(name);
    }
}
