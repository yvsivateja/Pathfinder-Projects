package com.pathfinder.controller;

import java.util.ArrayList;
import java.util.List;
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

import com.pathfinder.business.ManageRoleBusiness;
import com.pathfinder.constants.RealConstants;
import com.pathfinder.dto.ModuleDTO;
import com.pathfinder.dto.RolesDTO;
import com.pathfinder.dto.RolesResultDTO;
import com.pathfinder.filter.ModuleBean;
import com.pathfinder.filter.SessionBean;
import com.pathfinder.util.UserAccessManagementUtil;

@Controller
public class RoleController {

	@Autowired
	ManageRoleBusiness manageRoleBusiness;

	@RequestMapping(value = RealConstants.ROLE_ADD_URL, method = RequestMethod.GET)
	public String addRole(@ModelAttribute("rolesDTO") RolesDTO rolesDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean rolesModuleBean = modulesMap
					.get(RealConstants.MODULENAME.ROLES.name());
			if (!rolesModuleBean.isCanAdd()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}

			List<ModuleDTO> modules = new ArrayList<ModuleDTO>();
			for (int row = 0; row < RealConstants.MODULES.length; row++) {
				if (!isAdminModule(RealConstants.MODULES[row])) {
					ModuleDTO moduleDTO = new ModuleDTO();
					moduleDTO.setModuleName(RealConstants.MODULES[row]);
					moduleDTO.setCanCreate(false);
					moduleDTO.setCanDelete(false);
					moduleDTO.setCanUpdate(false);
					moduleDTO.setCanView(false);
					modules.add(moduleDTO);
				} else if (sessionBean.isSuperAdmin()) {
					ModuleDTO moduleDTO = new ModuleDTO();
					moduleDTO.setModuleName(RealConstants.MODULES[row]);
					moduleDTO.setCanCreate(false);
					moduleDTO.setCanDelete(false);
					moduleDTO.setCanUpdate(false);
					moduleDTO.setCanView(false);
					modules.add(moduleDTO);
				}
			}

			rolesDTO.setModules(modules);
			setSessionObjects(rolesDTO, httpServletRequest);
		} else {
			httpServletRequest.getSession().invalidate();
		}
		return RealConstants.ROLE_ADD_PAGE;
	}

	private boolean isAdminModule(String value) {

		if (value.contentEquals(RealConstants.MODULENAME.COMPANY.name())) {
			return true;
		} else if (value.contentEquals(RealConstants.MODULENAME.IMPERSONATE
				.name())) {
			return true;
		} else if (value.contentEquals(RealConstants.MODULENAME.APPROVEDEVICES
				.name())) {
			return true;
		}

		return false;
	}

	@RequestMapping(value = RealConstants.ROLE_SAVE_URL, method = RequestMethod.POST)
	public String saveRole(
			@ModelAttribute("rolesDTO") @Valid RolesDTO rolesDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean rolesModuleBean = modulesMap
					.get(RealConstants.MODULENAME.ROLES.name());
			if (UserAccessManagementUtil.checkIsEmpty(rolesDTO.getRoleId())) {
				if (!rolesModuleBean.isCanAdd()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
			} else {
				if (!rolesModuleBean.isCanUpdate()) {
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
			String returnType = RealConstants.ROLE_ADD_PAGE;
			if (rolesDTO.getActionType().contentEquals("update")) {
				returnType = RealConstants.ROLE_UPDATE_PAGE;
			}
			return returnType;

		}
		manageRoleBusiness.saveorMergeRole(rolesDTO, result, model);

		return "redirect:" + RealConstants.ROLE_ALL_VIEW;
	}

	@RequestMapping(value = RealConstants.ROLE_ALL_VIEW, method = RequestMethod.GET)
	public String showRoles(@ModelAttribute("rolesDTO") RolesDTO rolesDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean rolesModuleBean = modulesMap
					.get(RealConstants.MODULENAME.ROLES.name());
			if (!rolesModuleBean.isCanView()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}
		} else {
			httpServletRequest.getSession().invalidate();
		}

		setSessionObjects(rolesDTO, httpServletRequest);
		System.out.println("Retrieving results for roles : "
				+ rolesDTO.getCompanyId());
		RolesResultDTO rolesResultDTO = manageRoleBusiness.getRoles(rolesDTO,
				result, model);

		httpServletRequest.getSession().setAttribute("rolesForAjaxRequest",
				rolesResultDTO.getRolesMap());

		model.addAttribute("RolesResultDTO", rolesResultDTO);
		return RealConstants.ROLE_ALL_PAGE;
	}

	@RequestMapping(value = RealConstants.ROLE_MANAGE_ACTIONS, method = RequestMethod.POST)
	public String manageRole(@ModelAttribute("rolesDTO") RolesDTO rolesDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");

		String returnType = "redirect:" + RealConstants.ROLE_ALL_VIEW;
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean rolesModuleBean = modulesMap
					.get(RealConstants.MODULENAME.ROLES.name());
			if (rolesDTO.getActionType().contentEquals("update")) {
				if (!rolesModuleBean.isCanUpdate()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
				returnType = RealConstants.ROLE_UPDATE_PAGE;
				setSessionObjects(rolesDTO, httpServletRequest);
				RolesResultDTO rolesResultDTO = manageRoleBusiness.getRoles(
						rolesDTO, result, model);
				if (rolesResultDTO.getRolesList().size() > 0) {

					model.addAttribute("rolesDTO", rolesResultDTO
							.getRolesList().get(0));
				}
			} else if (rolesDTO.getActionType().contentEquals("delete")) {
				if (!rolesModuleBean.isCanDelete()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
				returnType = "redirect:" + RealConstants.ROLE_ALL_VIEW;
				manageRoleBusiness.deleteRole(rolesDTO);
			}
		} else {
			httpServletRequest.getSession().invalidate();
		}
		return returnType;
	}

	private void setSessionObjects(RolesDTO rolesDTO,
			HttpServletRequest httpServletRequest) {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			rolesDTO.setCompanyId(sessionBean.getCompanyid());
		}
	}
}
