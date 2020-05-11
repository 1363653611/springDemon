/**
 * 
 */
package com.zbcn.batch.mapper;

import com.zbcn.batch.pojo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author kunrey
 *
 */
public class UserDbMapper implements RowMapper {
	
	public Object mapRow(ResultSet rs, int i) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("ID"));
		user.setName(rs.getString("NAME"));
		user.setAge(rs.getInt("AGE"));
		user.setBalance(rs.getDouble("BALANCE"));
		return user;
	}

}
