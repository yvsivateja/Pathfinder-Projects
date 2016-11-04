package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

@Component
public class AvailableRoleResultSetExtractor implements
		ResultSetExtractor<Map<String, String>> {

	public Map<String, String> extractData(ResultSet resultSet)
			throws SQLException, DataAccessException {
		Map<String, String> roleMap = new LinkedHashMap<String, String>();
		while (resultSet.next()) {
			String mapKey = resultSet.getString("roleid");
			String mapValue = resultSet.getString("rolename");
			
			roleMap.put(mapKey, mapValue);
		}
		return roleMap;
	}
}
