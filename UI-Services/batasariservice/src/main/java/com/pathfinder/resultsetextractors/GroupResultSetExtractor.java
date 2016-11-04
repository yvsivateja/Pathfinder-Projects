package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.GroupRegistrationDTO;
import com.pathfinder.dto.GroupsResultDTO;

@Component
public class GroupResultSetExtractor implements
		ResultSetExtractor<GroupsResultDTO> {

	public GroupsResultDTO extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		GroupsResultDTO groupsResultDTO = new GroupsResultDTO();
		List<GroupRegistrationDTO> groupRegistrationDTOs = new ArrayList<GroupRegistrationDTO>();
		while (rs.next()) {
			GroupRegistrationDTO groupRegistrationDTO = new GroupRegistrationDTO();
			// companyid,companyname,status,expirydate
			groupRegistrationDTO.setGroupid(rs.getString("companyid"));
			groupRegistrationDTO.setGroupName(rs.getString("companyname"));
			groupRegistrationDTO.setShortKey(rs.getString("shortkey"));
			groupRegistrationDTO.setAddress1(rs.getString("address1"));
			groupRegistrationDTO.setAddress2(rs.getString("address2"));
			groupRegistrationDTO.setAddress3(rs.getString("address3"));
			groupRegistrationDTO.setCity(rs.getString("city"));
			groupRegistrationDTO.setCountry(rs.getString("country"));
			groupRegistrationDTO.setState(rs.getString("state"));
			groupRegistrationDTO.setAdditionalInfo(rs.getString("notes"));
			groupRegistrationDTOs.add(groupRegistrationDTO);
		}
		System.out.println("No of companies retrieved : "
				+ groupRegistrationDTOs.size());
		groupsResultDTO.setGroupRegistrationDTOs(groupRegistrationDTOs);
		return groupsResultDTO;
	}
}
