package com.pathfinder.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.SaveDeviceLocationRequest;
import com.pathfinder.dto.WSDeviceAuthenticateRequest;
import com.pathfinder.dto.WSDeviceAuthenticateResponse;
import com.pathfinder.exception.BatasariWSException;
import com.pathfinder.resultsetextractors.DeviceDetailsResultSetExtractor;

@Component
public class ManageDeviceWSDAOImpl implements ManageDeviceWSDAO {

	@Autowired
	private DeviceDetailsResultSetExtractor deviceDetailsResultSetExtractor;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public WSDeviceAuthenticateResponse authAndRegisterDevice(
			WSDeviceAuthenticateRequest wsDeviceAuthenticateRequest)
			throws BatasariWSException {

		
		Integer companyId = getCompanyId(wsDeviceAuthenticateRequest
				.getCompanyIdentifier());
		
		WSDeviceAuthenticateResponse wsDeviceAuthenticateResponse = null;
		if (companyId != 0) {

			wsDeviceAuthenticateResponse = getDeviceDetailsIfExists(wsDeviceAuthenticateRequest
					.getDeviceID());
			if (wsDeviceAuthenticateResponse == null) {
				wsDeviceAuthenticateResponse = registerNewDevice(
						wsDeviceAuthenticateRequest, companyId);
			}
		} else {
		    
			throw new BatasariWSException("101", "Invalid Company Identifier",
					false);
		}

		return wsDeviceAuthenticateResponse;
	}

	private WSDeviceAuthenticateResponse registerNewDevice(
			WSDeviceAuthenticateRequest wsDeviceAuthenticateRequest,
			Integer companyId) {

		String deviceInsertSQL = "insert into device (deviceid,devicename,phonenumber,companyshortkey,"
				+ "useridentifier,addtionalInfo,companyid) "
				+ "values (:deviceid,:devicename,:phonenumber,:companyshortkey,"
				+ ":useridentifier,:addtionalInfo,:companyid)";

		Map<String, Object> bind = new HashMap<String, Object>(8);
		bind.put("deviceid", wsDeviceAuthenticateRequest.getDeviceID());
		bind.put("devicename", wsDeviceAuthenticateRequest.getDeviceName());
		bind.put("phonenumber", wsDeviceAuthenticateRequest.getPhoneNumber());
		bind.put("companyshortkey",
				wsDeviceAuthenticateRequest.getCompanyIdentifier());
		bind.put("useridentifier",
				wsDeviceAuthenticateRequest.getUserIdentifier());
		bind.put("addtionalInfo",
				wsDeviceAuthenticateRequest.getAddtionalInfo());
		bind.put("companyid", companyId);

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		int devicesInserted = namedParameterJdbcTemplate.update(
				deviceInsertSQL, paramSource);
		System.out.println("devices inserted : " + devicesInserted);
		WSDeviceAuthenticateResponse wsDeviceAuthenticateResponse = new WSDeviceAuthenticateResponse();
		wsDeviceAuthenticateResponse.setSuccess(true);
		wsDeviceAuthenticateResponse.setMessage("device inserted");
		wsDeviceAuthenticateResponse.setCanStart(false);
		wsDeviceAuthenticateResponse.setCanStop(false);
		wsDeviceAuthenticateResponse.setCanUninstall(false);

		return wsDeviceAuthenticateResponse;
	}

	private WSDeviceAuthenticateResponse getDeviceDetailsIfExists(
			String deviceID) {
		String getDeviceDetailsSQL = "Select * from device where deviceid=:deviceid";

		Map<String, Object> bind = new HashMap<String, Object>(2);
		bind.put("deviceid", deviceID);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		WSDeviceAuthenticateResponse wsDeviceAuthenticateResponse = namedParameterJdbcTemplate
				.query(getDeviceDetailsSQL, paramSource,
						deviceDetailsResultSetExtractor);
		return wsDeviceAuthenticateResponse;
	}

	private Integer getCompanyId(String companyIdentifier) {
		Integer result = 0;
		try{
			String checkCompanyIdentifierSQL = "Select companyid from company where shortkey=:shortkey";

			Map<String, Object> bind = new HashMap<String, Object>(2);
			bind.put("shortkey", companyIdentifier);
			SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		
			Integer companyId = namedParameterJdbcTemplate.queryForObject(
				checkCompanyIdentifierSQL, paramSource, Integer.class);
				System.out.println("3");
			
			System.out.println("company identifier  : " + companyId);

			if (companyId != null) {
				result = companyId;
			}
		}catch(Exception e){
			System.err.println("Error while retrieving company ID");
		}	
		
		return result;		
	}

	public void saveDeviceLocation(
			SaveDeviceLocationRequest saveDeviceLocationRequest) {

		String deviceLocationQueueInsertSQL = "Insert into devicelocationqueue "
				+ "(deviceid,latitude,longitude,mapURL,addInfo,dateTriggerd) values "
				+ "(:deviceid,:latitude,:longitude,:mapURL,:addInfo,:dateTriggerd)";

		Map<String, Object> bind = new HashMap<String, Object>(8);
		bind.put("deviceid", saveDeviceLocationRequest.getDeviceId());
		bind.put("latitude", saveDeviceLocationRequest.getLatitude());
		bind.put("longitude", saveDeviceLocationRequest.getLongitude());
		bind.put("mapURL", saveDeviceLocationRequest.getMapURL());
		bind.put("addInfo", saveDeviceLocationRequest.getAddInfo());
		bind.put("dateTriggerd", saveDeviceLocationRequest.getDateTriggerd());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		namedParameterJdbcTemplate.update(deviceLocationQueueInsertSQL,
				paramSource);

	}
}
