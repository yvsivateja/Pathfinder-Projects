package com.pathfinder.controller;

import java.util.LinkedHashMap;
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

import com.pathfinder.business.UserAccessBusiness;
import com.pathfinder.constants.RealConstants;
import com.pathfinder.dto.UserProfileDTO;
import com.pathfinder.dto.UserRegistrationDTO;
import com.pathfinder.dto.UsersResultDTO;
import com.pathfinder.filter.ModuleBean;
import com.pathfinder.filter.SessionBean;
import com.pathfinder.util.UserAccessManagementUtil;

@Controller
public class UserController {

	@Autowired
	UserAccessBusiness userAccessBusiness;

	@RequestMapping(value = RealConstants.USER_ADD_URL, method = RequestMethod.GET)
	public String showUser(
			@ModelAttribute("userRegistrationDTO") UserRegistrationDTO userRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean usersModuleBean = modulesMap
					.get(RealConstants.MODULENAME.USERS.name());
			if (!usersModuleBean.isCanAdd()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}

			userRegistrationDTO.setGroupId(sessionBean.getCompanyid());
			userRegistrationDTO.setCompanyShortKey(sessionBean
					.getCompanyShortKey());
			Map<String, String> employees = new LinkedHashMap<String, String>();
			employees = userAccessBusiness.getAvailableEmployees(sessionBean
					.getCompanyid());

			Map<String, String> roles = new LinkedHashMap<String, String>();
			roles = userAccessBusiness.getAvailableRoles(sessionBean
					.getCompanyid());

			userRegistrationDTO.setAvailableEmployees(employees);
			userRegistrationDTO.setAvailableRolesTODisplay(roles);

			httpServletRequest.getSession().setAttribute("employeeMap",
					employees);
			httpServletRequest.getSession().setAttribute("rolesMap", roles);

		} else {
			httpServletRequest.getSession().invalidate();
		}
		return RealConstants.USER_ADD_PAGE;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = RealConstants.USER_SAVE_URL, method = RequestMethod.POST)
	public String saveUsers(
			@ModelAttribute("userRegistrationDTO") @Valid UserRegistrationDTO userRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean usersModuleBean = modulesMap
					.get(RealConstants.MODULENAME.USERS.name());
			if (UserAccessManagementUtil.checkIsEmpty(userRegistrationDTO
					.getUserId())) {
				if (!usersModuleBean.isCanAdd()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
			} else {
				if (!usersModuleBean.isCanUpdate()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
			}

		} else {
			httpServletRequest.getSession().invalidate();
		}

		if (result.hasErrors()) {
			httpServletRequest.getSession().getAttribute("employeeMap");

			Map<String, String> roles = (Map<String, String>) httpServletRequest
					.getSession().getAttribute("rolesMap");
			Map<String, String> employees = (Map<String, String>) httpServletRequest
					.getSession().getAttribute("employeeMap");

			userRegistrationDTO.setAvailableEmployees(employees);
			userRegistrationDTO.setAvailableRolesTODisplay(roles);
			boolean userNameUniqueError = false;
			for (FieldError err : result.getFieldErrors()) {
				System.out.println(err.getField() + "-"
						+ err.getDefaultMessage());
				if (err.getField().contentEquals("username")) {
					userNameUniqueError = true;
				}
			}

			if (userRegistrationDTO.getActionType().contentEquals("update")
					&& !userNameUniqueError) {
				return RealConstants.USER_UPDATE_PAGE;
			} else if (userRegistrationDTO.getActionType().contentEquals("add")) {
				return RealConstants.USER_ADD_PAGE;
			}

		}
		setSessionObjects(userRegistrationDTO, httpServletRequest);
		userAccessBusiness.saveOrMergeUser(userRegistrationDTO);
		return "redirect:" + RealConstants.USERS_ALL_VIEW;
	}

	@RequestMapping(value = RealConstants.USERS_ALL_VIEW, method = RequestMethod.GET)
	public String showAllUsers(
			@ModelAttribute("userRegistrationDTO") UserRegistrationDTO userRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean usersModuleBean = modulesMap
					.get(RealConstants.MODULENAME.USERS.name());
			if (!usersModuleBean.isCanView()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}
		} else {
			httpServletRequest.getSession().invalidate();
		}

		setSessionObjects(userRegistrationDTO, httpServletRequest);

		UsersResultDTO usersResultDTO = userAccessBusiness
				.getUsers(userRegistrationDTO);
		model.addAttribute("UsersResultDTO", usersResultDTO);

		return RealConstants.USER_VIEW_PAGE;
	}

	@RequestMapping(value = RealConstants.USER_MANAGE_ACTIONS, method = RequestMethod.POST)
	public String manageRole(
			@ModelAttribute("userRegistrationDTO") UserRegistrationDTO userRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		String returnType = "redirect:" + RealConstants.USERS_ALL_VIEW;
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean usersModuleBean = modulesMap
					.get(RealConstants.MODULENAME.USERS.name());
			if (userRegistrationDTO.getActionType().contentEquals("update")) {
				if (!usersModuleBean.isCanUpdate()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
				returnType = RealConstants.USER_UPDATE_PAGE;
				setSessionObjects(userRegistrationDTO, httpServletRequest);

				userRegistrationDTO = userAccessBusiness
						.getUserRoles(userRegistrationDTO);
				System.out.println("results retrieved for update : "
						+ userRegistrationDTO.getUsername());

				setGarbageValuesTOAvoidValidationErrors(userRegistrationDTO);

				model.addAttribute("userRegistrationDTO", userRegistrationDTO);

			} else if (userRegistrationDTO.getActionType().contentEquals(
					"delete")) {
				if (!usersModuleBean.isCanDelete()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
				returnType = "redirect:" + RealConstants.USERS_ALL_VIEW;
				userAccessBusiness.deleteUser(userRegistrationDTO,sessionBean.getLoggedInUserId());
			}
		} else {
			httpServletRequest.getSession().invalidate();
		}
		return returnType;
	}

	private void setGarbageValuesTOAvoidValidationErrors(
			UserRegistrationDTO userRegistrationDTO) {
		userRegistrationDTO.setEmployeeId("TEMP!@5");
		userRegistrationDTO.setPassword("TEMP!@5");
		userRegistrationDTO.setRepassword("TEMP!@5");
	}

	private void setSessionObjects(UserRegistrationDTO userRegistrationDTO,
			HttpServletRequest httpServletRequest) {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			userRegistrationDTO.setGroupId(sessionBean.getCompanyid());
		}
	}

	@RequestMapping(value = RealConstants.USER_PROFILE_VIEW, method = RequestMethod.GET)
	public String showProfile(
			@ModelAttribute("userProfileDTO") UserProfileDTO userProfileDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			userProfileDTO = userAccessBusiness.getUserProfile(sessionBean
					.getLoggedInUserId());
			model.addAttribute("userProfileDTO", userProfileDTO);
		} else {
			httpServletRequest.getSession().invalidate();

		}
		String changePassword = httpServletRequest
				.getParameter("changePassword");
		if (!UserAccessManagementUtil.checkIsEmpty(changePassword)
				&& changePassword.contentEquals("true")) {
			return RealConstants.USER_PASSWORD_CHANGE_PAGE;
		}
		return RealConstants.USER_PROFILE_PAGE;
	}

	@RequestMapping(value = RealConstants.USER_CHANGE_PASSWORD, method = RequestMethod.POST)
	public String changeUserPassword(
			@ModelAttribute("userProfileDTO") @Valid UserProfileDTO userProfileDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {
		if (result.hasErrors()) {
			for (FieldError err : result.getFieldErrors()) {
				System.out.println(err.getField() + "-"
						+ err.getDefaultMessage());
			}
			return RealConstants.USER_PASSWORD_CHANGE_PAGE;
		}
		userAccessBusiness.changeUserPassword(userProfileDTO);

		return "redirect:" + RealConstants.USER_PROFILE_VIEW + "?message=true";
	}
}
