package com.zbcn.jdbc.mapper;

import com.zbcn.jdbc.entity.UserTest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 表与实体之间的映射关系
 */
public class UserTestRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return new UserTest(resultSet.getInt("id"),
                resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("sex"));
    }

}
