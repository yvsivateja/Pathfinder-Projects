package com.pathfinder.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pathfinder.dao.ManageDevicesDAO;
import com.pathfinder.dto.DeviceDTO;

@Component
public class ManageDevicesBusiness {

	@Autowired
	ManageDevicesDAO manageDevicesDAO;

	public List<DeviceDTO> getUnassignedDevices(String companyID)
			throws Exception {

		return manageDevicesDAO.getUnassignedDevices(companyID);
	}

	public List<DeviceDTO> getDevicesWithApproval(String companyID,
			boolean approved) throws Exception {

		return manageDevicesDAO.getDevicesWithApproval(companyID, approved);
	}

	public void approveDevices(DeviceDTO deviceDTO, String approvedby)
			throws Exception {
		manageDevicesDAO.approveDevices(deviceDTO.getDeviceID(), approvedby);
	}

	public void updateDevice(DeviceDTO deviceDTO) {
		manageDevicesDAO.updateDevice(deviceDTO);
	}

	public void deleteDevice(DeviceDTO deviceDTO) {
		manageDevicesDAO.deleteDevice(deviceDTO);
	}
}
