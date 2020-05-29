package com.zbcn.web.service.auth.impl;

import com.zbcn.web.entity.dto.User;
import com.zbcn.web.service.auth.LoginTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginTokenServiceImpl implements LoginTokenService {
    @Override
    public User getLoginUser(String token) {
        if(StringUtils.equals(token, "123456")){
            User user = new User();
            user.setId(123456L);
            user.setCreateTime(new Date());
            return user;
        }
        return null;
    }
}
