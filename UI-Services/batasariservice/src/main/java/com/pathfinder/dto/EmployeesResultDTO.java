package com.pathfinder.dto;

import java.util.List;

public class EmployeesResultDTO {

	private List<EmployeeRegistrationDTO> employeeRegistrationDTO;

	public List<EmployeeRegistrationDTO> getEmployeeRegistrationDTO() {
		return employeeRegistrationDTO;
	}

	public void setEmployeeRegistrationDTO(
			List<EmployeeRegistrationDTO> employeeRegistrationDTO) {
		this.employeeRegistrationDTO = employeeRegistrationDTO;
	}

}
