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

import com.pathfinder.dto.DeviceLocationDTO;
import com.pathfinder.resultsetextractors.DeviceLocationResultSetExtractor;

@Component
public class ManageDeviceLocationDAOImpl implements ManageDeviceLocationDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private DeviceLocationResultSetExtractor deviceLocationResultSetExtractor;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public List<DeviceLocationDTO> getDeviceLocation(String deviceId,
			String onDate, String companyId) {
		String getDeviceLocationsSQL = "Select * from devicelocations where deviceid=:deviceid "
				+ "and companyid=:companyid and DATE(dateTriggerd)=DATE(:onDate) and latitude <> '0.0' order by dateTriggerd desc ";

		Map<String, Object> bind = new HashMap<String, Object>(3);
		bind.put("deviceid", deviceId);
		bind.put("companyid", companyId);
		bind.put("onDate", getFormattedDate(onDate));
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		List<DeviceLocationDTO> deviceLocationDTOs = namedParameterJdbcTemplate
				.query(getDeviceLocationsSQL, paramSource,
						deviceLocationResultSetExtractor);
		return deviceLocationDTOs;
	}

	private String getFormattedDate(String date) {
		SimpleDateFormat dateFromFormatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dateToFormatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date inputDate = dateFromFormatter.parse(date);
			return dateToFormatter.format(inputDate);

		} catch (ParseException e) {
			Date today = new Date();
			System.out.println(dateToFormatter.format(today));
			return dateToFormatter.format(today);
		}

	}
}
