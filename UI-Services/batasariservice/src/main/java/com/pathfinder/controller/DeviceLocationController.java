package com.pathfinder.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pathfinder.business.ManageDeviceLocationBusiness;
import com.pathfinder.constants.RealConstants;
import com.pathfinder.dto.DeviceLocationDTO;
import com.pathfinder.filter.SessionBean;
import com.pathfinder.util.UserAccessManagementUtil;

@Controller
public class DeviceLocationController {

	@Autowired
	ManageDeviceLocationBusiness manageDeviceLocationBusiness;

	@RequestMapping(value = RealConstants.DEVICE_SHOW_LOCATIONS_TABULAR, method = RequestMethod.GET)
	public String getDeviceTabular(
			@RequestParam(value = "device", required = true) String device,
			@RequestParam(value = "ondate", required = false) String onDate,
			Model model, HttpServletRequest httpServletRequest)
			throws Exception {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");

		if (UserAccessManagementUtil.checkIsEmpty(onDate)) {

			onDate = getTodayDate();
			System.out.println("date is empty set todays date as default : "
					+ onDate);
		}
		List<DeviceLocationDTO> deviceLocationDTOs = manageDeviceLocationBusiness
				.getDeviceLocation(device, onDate, sessionBean.getCompanyid());

		model.addAttribute("deviceLocationDTOs", deviceLocationDTOs);
		return RealConstants.DEVICE_LOCATION_TABULAR;
	}

	@RequestMapping(value = RealConstants.DEVICE_SHOW_LOCATIONS, method = RequestMethod.GET)
	public String getDeviceMapPoints(
			@RequestParam(value = "device", required = true) String device,
			@RequestParam(value = "ondate", required = false) String onDate,
			Model model, HttpServletRequest httpServletRequest)
			throws Exception {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (UserAccessManagementUtil.checkIsEmpty(onDate)) {
			onDate = getTodayDate();
			System.out.println("date is empty set todays date as default : "
					+ onDate);
		}
		List<DeviceLocationDTO> deviceLocationDTOs = manageDeviceLocationBusiness
				.getDeviceLocation(device, onDate, sessionBean.getCompanyid());
		httpServletRequest.setAttribute("ondate", onDate);
		model.addAttribute("deviceLocationDTOs", deviceLocationDTOs);
		return RealConstants.DEVICE_SHOW_MAP_POINTS;
	}

	private String getTodayDate() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(sdf.format(today));
		return sdf.format(today);
	}

}
