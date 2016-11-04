package com.pathfinder.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pathfinder.dto.EmployeeRegistrationDTO;
import com.pathfinder.dto.EmployeesResultDTO;
import com.pathfinder.resultsetextractors.EmployeeResultSetExtractor;

@Component
@Transactional
public class EmployeeDaoImpl implements EmployeeDAO {

	@Autowired
	EmployeeResultSetExtractor employeeResultSetExtractor;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public void saveEmployee(EmployeeRegistrationDTO employeeRegistrationDTO)
			throws Exception {

		String employeeInsertSQL = "INSERT INTO employee (employeename, phone,emailid,age,sex,companyid) VALUES (:employeename, :phone,:emailId,:age,:sex,:companyid)";
		String[] employeekeyColumnName = { "employee_id" };
		Map<String, Object> bind = new HashMap<String, Object>(6);
		bind.put("employeename", employeeRegistrationDTO.getEmpName());
		bind.put("phone", employeeRegistrationDTO.getEmpName());
		bind.put("emailId", employeeRegistrationDTO.getEmpEmailid());
		bind.put("age", employeeRegistrationDTO.getAge());
		bind.put("sex", employeeRegistrationDTO.getSex());
		bind.put("companyid", employeeRegistrationDTO.getGroupid());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int groupEmployeesInserted = namedParameterJdbcTemplate.update(
				employeeInsertSQL, paramSource, keyHolder,
				employeekeyColumnName);

		if (groupEmployeesInserted > 0) {
			String employeeId = keyHolder.getKey().toString();
			employeeRegistrationDTO.setEmployeeId(employeeId);
		}

	}

	public EmployeesResultDTO getEmployee(
			EmployeeRegistrationDTO employeeRegistrationDTO) throws Exception {
		String groupSelectSQL = "SELECT companyid,employeeid,employeename,age,sex,phone,emailid,notes FROM employee e "
				+ "where e.status=true and e.employeeid like :employeeid and e.companyid like :companyid";
		Map<String, Object> bind = new HashMap<String, Object>(2);
		System.out.println("Retrieving employees for group : "
				+ employeeRegistrationDTO.getGroupid());
		System.out.println("Retrieving employees for employee : "
				+ employeeRegistrationDTO.getEmployeeId());
		bind.put("companyid", employeeRegistrationDTO.getGroupid());
		bind.put("employeeid", employeeRegistrationDTO.getEmployeeId());

		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		EmployeesResultDTO employeesResultDTO = namedParameterJdbcTemplate
				.query(groupSelectSQL, paramSource, employeeResultSetExtractor);

		return employeesResultDTO;
	}

	public void updateEmployee(EmployeeRegistrationDTO employeeRegistrationDTO)
			throws Exception {
		String employeeUpdateSQL = "Update employee SET age=:age,sex=:sex,"
				+ "phone=:phone,emailid=:emailid,notes=:notes where employeeid=:employeeid";

		Map<String, Object> bind = new HashMap<String, Object>(7);
		bind.put("age", employeeRegistrationDTO.getAge());
		bind.put("sex", employeeRegistrationDTO.getSex());
		bind.put("phone", employeeRegistrationDTO.getEmpPhone());
		bind.put("emailid", employeeRegistrationDTO.getEmpEmailid());
		bind.put("notes", employeeRegistrationDTO.getAdditionalInfo());
		bind.put("employeeid", employeeRegistrationDTO.getEmployeeId());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnName = new String[] { "employeeid" };

		namedParameterJdbcTemplate.update(employeeUpdateSQL, paramSource,
				keyHolder, keyColumnName);
		// Map<String, Object> keys = keyHolder.getKeys();

	}

	public void deleteEmployee(EmployeeRegistrationDTO employeeRegistrationDTO)
			throws Exception {
		String employeeUpdateStatusSQL = "Update employee SET status=false where employeeid=:employeeid";
		Map<String, Object> bind = new HashMap<String, Object>(1);
		bind.put("employeeid", employeeRegistrationDTO.getEmployeeId());
		SqlParameterSource paramSource = new MapSqlParameterSource(bind);
		namedParameterJdbcTemplate.update(employeeUpdateStatusSQL, paramSource);
	}
}
