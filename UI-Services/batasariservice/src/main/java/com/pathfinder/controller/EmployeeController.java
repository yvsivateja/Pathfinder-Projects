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

import com.pathfinder.business.ManageEmployeeBusiness;
import com.pathfinder.constants.RealConstants;
import com.pathfinder.dto.EmployeeRegistrationDTO;
import com.pathfinder.dto.EmployeesResultDTO;
import com.pathfinder.filter.ModuleBean;
import com.pathfinder.filter.SessionBean;
import com.pathfinder.util.UserAccessManagementUtil;

@Controller
public class EmployeeController {

	@Autowired
	ManageEmployeeBusiness manageEmployeeBusiness;

	@RequestMapping(value = RealConstants.EMPLOYEE_ADD_URL, method = RequestMethod.GET)
	public String addEmployee(
			@ModelAttribute("employeeRegistrationDTO") EmployeeRegistrationDTO employeeRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) throws Exception {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean employeeModuleBean = modulesMap
					.get(RealConstants.MODULENAME.EMPLOYEE.name());
			if (!employeeModuleBean.isCanAdd()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}
		} else {
			httpServletRequest.getSession().invalidate();
		}
		setSessionObjects(employeeRegistrationDTO, httpServletRequest);
		return RealConstants.EMPLOYEE_ADD_PAGE;
	}

	@RequestMapping(value = RealConstants.EMPLOYEE_SAVE_URL, method = RequestMethod.POST)
	public String saveEmployee(
			@ModelAttribute("employeeRegistrationDTO") @Valid EmployeeRegistrationDTO employeeRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) throws Exception {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean employeeModuleBean = modulesMap
					.get(RealConstants.MODULENAME.EMPLOYEE.name());
			if (UserAccessManagementUtil.checkIsEmpty(employeeRegistrationDTO
					.getEmployeeId())) {
				if (!employeeModuleBean.isCanAdd()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
			} else {
				if (!employeeModuleBean.isCanUpdate()) {
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
			String returnType = RealConstants.EMPLOYEE_ADD_PAGE;
			if (employeeRegistrationDTO.getActionType().contentEquals("update")) {
				returnType = RealConstants.EMPLOYEE_UPDATE_PAGE;
			}
			return returnType;
		}
		manageEmployeeBusiness.saveorMergeEmployee(employeeRegistrationDTO,
				result, model);

		return "redirect:" + RealConstants.EMPLOYEE_ALL_VIEW;
	}

	@RequestMapping(value = RealConstants.EMPLOYEE_ALL_VIEW, method = RequestMethod.GET)
	public String showEmployees(
			@ModelAttribute("employeeRegistrationDTO") EmployeeRegistrationDTO employeeRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) throws Exception {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean employeeModuleBean = modulesMap
					.get(RealConstants.MODULENAME.EMPLOYEE.name());
			if (!employeeModuleBean.isCanView()) {
				return RealConstants.ACCESS_DENIED_PAGE;
			}
		} else {
			httpServletRequest.getSession().invalidate();
		}

		setSessionObjects(employeeRegistrationDTO, httpServletRequest);
		EmployeesResultDTO employeesResultDTO = manageEmployeeBusiness
				.getEmployees(employeeRegistrationDTO, result, model);
		model.addAttribute("EmployeesResultDTO", employeesResultDTO);
		return RealConstants.EMPLOYEES_SHOW_PAGE;
	}

	@RequestMapping(value = RealConstants.EMPLOYEE_MANAGE_ACTIONS, method = RequestMethod.POST)
	public String manageEmployee(
			@ModelAttribute("employeeRegistrationDTO") EmployeeRegistrationDTO employeeRegistrationDTO,
			BindingResult result, Model model,
			HttpServletRequest httpServletRequest) throws Exception {

		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		String returnType = RealConstants.EMPLOYEES_SHOW_PAGE;
		if (sessionBean != null) {
			Map<String, ModuleBean> modulesMap = sessionBean
					.getModulePermissions();
			ModuleBean employeeModuleBean = modulesMap
					.get(RealConstants.MODULENAME.EMPLOYEE.name());
			if (employeeRegistrationDTO.getActionType().contentEquals("update")) {
				if (!employeeModuleBean.isCanUpdate()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
				returnType = RealConstants.EMPLOYEE_UPDATE_PAGE;
				setSessionObjects(employeeRegistrationDTO, httpServletRequest);
				EmployeesResultDTO employeesResultDTO = manageEmployeeBusiness
						.getEmployees(employeeRegistrationDTO, result, model);
				if (employeesResultDTO.getEmployeeRegistrationDTO().size() > 0) {
					System.out.println("Emp Name : "
							+ employeesResultDTO.getEmployeeRegistrationDTO()
									.get(0).getEmpName());
					model.addAttribute("employeeRegistrationDTO",
							employeesResultDTO.getEmployeeRegistrationDTO()
									.get(0));
				} else
					returnType = "redirect:" + RealConstants.EMPLOYEE_ALL_VIEW;
			} else if (employeeRegistrationDTO.getActionType().contentEquals(
					"delete")) {
				if (!employeeModuleBean.isCanDelete()) {
					return RealConstants.ACCESS_DENIED_PAGE;
				}
				returnType = "redirect:" + RealConstants.EMPLOYEE_ALL_VIEW;
				manageEmployeeBusiness.deleteEmployee(employeeRegistrationDTO);
			}
		} else {
			httpServletRequest.getSession().invalidate();
		}
		return returnType;
	}

	private void setSessionObjects(
			EmployeeRegistrationDTO employeeRegistrationDTO,
			HttpServletRequest httpServletRequest) {
		SessionBean sessionBean = (SessionBean) httpServletRequest.getSession()
				.getAttribute("sessionUserDetails");
		if (sessionBean != null) {
			employeeRegistrationDTO.setGroupid(sessionBean.getCompanyid());
		}
	}
}
