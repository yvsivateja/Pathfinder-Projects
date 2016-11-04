package com.pathfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pathfinder.business.ManageDeviceWSBusiness;
import com.pathfinder.constants.RealConstants;
import com.pathfinder.dto.WSDeviceAuthenticateRequest;
import com.pathfinder.dto.WSDeviceAuthenticateResponse;
import com.pathfinder.exception.BatasariWSException;
import com.pathfinder.util.UserAccessManagementUtil;

@RestController
public class AuthenticateDeviceRestWS {

	@Autowired
	ManageDeviceWSBusiness manageDeviceWSBusiness;

	@RequestMapping(value = RealConstants.AUTH_REG_DEVICE, method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String authenticateAndRegisterDevice(
			@RequestBody String notificationJsonRequest)
			throws BatasariWSException {
		System.out.println("Json Input : "+notificationJsonRequest);	
		WSDeviceAuthenticateRequest wsDeviceAuthenticateRequest = (WSDeviceAuthenticateRequest) UserAccessManagementUtil
				.convertToJava(notificationJsonRequest,
						WSDeviceAuthenticateRequest.class);

		WSDeviceAuthenticateResponse wsDeviceAuthenticateResponse = manageDeviceWSBusiness
				.authAndRegisterDevice(wsDeviceAuthenticateRequest);
		System.out.println("Response : "+wsDeviceAuthenticateResponse);
		return UserAccessManagementUtil
				.convertToJson(wsDeviceAuthenticateResponse);
	}

}
