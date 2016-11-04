package com.pathfinder.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pathfinder.business.ManageGroupBusiness;
import com.pathfinder.constants.RealConstants;
import com.pathfinder.dto.GroupRegistrationDTO;
import com.pathfinder.dto.GroupsResultDTO;
import com.pathfinder.filter.ModuleBean;
import com.pathfinder.filter.SessionBean;
import com.pathfinder.util.UserAccessManagementUtil;

@Controller
public class GroupController {

	@Autowired
	ManageGroupBusiness manageGroupBusiness;

	@RequestMapping(value = RealConstants.GROUP_ADD_URL, method = RequestMethod.GET)
	public String addGroup(
			@ModelAttribute("groupRegistrationDTO") GroupRegistrationDTO groupRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");

		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean companyModuleBean = modulesMap
					.get(RealConstants.MODULENAME.COMPANY.name());
			if (!companyModuleBean.isCanAdd()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}
		} else {
			httpServletRequest.getSession().invalidate();
		}

		return RealConstants.GROUP_ADD_PAGE;
	}

	@RequestMapping(value = RealConstants.GROUP_SAVE_URL, method = RequestMethod.POST)
	public String saveGroup(
			@ModelAttribute("groupRegistrationDTO") @Valid GroupRegistrationDTO groupRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean companyModuleBean = modulesMap
					.get(RealConstants.MODULENAME.COMPANY.name());
			if (UserAccessManagementUtil.checkIsEmpty(groupRegistrationDTO
					.getGroupid())) {
				if (!companyModuleBean.isCanAdd()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
			} else {
				if (!companyModuleBean.isCanUpdate()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
			}

		} else {
			httpServletRequest.getSession().invalidate();
		}

		if (result.hasErrors()) {
			for (FieldError err : result.getFieldErrors()) {
				System.out.println(err.getField() + "-"
						+ err.getDefaultMessage());
			}
			String returnType = RealConstants.GROUP_ADD_PAGE;
			if (groupRegistrationDTO.getActionType().contentEquals("update")) {
				returnType = RealConstants.GROUPS_UPDATE_PAGE;
			}
			return returnType;
		}
		manageGroupBusiness.saveorMergeGroup(groupRegistrationDTO, result,
				model);

		return "redirect:" + RealConstants.GROUPS_ALL_VIEW;
	}

	@RequestMapping(value = RealConstants.GROUPS_ALL_VIEW, method = RequestMethod.GET)
	public String showGroups(
			@ModelAttribute("groupRegistrationDTO") GroupRegistrationDTO groupRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		System.out.println("in java "+sessionBean);
		boolean userCanImpersonate = false;
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean companyModuleBean = modulesMap
					.get(RealConstants.MODULENAME.COMPANY.name());
			if (!companyModuleBean.isCanView()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}

			ModuleBean impersonateModuleBean = modulesMap
					.get(RealConstants.MODULENAME.IMPERSONATE.name());
			if (impersonateModuleBean != null
					&& impersonateModuleBean.isCanView()) {
				userCanImpersonate = true;
			}

		} else {
			httpServletRequest.getSession().invalidate();
		}

		GroupsResultDTO groupsResultDTO = manageGroupBusiness.getGroups(
				groupRegistrationDTO, result, model);
		if (userCanImpersonate) {
			groupsResultDTO.setShowImpersonate(true);
		} else {
			groupsResultDTO.setShowImpersonate(false);
		}
		model.addAttribute("GroupsResultDTO", groupsResultDTO);
		return RealConstants.GROUPS_SHOW_PAGE;
	}

	@RequestMapping(value = RealConstants.GROUP_MANAGE_ACTIONS, method = RequestMethod.POST)
	public String manageGroup(
			@ModelAttribute("groupRegistrationDTO") GroupRegistrationDTO groupRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		String returnType = RealConstants.GROUPS_SHOW_PAGE;
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean companyModuleBean = modulesMap
					.get(RealConstants.MODULENAME.COMPANY.name());

			System.out.println(groupRegistrationDTO.getActionType());
			System.out.println(groupRegistrationDTO.getGroupid());

			if (groupRegistrationDTO.getActionType().contentEquals("update")) {
				System.out.println("Can Update  : "
						+ companyModuleBean.isCanUpdate());
				if (!companyModuleBean.isCanUpdate()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
				returnType = RealConstants.GROUPS_UPDATE_PAGE;
				GroupsResultDTO groupsResultDTO = manageGroupBusiness
						.getGroups(groupRegistrationDTO, result, model);

				if (groupsResultDTO.getGroupRegistrationDTOs().size() > 0) {
					GroupRegistrationDTO groupRegistrationDTO2 = groupsResultDTO
							.getGroupRegistrationDTOs().get(0);
					setGarbageValuesToAvoidValidationErrors(groupRegistrationDTO2);
					model.addAttribute("groupRegistrationDTO",
							groupRegistrationDTO2);
				} else
					returnType = "redirect:" + RealConstants.GROUPS_ALL_VIEW;
			} else if (groupRegistrationDTO.getActionType().contentEquals(
					"delete")) {
				if (!companyModuleBean.isCanDelete()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
				returnType = "redirect:" + RealConstants.GROUPS_ALL_VIEW;
				manageGroupBusiness.deleteGroup(groupRegistrationDTO);
			} else if (groupRegistrationDTO.getActionType().contentEquals(
					"impersonate")) {
				sessionBean.setCompanyid(groupRegistrationDTO.getGroupid());
				sessionBean.setImpersonated(true);
				httpServletRequest.getSession().setAttribute(
						"sessionUserDetails", sessionBean);
				returnType = "redirect:" + RealConstants.GROUPS_ALL_VIEW;
			}
		} else {
			httpServletRequest.getSession().invalidate();
		}
		return returnType;
	}

	@RequestMapping(value = RealConstants.REMOVE_IMPERSONATION, method = RequestMethod.GET)
	public String backFromImpersonation(HttpServletRequest httpServletRequest) {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			sessionBean.setCompanyid(sessionBean.getUserCompanyid());
			sessionBean.setImpersonated(false);
			httpServletRequest.getSession().setAttribute("sessionUserDetails",
					sessionBean);
			Object URI = httpServletRequest
					.getAttribute("javax.servlet.forward.request_uri");
			System.out.println(URI);
		} else {
			httpServletRequest.getSession().invalidate();
		}
		return "redirect:" + RealConstants.GROUPS_ALL_VIEW;
	}

	private void setGarbageValuesToAvoidValidationErrors(
			GroupRegistrationDTO groupRegistrationDTO2) {
		groupRegistrationDTO2.setEmpName("AJSKH");
		groupRegistrationDTO2.setAge("100");
		groupRegistrationDTO2.setSex("AJSKH");
		groupRegistrationDTO2.setEmpPhone("9120192012");
		groupRegistrationDTO2.setUsername("TSDQ@5");
	}
}
