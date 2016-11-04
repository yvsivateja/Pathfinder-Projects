package com.pathfinder.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pathfinder.dao.ManageDeviceWSDAO;
import com.pathfinder.dto.SaveDeviceLocationRequest;
import com.pathfinder.dto.WSDeviceAuthenticateRequest;
import com.pathfinder.dto.WSDeviceAuthenticateResponse;
import com.pathfinder.exception.BatasariWSException;

@Component
public class ManageDeviceWSBusiness {

	@Autowired
	ManageDeviceWSDAO manageDeviceWSDAO;

	public WSDeviceAuthenticateResponse authAndRegisterDevice(
			WSDeviceAuthenticateRequest wsDeviceAuthenticateRequest)
			throws BatasariWSException {
		// TODO Auto-generated method stub
		return manageDeviceWSDAO
				.authAndRegisterDevice(wsDeviceAuthenticateRequest);
	}

	public void saveDeviceLocation(
			SaveDeviceLocationRequest saveDeviceLocationRequest) {
		// TODO Auto-generated method stub
		manageDeviceWSDAO.saveDeviceLocation(saveDeviceLocationRequest);
	}

}
