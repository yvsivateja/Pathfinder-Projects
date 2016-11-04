package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.SaveDeviceLocationRequest;

@Component
public class DeviceLocationQueueResultSet implements
		ResultSetExtractor<List<SaveDeviceLocationRequest>> {

	public List<SaveDeviceLocationRequest> extractData(ResultSet resultSet)
			throws SQLException, DataAccessException {
		List<SaveDeviceLocationRequest> deviceLocationRequests = new ArrayList<SaveDeviceLocationRequest>();
		while (resultSet.next()) {
			SaveDeviceLocationRequest deviceLocationRequest = new SaveDeviceLocationRequest();

			deviceLocationRequest.setQueueId(resultSet.getString("id"));
			deviceLocationRequest.setCompanyId(resultSet.getString("companyid"));
			deviceLocationRequest.setAddInfo(resultSet.getString("addInfo"));
			deviceLocationRequest.setDateTriggerd(resultSet
					.getString("dateTriggerd"));
			deviceLocationRequest.setDeviceId(resultSet.getString("deviceId"));
			deviceLocationRequest.setLatitude(Double.parseDouble(resultSet
					.getString("latitude")));
			deviceLocationRequest.setLongitude(Double.parseDouble(resultSet
					.getString("longitude")));
			deviceLocationRequest.setMapURL(resultSet.getString("mapURL"));

			deviceLocationRequests.add(deviceLocationRequest);
		}
		return deviceLocationRequests;
	}

}
