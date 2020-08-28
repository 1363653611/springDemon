package com.zbcn.web.controller;

import com.zbcn.common.enums.ResultCode;
import com.zbcn.common.exceptions.BusinessException;
import com.zbcn.web.entity.dto.User;
import com.zbcn.web.pub.annotations.LoginAuth;
import com.zbcn.web.pub.annotations.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@ResponseResult
@RestController
@RequestMapping("/login")
public class LoginController {

    @LoginAuth
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
