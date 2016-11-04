package com.pathfinder.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pathfinder.dao.ManageDeviceLocationDAO;
import com.pathfinder.dto.DeviceLocationDTO;

@Component
public class ManageDeviceLocationBusiness {

	@Autowired
	ManageDeviceLocationDAO manageDeviceLocationDAO;

	public List<DeviceLocationDTO> getDeviceLocation(String device,
			String onDate, String companyid) {
		return manageDeviceLocationDAO.getDeviceLocation(device, onDate,
				companyid);
	}
}
