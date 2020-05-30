package com.zbcn.web.validate.constraint;

import com.zbcn.web.validate.self.Forbidden;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *  @title ForbiddenValidator
 *  @Description 自定义参数校验验证器: 禁止
 *  @author zbcn8
 *  @Date 2020/5/30 17:12
 */
public class ForbiddenValidator implements ConstraintValidator<Forbidden, String> {

    private String[] forbiddenWords = {"admin"};

    @Override
    public void initialize(Forbidden forbidden) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isEmpty(value)) {
            return true;
        }

        for(String word : forbiddenWords) {
            if(value.contains(word)) {
                return false;//验证失败
            }
        }
        return true;
    }
}
