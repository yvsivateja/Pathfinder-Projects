package com.pathfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pathfinder.business.ManageDeviceWSBusiness;
import com.pathfinder.dto.SaveDeviceLocationRequest;
import com.pathfinder.exception.BatasariWSException;
import com.pathfinder.util.UserAccessManagementUtil;

@RestController
public class SaveDeviceLocationRestWS {

	@Autowired
	ManageDeviceWSBusiness manageDeviceWSBusiness;

	//@RequestMapping(value = RealConstants.SAVE_DEVICE_LOACATION, method = RequestMethod.POST)
	@ResponseBody
	public String saveDeviceLocation(@RequestBody String notificationJsonRequest)
			throws BatasariWSException {

		SaveDeviceLocationRequest saveDeviceLocationRequest = (SaveDeviceLocationRequest) UserAccessManagementUtil
				.convertToJava(notificationJsonRequest,
						SaveDeviceLocationRequest.class);

		manageDeviceWSBusiness.saveDeviceLocation(saveDeviceLocationRequest);
		return "success";
	}
}
