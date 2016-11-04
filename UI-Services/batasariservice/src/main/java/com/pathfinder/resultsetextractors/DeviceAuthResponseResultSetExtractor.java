package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.WSDeviceAuthenticateResponse;

@Component
public class DeviceAuthResponseResultSetExtractor implements
		ResultSetExtractor<WSDeviceAuthenticateResponse> {

	public WSDeviceAuthenticateResponse extractData(ResultSet resultSet)
			throws SQLException, DataAccessException {
		WSDeviceAuthenticateResponse wsDeviceAuthenticateResponse = null;

		while (resultSet.next()) {
			wsDeviceAuthenticateResponse = new WSDeviceAuthenticateResponse();
			wsDeviceAuthenticateResponse.setSuccess(true);
			wsDeviceAuthenticateResponse.setMessage("success");
			wsDeviceAuthenticateResponse.setCanStart(resultSet
					.getBoolean("canstart"));
			wsDeviceAuthenticateResponse.setCanStop(resultSet
					.getBoolean("canstop"));
			wsDeviceAuthenticateResponse.setCanUninstall(resultSet
					.getBoolean("canuninstall"));
		}
		return wsDeviceAuthenticateResponse;
	}

}
