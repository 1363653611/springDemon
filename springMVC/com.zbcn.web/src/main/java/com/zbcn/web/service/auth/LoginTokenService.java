package com.zbcn.web.service.auth;

import com.zbcn.web.entity.dto.User;

/**
 * 权限校验service
 */
public interface LoginTokenService {

    public User getLoginUser(String token);
}
