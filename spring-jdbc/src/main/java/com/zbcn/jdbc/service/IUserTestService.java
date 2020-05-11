package com.zbcn.jdbc.service;

import com.zbcn.jdbc.entity.UserTest;

import java.util.List;

/**
 * 操作service
 */
public interface IUserTestService {

    public void save(UserTest userTest);

    public List<UserTest> getUsers();
}
