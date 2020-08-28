package com.zbcn.service;

import com.zbcn.pojo.UserTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 *  @title IUserTestService
 *  @Description 查询
 *  @author zbcn8
 *  @Date 2020/5/12 10:19
 */
@Transactional(rollbackFor = RuntimeException.class)
public interface IUserTestService {

    List<UserTest> list();
}
