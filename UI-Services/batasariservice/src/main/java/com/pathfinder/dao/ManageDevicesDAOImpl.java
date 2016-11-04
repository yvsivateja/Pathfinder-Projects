package com.pathfinder.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.DeviceDTO;
import com.pathfinder.resultsetextractors.DeviceDetailsResultSetExtractor;

@Component
public class ManageDevicesDAOImpl implements ManageDevicesDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private DeviceDetailsResultSetExtractor deviceDetailsResultSetExtractor;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public List<DeviceDTO> getUnassignedDevices(String companyID)
			throws Exception {

		String getInactiveDevicesSQL = "Select * from device where companyid=:companyid and employeeid IS NULL";
		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("companyid", companyID);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		List<DeviceDTO> deviceDTOs = namedParameterJdbcTemplate.query(
				getInactiveDevicesSQL, paramSource,
				deviceDetailsResultSetExtractor);

		return deviceDTOs;
	}

	public List<DeviceDTO> getDevicesWithApproval(String companyID,
			boolean approved) throws Exception {

		String getDevicesWithApprovalSQL = "Select * from device where companyid like :companyid and approved=:approved";

		Map<String, Object> bind = new HashMap<String, Object>(2);
		bind.put("companyid", companyID);
		bind.put("approved", approved);
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		List<DeviceDTO> deviceDTOs = namedParameterJdbcTemplate.query(
				getDevicesWithApprovalSQL, paramSource,
				deviceDetailsResultSetExtractor);

		return deviceDTOs;
	}

	public void approveDevices(String deviceID, String approvedBy)
			throws Exception {

		String approveDevicesSQL = "Update device set approved=:approved,approvedby=:approvedby where id =:id";
		List<SqlParameterSource> paramSourceList = new ArrayList<SqlParameterSource>();
		String[] deviceIDs = deviceID.split(",");
		for (String id : deviceIDs) {
			Map<String, Object> bind = new HashMap<String, Object>(2);
			bind.put("id", id);
			bind.put("approved", true);
			bind.put("approvedby", approvedBy);
			paramSourceList.add(new MapSqlParameterSource(bind));
		}

		int[] rowsUpdated = namedParameterJdbcTemplate.batchUpdate(
				approveDevicesSQL,
				paramSourceList.toArray(new SqlParameterSource[0]));

		System.out.println("approved devices : " + rowsUpdated.length);

	}

	public void updateDevice(DeviceDTO deviceDTO) {

		String updateDeviceSQL = "update device set canstart=:canstart,canstop=:canstop,canuninstall=:canuninstall where id=:id";

		Map<String, Object> bind = new HashMap<String, Object>(4);
		bind.put("canstart", deviceDTO.isCanStart());
		bind.put("canstop", deviceDTO.isCanStop());
		bind.put("canuninstall", deviceDTO.isCanUninstall());
		bind.put("id", deviceDTO.getId());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		int row = namedParameterJdbcTemplate.update(updateDeviceSQL,
				paramSource);

		System.out.println(row);

	}

	public void deleteDevice(DeviceDTO deviceDTO) {
		String deleteDeviceLocationsSQL = "Delete from devicelocations where deviceid IN (Select deviceid from device where id=:id)";
		String deleteDeviceSQL = "Delete from device where id=:id";
		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("id", deviceDTO.getId());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		namedParameterJdbcTemplate
				.update(deleteDeviceLocationsSQL, paramSource);
		namedParameterJdbcTemplate
				.update(deleteDeviceSQL, paramSource);

	}

}
