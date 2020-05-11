package com.zbcn.web.test;

import com.zbcn.common.enums.ResultCode;
import com.zbcn.common.exceptions.BusinessException;
import com.zbcn.common.response.PlatformResult;
import com.zbcn.web.entity.dto.User;
import com.zbcn.web.pub.annotations.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * 测试返回值封装类:
 * Result 是返回格式类的父接口（所有返回格式类都需要继承它）
 * PlatformResult 通用返回结果格式（我们上面说的第二种返回结果）
 * DefaultErrorResult 全局错误返回结果（我们上面说的第一种错误时的返回结果）
 * GlobalExceptionHandler全局异常处理
 * ResponseResult 注解类（用于在Controller上指定返回值格式类）
 * ResponseResultInterceptor 拦截器（主要用于将ResponseResult注解类的标记信息传入ResponseResultHandler中）
 * ResponseResultHandler 响应体格式处理器（主要转换逻辑都在这里）
 */
@RestController
@ResponseResult(PlatformResult.class)
@RequestMapping("/users")
public class UserController {

    /**
     * 说明：@RequestBody 接受的数据的context-type 必须为：application/json.其他的参数都会报参数类型错误
     * @param user
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Validated @RequestBody User user) {
        //user.setId(10000L);
        user.setCreateTime(new Date());
        if(StringUtils.equals(String.valueOf(user.getId()),"11")){
            throw new BusinessException(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
        }
        return user;
    }
}
