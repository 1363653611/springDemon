package com.zbcn.service.impl;

import com.zbcn.mapper.IUserTestMapper;
import com.zbcn.pojo.UserTest;
import com.zbcn.service.IUserTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserTestServiceImpl implements IUserTestService {

    @Resource
    private IUserTestMapper userTestMapper;

    @Override
    public List<UserTest> list() {
        return userTestMapper.list();
    }


    public static void main(String[] args) {
        String property = System.getProperty("line.separator");
        System.out.println(property);
    }
}
