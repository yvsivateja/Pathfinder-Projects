package com.pathfinder.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserAccountDAOImpl implements UserAccountDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public void updatePassword(String encryptedPassword, String loggedInUserName) {
		String updateUserPasswordSQL = "UPDATE user SET password=:newPassword where username=:user";

		Map<String, Object> bind = new HashMap<String, Object>(2);
		bind.put("newPassword", encryptedPassword);
		bind.put("user", loggedInUserName);

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		namedParameterJdbcTemplate.update(updateUserPasswordSQL, paramSource);

	}

}
