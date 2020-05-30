package com.zbcn.web.validate.self;

import com.zbcn.web.validate.constraint.ForbiddenValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 *  @title Forbidden
 *  @Description 自定义验证规则
 *  @author zbcn8
 *  @Date 2020/5/30 16:10
 */
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE,ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ForbiddenValidator.class)
@Documented
public @interface Forbidden {

    //默认错误消息
    String message() default "{forbidden.word}";

    //分组
    Class<?>[] groups() default { };

    //负载
    Class<? extends Payload>[] payload() default { };

    //指定多个时使用
    @Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Forbidden[] value();
    }
}
