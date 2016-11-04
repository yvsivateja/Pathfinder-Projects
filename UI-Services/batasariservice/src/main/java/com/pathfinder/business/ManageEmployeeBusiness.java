package com.pathfinder.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.pathfinder.dao.EmployeeDAO;
import com.pathfinder.dto.EmployeeRegistrationDTO;
import com.pathfinder.dto.EmployeesResultDTO;
import com.pathfinder.util.UserAccessManagementUtil;

@Component
public class ManageEmployeeBusiness {

	@Autowired
	EmployeeDAO employeeDAO;

	public void saveorMergeEmployee(
			EmployeeRegistrationDTO employeeRegistrationDTO,
			BindingResult result, Model model) throws Exception{
		if (UserAccessManagementUtil.checkIsEmpty(employeeRegistrationDTO.getEmployeeId()))
			employeeDAO.saveEmployee(employeeRegistrationDTO);
		else
			employeeDAO.updateEmployee(employeeRegistrationDTO);
	}

	public EmployeesResultDTO getEmployees(
			EmployeeRegistrationDTO employeeRegistrationDTO,
			BindingResult result, Model model) throws Exception{
		// TODO Auto-generated method stub
		if (UserAccessManagementUtil.checkIsEmpty(employeeRegistrationDTO.getEmployeeId())) {
			employeeRegistrationDTO.setEmployeeId("%");
		}
		EmployeesResultDTO employeesResultDTO = employeeDAO
				.getEmployee(employeeRegistrationDTO);
		return employeesResultDTO;
	}

	public void deleteEmployee(EmployeeRegistrationDTO employeeRegistrationDTO) throws Exception{
		if (!UserAccessManagementUtil.checkIsEmpty(employeeRegistrationDTO.getEmployeeId())) {
			employeeDAO.deleteEmployee(employeeRegistrationDTO);
		}
	}

}
