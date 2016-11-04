package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.EmployeeRegistrationDTO;
import com.pathfinder.dto.EmployeesResultDTO;

@Component
public class EmployeeResultSetExtractor implements
		ResultSetExtractor<EmployeesResultDTO> {

	public EmployeesResultDTO extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		EmployeesResultDTO employeesResultDTO = new EmployeesResultDTO();
		List<EmployeeRegistrationDTO> employeeRegistrationDTOs = new ArrayList<EmployeeRegistrationDTO>();
		while (rs.next()) {
			EmployeeRegistrationDTO employeeRegistrationDTO = new EmployeeRegistrationDTO();
			// employeeid,employeename,age,sex,phone,emailid,notes
			employeeRegistrationDTO.setGroupid(rs.getString("companyid"));
			employeeRegistrationDTO.setEmployeeId(rs.getString("employeeid"));
			employeeRegistrationDTO.setEmpName(rs.getString("employeename"));
			employeeRegistrationDTO.setAge(rs.getString("age"));
			employeeRegistrationDTO.setSex(rs.getString("sex"));
			employeeRegistrationDTO.setEmpPhone(rs.getString("phone"));
			employeeRegistrationDTO.setEmpEmailid(rs.getString("emailid"));
			employeeRegistrationDTO.setAdditionalInfo(rs.getString("notes"));
			employeeRegistrationDTOs.add(employeeRegistrationDTO);
		}
		System.out.println("No of Employees retrieved : "
				+ employeeRegistrationDTOs.size());
		employeesResultDTO.setEmployeeRegistrationDTO(employeeRegistrationDTOs);
		return employeesResultDTO;
	}
}
