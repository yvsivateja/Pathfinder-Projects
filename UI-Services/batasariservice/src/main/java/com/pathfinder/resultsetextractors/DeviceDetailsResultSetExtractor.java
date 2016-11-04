package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.DeviceDTO;

@Component
public class DeviceDetailsResultSetExtractor implements
		ResultSetExtractor<List<DeviceDTO>> {

	public List<DeviceDTO> extractData(ResultSet resultSet)
			throws SQLException, DataAccessException {
		List<DeviceDTO> deviceDTOs = new ArrayList<DeviceDTO>();
		while (resultSet.next()) {
			DeviceDTO deviceDTO = new DeviceDTO();

			deviceDTO.setAddtionalInfo(resultSet.getString("addtionalInfo"));
			deviceDTO.setCanStart(resultSet.getBoolean("canstart"));
			deviceDTO.setCanStop(resultSet.getBoolean("canstop"));
			deviceDTO.setCanUninstall(resultSet.getBoolean("canuninstall"));
			deviceDTO.setCompanyID(resultSet.getString("companyid"));
			deviceDTO
					.setCompanyShortKey(resultSet.getString("companyshortkey"));
			deviceDTO.setCreatedTime(resultSet.getDate("createdtime"));
			deviceDTO.setDeviceID(resultSet.getString("deviceid"));
			deviceDTO.setId(resultSet.getString("id"));
			deviceDTO.setDeviceName(resultSet.getString("devicename"));
			deviceDTO.setEmployeeID(resultSet.getString("employeeid"));
			deviceDTO.setPhoneNumber(resultSet.getString("phonenumber"));
			deviceDTO.setUserIdentifier(resultSet.getString("useridentifier"));
			deviceDTO.setApproved(resultSet.getBoolean("approved"));
			deviceDTO.setApprovedBy(resultSet.getString("approvedby"));
			

			deviceDTOs.add(deviceDTO);
		}
		return deviceDTOs;
	}

}
