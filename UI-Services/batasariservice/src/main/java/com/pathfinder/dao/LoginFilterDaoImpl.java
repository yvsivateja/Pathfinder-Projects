package com.pathfinder.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.pathfinder.filter.SessionBean;
import com.pathfinder.resultsetextractors.SessionBeanResultSetExtractor;

@Component
public class LoginFilterDaoImpl implements LoginFilterDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	SessionBeanResultSetExtractor sessionBeanResultSetExtractor;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public SessionBean getSessionDetails(String userName) {
		String queryForPermissionsOfLoggedInUser = "SELECT * FROM userrolespermissionsview r "
				+ "where r.username=:username";

		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("username", userName);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		SessionBean sessionBean = namedParameterJdbcTemplate.query(
				queryForPermissionsOfLoggedInUser, paramSource,
				sessionBeanResultSetExtractor);
		return sessionBean;

	}

}
