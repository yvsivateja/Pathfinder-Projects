package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.SaveDeviceLocationRequest;

@Component
public class DeviceLocationRequestResultSet implements
		ResultSetExtractor<SaveDeviceLocationRequest> {

	public SaveDeviceLocationRequest extractData(ResultSet resultSet)
			throws SQLException, DataAccessException {
		SaveDeviceLocationRequest deviceLocationRequest = new SaveDeviceLocationRequest();

		setDefaultValues(deviceLocationRequest);

		while (resultSet.next()) {
			deviceLocationRequest.setAddInfo(resultSet.getString("addInfo"));
			deviceLocationRequest.setAddress(resultSet.getString("address"));
			deviceLocationRequest.setDateTriggerd(resultSet
					.getString("dateTriggerd"));
			deviceLocationRequest.setDeviceId(resultSet.getString("deviceId"));
			deviceLocationRequest.setLatitude(resultSet.getDouble("latitude"));
			deviceLocationRequest
					.setLongitude(resultSet.getDouble("longitude"));
			deviceLocationRequest.setMapURL(resultSet.getString("mapURL"));
		}
		return deviceLocationRequest;
	}

	private void setDefaultValues(
			SaveDeviceLocationRequest deviceLocationRequest) {
		deviceLocationRequest.setAddInfo("Default Values");
		deviceLocationRequest.setAddress("Not Known");
		deviceLocationRequest.setLatitude(Double.valueOf("0.0"));
		deviceLocationRequest.setLongitude(Double.valueOf("0.0"));
	}
}
