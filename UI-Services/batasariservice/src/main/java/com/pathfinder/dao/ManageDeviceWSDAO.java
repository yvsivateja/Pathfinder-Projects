package com.pathfinder.dao;

import org.springframework.transaction.annotation.Transactional;

import com.pathfinder.dto.SaveDeviceLocationRequest;
import com.pathfinder.dto.WSDeviceAuthenticateRequest;
import com.pathfinder.dto.WSDeviceAuthenticateResponse;
import com.pathfinder.exception.BatasariWSException;

@Transactional
public interface ManageDeviceWSDAO {

	public WSDeviceAuthenticateResponse authAndRegisterDevice(
			WSDeviceAuthenticateRequest wsDeviceAuthenticateRequest)
			throws BatasariWSException;

	public void saveDeviceLocation(
			SaveDeviceLocationRequest saveDeviceLocationRequest);
}
