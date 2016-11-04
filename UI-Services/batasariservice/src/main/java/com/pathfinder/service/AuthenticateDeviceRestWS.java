package com.pathfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pathfinder.business.ManageDeviceWSBusiness;
import com.pathfinder.dto.WSDeviceAuthenticateRequest;
import com.pathfinder.dto.WSDeviceAuthenticateResponse;
import com.pathfinder.exception.BatasariWSException;
import com.pathfinder.util.UserAccessManagementUtil;

@RestController
public class AuthenticateDeviceRestWS {

	@Autowired
	ManageDeviceWSBusiness manageDeviceWSBusiness;

	//@RequestMapping(value = RealConstants.AUTH_REG_DEVICE, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String authenticateAndRegisterDevice(
			@RequestBody String notificationJsonRequest)
			throws BatasariWSException {
		WSDeviceAuthenticateRequest wsDeviceAuthenticateRequest = (WSDeviceAuthenticateRequest) UserAccessManagementUtil
				.convertToJava(notificationJsonRequest,
						WSDeviceAuthenticateRequest.class);
		System.out.println("In service");
		System.out.println("Company ID : "
				+ wsDeviceAuthenticateRequest.getCompanyIdentifier());
		System.out.println("getDeviceID ID : "
				+ wsDeviceAuthenticateRequest.getDeviceID());
		System.out.println("getDeviceName ID : "
				+ wsDeviceAuthenticateRequest.getDeviceName());
		System.out.println("getPhoneNumber ID : "
				+ wsDeviceAuthenticateRequest.getPhoneNumber());
		System.out.println("UserIdentifier ID : "
				+ wsDeviceAuthenticateRequest.getUserIdentifier());

		WSDeviceAuthenticateResponse wsDeviceAuthenticateResponse = manageDeviceWSBusiness
				.authAndRegisterDevice(wsDeviceAuthenticateRequest);

		return UserAccessManagementUtil
				.convertToJson(wsDeviceAuthenticateResponse);
	}

}
