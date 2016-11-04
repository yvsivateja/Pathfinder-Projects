package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.UserRegistrationDTO;
import com.pathfinder.dto.UsersResultDTO;

@Component
public class UsersResultSetExtractor implements
		ResultSetExtractor<UsersResultDTO> {

	public UsersResultDTO extractData(ResultSet resultSet) throws SQLException,
			DataAccessException {
		UsersResultDTO usersResultDTO = new UsersResultDTO();
		List<UserRegistrationDTO> userRegistrationDTOs = new ArrayList<UserRegistrationDTO>();
		while (resultSet.next()) {
			UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();

			userRegistrationDTO.setUserId(resultSet.getString("userid"));
			userRegistrationDTO.setUsername(resultSet.getString("username"));
			userRegistrationDTO.setRolesDisplay(resultSet
					.getString("rolenames"));
			userRegistrationDTO.setEmployeeName(resultSet
					.getString("employeename"));

			userRegistrationDTOs.add(userRegistrationDTO);
		}
		usersResultDTO.setUserResults(userRegistrationDTOs);
		return usersResultDTO;
	}

}
