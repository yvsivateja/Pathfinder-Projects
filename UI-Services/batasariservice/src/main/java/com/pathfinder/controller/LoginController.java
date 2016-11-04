package com.pathfinder.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pathfinder.constants.RealConstants;
import com.pathfinder.filter.ModuleBean;
import com.pathfinder.filter.SessionBean;

@Controller
public class LoginController {

	@RequestMapping(value = "userLogin.htm", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error) {
		System.out.println("In Login");
		ModelAndView model = new ModelAndView();
		model.setViewName("userLogin");

		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			model.setViewName("userLoginError");
			// return "userLoginError";
		}

		// return "userLogin";
		return model;

	}

	@RequestMapping(value = "accessdenied.htm", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		System.out.println("access denied");
		ModelAndView model = new ModelAndView();
		model.setViewName("accessdenied");

		return model;

	}

	@RequestMapping(value = RealConstants.USER_HOME, method = RequestMethod.GET)
	public String renderUserHome(HttpServletRequest httpServletRequest) {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		String returnType = "redirect:userLogin.htm";

		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();

			ModuleBean companyModuleBean = modulesMap
					.get(RealConstants.MODULENAME.COMPANY.name());
			ModuleBean employeeModuleBean = modulesMap
					.get(RealConstants.MODULENAME.EMPLOYEE.name());
			ModuleBean rolesModuleBean = modulesMap
					.get(RealConstants.MODULENAME.ROLES.name());
			ModuleBean usersModuleBean = modulesMap
					.get(RealConstants.MODULENAME.USERS.name());

			if (companyModuleBean != null && companyModuleBean.isCanView()) {
				returnType = "redirect:" + RealConstants.GROUPS_ALL_VIEW;
				sessionBean
						.setHomePage(RealConstants.MODULENAME.COMPANY.name());
			} else if (employeeModuleBean != null
					&& employeeModuleBean.isCanView()) {
				returnType = "redirect:" + RealConstants.EMPLOYEE_ALL_VIEW;
				sessionBean
						.setHomePage(RealConstants.MODULENAME.EMPLOYEE.name());
			} else if (rolesModuleBean != null && rolesModuleBean.isCanView()) {
				returnType = "redirect:" + RealConstants.ROLE_ALL_VIEW;
				sessionBean
						.setHomePage(RealConstants.MODULENAME.ROLES.name());
			} else if (usersModuleBean != null && usersModuleBean.isCanView()) {
				returnType = "redirect:" + RealConstants.USERS_ALL_VIEW;
				sessionBean
						.setHomePage(RealConstants.MODULENAME.USERS.name());
			} else {
				returnType = "redirect:" + RealConstants.USER_PROFILE_VIEW;
				sessionBean.setHomePage("Profile");
			}
			httpServletRequest.getSession().setAttribute("sessionUserDetails",
					sessionBean);

		} else {
			httpServletRequest.getSession().invalidate();
		}
		return returnType;
	}

}
