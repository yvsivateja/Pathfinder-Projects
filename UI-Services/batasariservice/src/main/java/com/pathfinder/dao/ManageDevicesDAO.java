package com.pathfinder.dao;

import java.util.List;

import com.pathfinder.dto.DeviceDTO;

public interface ManageDevicesDAO {

	public List<DeviceDTO> getUnassignedDevices(String companyID)
			throws Exception;

	public List<DeviceDTO> getDevicesWithApproval(String companyID,
			boolean approved) throws Exception;

	public void approveDevices(String deviceIDs, String approvedby) throws Exception;

	public void updateDevice(DeviceDTO deviceDTO);

	public void deleteDevice(DeviceDTO deviceDTO);
}
