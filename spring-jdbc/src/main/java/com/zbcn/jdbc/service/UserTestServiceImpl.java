package com.zbcn.jdbc.service;

import com.zbcn.jdbc.entity.UserTest;
import com.zbcn.jdbc.mapper.UserTestRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;
import java.util.List;

public class UserTestServiceImpl implements IUserTestService {

    private JdbcTemplate jdbcTemplate;

    /**
     * 设置数据源
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(UserTest userTest) {
        jdbcTemplate.update("insert into user_test(name,age, sex) values(?,?,?)",
                //参数
                new Object[]{
                        userTest.getName(),
                        userTest.getAge(),
                        userTest.getSex()
                },
                //参数类型
                new int[]{
                        Types.VARCHAR,
                        Types.INTEGER,
                        Types.VARCHAR
                });
    }

    @Override
    public List<UserTest> getUsers() {
        return jdbcTemplate.query("select * from user_test", new UserTestRowMapper());
    }
}
