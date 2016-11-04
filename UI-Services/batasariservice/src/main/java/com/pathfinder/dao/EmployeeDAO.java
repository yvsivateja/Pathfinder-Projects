package com.pathfinder.dao;

import com.pathfinder.dto.EmployeeRegistrationDTO;
import com.pathfinder.dto.EmployeesResultDTO;

public interface EmployeeDAO {

	public void saveEmployee(EmployeeRegistrationDTO employeeRegistrationDTO) throws Exception;

	public EmployeesResultDTO getEmployee(
			EmployeeRegistrationDTO employeeRegistrationDTO) throws Exception;

	public void updateEmployee(EmployeeRegistrationDTO employeeRegistrationDTO) throws Exception;

	public void deleteEmployee(EmployeeRegistrationDTO employeeRegistrationDTO) throws Exception;
}
