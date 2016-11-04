package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.UserRegistrationDTO;

@Component
public class RetrieveUserRoleResultSetExtractor implements
		ResultSetExtractor<UserRegistrationDTO> {

	public UserRegistrationDTO extractData(ResultSet resultSet)
			throws SQLException, DataAccessException {

		UserRegistrationDTO userRegistrationDTO = null;
		Map<String, String> notSelectedRoles = new HashMap<String, String>();
		Map<String, String> selectedRoles = new HashMap<String, String>();
		String userId = "";
		String userName = "";
		String employeeName = "";
		while (resultSet.next()) {

			// r.roleid,r.rolename,ur.userid,u.username,e.employeename
			if (checkIsEmpty(userId)) {
				userId = resultSet.getString("userid");
				userName = resultSet.getString("username");
				employeeName = resultSet.getString("employeename");
			} else {
				System.out.println("Not Empty");
			}
			String key = resultSet.getString("roleid");
			String value = resultSet.getString("rolename");
			if (checkIsEmpty(resultSet.getString("userid"))) {
				notSelectedRoles.put(key, value);
			} else {
				selectedRoles.put(key, value);
			}

		}
		System.out.println(userId);
		userRegistrationDTO = new UserRegistrationDTO();
		userRegistrationDTO.setUsername(userName);
		userRegistrationDTO.setUserId(userId);
		userRegistrationDTO.setEmployeeName(employeeName);
		userRegistrationDTO.setAvailableRolesTODisplay(notSelectedRoles);
		userRegistrationDTO.setSelectedRolesTODisplay(selectedRoles);

		return userRegistrationDTO;
	}

	private boolean checkIsEmpty(String value) {
		try {
			if (!value.isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (NullPointerException ex) {
			return true;
		}
	}
}
