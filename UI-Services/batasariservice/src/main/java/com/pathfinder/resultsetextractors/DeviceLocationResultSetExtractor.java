package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.DeviceLocationDTO;

@Component
public class DeviceLocationResultSetExtractor implements
		ResultSetExtractor<List<DeviceLocationDTO>> {

	public List<DeviceLocationDTO> extractData(ResultSet resultSet)
			throws SQLException, DataAccessException {
		List<DeviceLocationDTO> deviceLocationDTOs = new ArrayList<DeviceLocationDTO>();
		while (resultSet.next()) {
			DeviceLocationDTO deviceLocationDTO = new DeviceLocationDTO();

			deviceLocationDTO.setAddInfo(resultSet.getString("addInfo"));
			deviceLocationDTO.setAddress(resultSet.getString("address"));
			deviceLocationDTO.setCompanyId(resultSet.getString("companyid"));
			deviceLocationDTO.setCreatedTimestamp(resultSet
					.getTimestamp("created_timestamp"));
			deviceLocationDTO
					.setDateTriggerd(resultSet.getTimestamp("dateTriggerd"));
			deviceLocationDTO.setDeviceId(resultSet.getString("deviceid"));
			deviceLocationDTO.setDistance(resultSet.getString("distance"));
			deviceLocationDTO.setId(resultSet.getString("id"));
			deviceLocationDTO.setLatitude(resultSet.getString("latitude"));
			deviceLocationDTO.setLongitude(resultSet.getString("longitude"));
			deviceLocationDTO.setMapURL(resultSet.getString("mapURL"));
			deviceLocationDTO.setPrevlatitude(resultSet
					.getString("prevlatitude"));
			deviceLocationDTO.setPrevlongitude(resultSet
					.getString("prevlongitude"));

			deviceLocationDTOs.add(deviceLocationDTO);
		}
		return deviceLocationDTOs;
	}

}
