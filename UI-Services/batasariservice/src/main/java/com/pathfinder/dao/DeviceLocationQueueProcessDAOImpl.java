package com.pathfinder.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.SaveDeviceLocationRequest;
import com.pathfinder.resultsetextractors.DeviceLocationQueueResultSet;
import com.pathfinder.resultsetextractors.DeviceLocationRequestResultSet;
import com.pathfinder.util.UserAccessManagementUtil;

@Component
public class DeviceLocationQueueProcessDAOImpl implements
		DeviceLocationQueueProcessDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	DeviceLocationQueueResultSet deviceLocationQueueResultSet;

	@Autowired
	DeviceLocationRequestResultSet deviceLocationRequestResultSet;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public void processQueue() throws Exception {
		String retrieveDeviceLocationQueueSQL = "SELECT dlq.id,d.companyid,dlq.deviceid,dlq.latitude,dlq.longitude,"
				+ "dlq.mapURL,dlq.addInfo,dlq.dateTriggerd "
				+ "FROM devicelocationqueue dlq JOIN device d ON d.deviceid=dlq.deviceid "
				+ "order by dlq.deviceid,dlq.dateTriggerd";

		List<SaveDeviceLocationRequest> deviceLocationRequests = namedParameterJdbcTemplate
				.query(retrieveDeviceLocationQueueSQL,
						deviceLocationQueueResultSet);

		System.out.println("Location Empty : "
				+ deviceLocationRequests.isEmpty());
		if (!deviceLocationRequests.isEmpty()) {
			if (deviceLocationRequests.size() > 0) {
				System.out.println("Processing "
						+ deviceLocationRequests.size() + " record(s)");
				for (SaveDeviceLocationRequest deviceLocationRequest : deviceLocationRequests) {
					String currentAddress = UserAccessManagementUtil
							.getGeoCodeAddress(deviceLocationRequest
									.getLatitude().toString(),
									deviceLocationRequest.getLongitude()
											.toString());
					SaveDeviceLocationRequest previousdeviceLocation = getDevicePreviousRecord(deviceLocationRequest
							.getDeviceId());
					deviceLocationRequest.setAddress(currentAddress);
					if (!currentAddress.contentEquals(previousdeviceLocation
							.getAddress())) {

						insertDeviceLocation(deviceLocationRequest,
								previousdeviceLocation);

						deleteRecord(deviceLocationRequest);

					} else {
						deleteRecord(deviceLocationRequest);
					}

				}
			}
		}

	}

	private void insertDeviceLocation(
			SaveDeviceLocationRequest deviceLocationRequest,
			SaveDeviceLocationRequest previousdeviceLocation) {

		String distance = UserAccessManagementUtil.haverSineDistance(
				deviceLocationRequest.getLatitude().toString(),
				deviceLocationRequest.getLongitude().toString(),
				previousdeviceLocation.getLatitude().toString(),
				previousdeviceLocation.getLongitude().toString());

		String insertDeviceLocationSQL = "INSERT into devicelocations (deviceid,companyid,latitude,longitude,mapURL,dateTriggerd,"
				+ "prevlatitude,prevlongitude,distance,address) "
				+ "values (:deviceid,:companyid,:latitude,:longitude,:mapURL,:dateTriggerd,:prevlatitude,:prevlongitude,:distance,:address)";

		Map<String, Object> bind = new HashMap<String, Object>(11);
		bind.put("deviceid", deviceLocationRequest.getDeviceId());
		bind.put("companyid", deviceLocationRequest.getCompanyId());
		bind.put("latitude", deviceLocationRequest.getLatitude());
		bind.put("longitude", deviceLocationRequest.getLongitude());
		bind.put("mapURL", deviceLocationRequest.getMapURL());
		bind.put("dateTriggerd",
				parseDateFormat(deviceLocationRequest.getDateTriggerd()));
		bind.put("prevlatitude", previousdeviceLocation.getLatitude());
		bind.put("prevlongitude", previousdeviceLocation.getLongitude());
		bind.put("distance", distance);
		bind.put("address", deviceLocationRequest.getAddress());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		namedParameterJdbcTemplate.update(insertDeviceLocationSQL, paramSource);
	}

	private Date parseDateFormat(String date) {

		SimpleDateFormat inputFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		System.out.println("Date to parse : " + date);
		try {
			//String formattedDate = inputFormat.format(date);
			return inputFormat.parse(date);
		} catch (ParseException e) {
			return new Date();
		}

	}

	private void deleteRecord(SaveDeviceLocationRequest deviceLocationRequest) {
		String deleteLocationQueueSQL = "delete from devicelocationqueue where id=:id";

		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("id", deviceLocationRequest.getQueueId());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		namedParameterJdbcTemplate.update(deleteLocationQueueSQL, paramSource);

	}

	private SaveDeviceLocationRequest getDevicePreviousRecord(String deviceId) {
		String retrieveDeviceLocationQueueSQL = "SELECT * FROM devicelocations where deviceid=:deviceid and DATE(created_timestamp)=CURDATE() order by created_timestamp desc LIMIT 1";
		Map<String, Object> bind = new HashMap<String, Object>(2);

		bind.put("deviceid", deviceId);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		SaveDeviceLocationRequest previousdeviceLocation = namedParameterJdbcTemplate
				.query(retrieveDeviceLocationQueueSQL, paramSource,
						deviceLocationRequestResultSet);

		return previousdeviceLocation;

	}

}
