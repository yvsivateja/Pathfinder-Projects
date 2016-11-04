package com.pathfinder.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pathfinder.business.ManageDevicesBusiness;
import com.pathfinder.constants.RealConstants;
import com.pathfinder.dto.DeviceDTO;
import com.pathfinder.filter.ModuleBean;
import com.pathfinder.filter.SessionBean;

@Controller
public class DeviceController {

	@Autowired
	ManageDevicesBusiness manageDevicesBusiness;

	@RequestMapping(value = RealConstants.INACTIVE_DEVICES, method = RequestMethod.GET)
	public String getInactiveDevices(Model model,
			HttpServletRequest httpServletRequest) throws Exception {

		// SessionBean sessionBean = (SessionBean)
		// httpServletRequest.getSession()
		// .getAttribute("sessionUserDetails");
		// List<DeviceDTO> deviceDTOs =
		// manageDevicesBusiness.getUnassignedDevices(sessionBean.getCompanyid());
		// model.addAttribute("deviceDTOs", deviceDTOs);
		// return RealConstants.INACTIVE_DEVICES_PAGE;
		return RealConstants.ACCESS_DENIED_PAGE;
	}

	@RequestMapping(value = RealConstants.DEVICES_TO_APPROVE, method = RequestMethod.GET)
	public String getDevicesToApprove(
			@ModelAttribute("deviceDTO") DeviceDTO deviceDTO, Model model,
			HttpServletRequest httpServletRequest) throws Exception {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean approveDevicesModuleBean = modulesMap
					.get(RealConstants.MODULENAME.APPROVEDEVICES.name());
			if (!approveDevicesModuleBean.isCanView()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}
			String companyId = sessionBean.getCompanyid();
			if (sessionBean.isSuperAdmin()) {
				companyId = "%";
			}
			List<DeviceDTO> deviceDTOs = manageDevicesBusiness
					.getDevicesWithApproval(companyId, false);
			model.addAttribute("deviceDTOs", deviceDTOs);

		} else {
			httpServletRequest.getSession().invalidate();
		}
		return RealConstants.DEVICES_TO_APPROVE_PAGE;
	}

	@RequestMapping(value = RealConstants.DEVICES_VIEW_ALL, method = RequestMethod.GET)
	public String showDevices(@ModelAttribute("deviceDTO") DeviceDTO deviceDTO,
			Model model, HttpServletRequest httpServletRequest)
			throws Exception {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean devicesModuleBean = modulesMap
					.get(RealConstants.MODULENAME.DEVICES.name());
			if (!devicesModuleBean.isCanView()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}
		}
		String companyId = sessionBean.getCompanyid();
		if (sessionBean.isSuperAdmin()) {
			companyId = "%";
		}
		List<DeviceDTO> deviceDTOs = manageDevicesBusiness
				.getDevicesWithApproval(companyId, true);
		model.addAttribute("deviceDTOs", deviceDTOs);
		return RealConstants.DEVICES_VIEW_PAGE;
	}

	@RequestMapping(value = RealConstants.APPROVE_DEVICES, method = RequestMethod.POST)
	public String devicesApproval(
			@ModelAttribute("deviceDTO") DeviceDTO deviceDTO,
			HttpServletRequest httpServletRequest) throws Exception {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean approveDevicesModuleBean = modulesMap
					.get(RealConstants.MODULENAME.APPROVEDEVICES.name());
			if (!approveDevicesModuleBean.isCanView()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}

			System.out.println("device ids :" + deviceDTO.getDeviceID());
			manageDevicesBusiness.approveDevices(deviceDTO,
					sessionBean.getLoggedInUserName());
		} else {
			httpServletRequest.getSession().invalidate();
		}
		return "redirect:" + RealConstants.DEVICES_TO_APPROVE;
	}

	@RequestMapping(value = RealConstants.DEVICE_MANAGE_ACTION, method = RequestMethod.POST)
	public String manageDeviceActions(
			@ModelAttribute("deviceDTO") DeviceDTO deviceDTO,
			HttpServletRequest httpServletRequest) throws Exception {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean devicesModuleBean = modulesMap
					.get(RealConstants.MODULENAME.DEVICES.name());
			if (deviceDTO.getActionType().contentEquals("update")) {
				if (devicesModuleBean.isCanUpdate()) {
					manageDevicesBusiness.updateDevice(deviceDTO);
				} else {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
			} else if (deviceDTO.getActionType().contentEquals("delete")) {
				if (devicesModuleBean.isCanDelete()) {
					manageDevicesBusiness.deleteDevice(deviceDTO);
				} else {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
			}
		} else {
			httpServletRequest.getSession().invalidate();
		}

		return "redirect:" + RealConstants.DEVICES_VIEW_ALL;
	}

}
