package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.UserProfileDTO;

@Component
public class UserProfileResultSetExtractor implements
		ResultSetExtractor<UserProfileDTO> {

	public UserProfileDTO extractData(ResultSet resultSet) throws SQLException,
			DataAccessException {
		UserProfileDTO userProfileDTO = new UserProfileDTO();

		while (resultSet.next()) {
			// u.userid,u.username,u.password,u.companyid,u.employeeid,u.issuperadmin,e.employeename,
			// e.age,e.sex,e.phone,e.emailid,e.notes

			userProfileDTO.setAdditionalInfo(resultSet.getString("notes"));
			userProfileDTO.setAge(resultSet.getString("age"));
			userProfileDTO.setEmpEmailid(resultSet.getString("emailid"));
			userProfileDTO.setEmployeeId(resultSet.getString("employeeid"));
			userProfileDTO.setEmpName(resultSet.getString("employeename"));
			userProfileDTO.setEmpPhone(resultSet.getString("phone"));
			userProfileDTO.setGroupId(resultSet.getString("companyid"));
			userProfileDTO.setPassword(resultSet.getString("password"));
			userProfileDTO.setRepassword(resultSet.getString("password"));
			userProfileDTO.setSex(resultSet.getString("sex"));
			userProfileDTO.setUserId(resultSet.getString("userid"));
			userProfileDTO.setUsername(resultSet.getString("username"));
		}
		return userProfileDTO;
	}

}
