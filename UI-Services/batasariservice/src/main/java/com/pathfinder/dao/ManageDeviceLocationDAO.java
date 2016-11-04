package com.pathfinder.dao;

import java.util.List;

import com.pathfinder.dto.DeviceLocationDTO;

public interface ManageDeviceLocationDAO {

	public List<DeviceLocationDTO> getDeviceLocation(String deviceId,
			String onDate, String companyId);
}
