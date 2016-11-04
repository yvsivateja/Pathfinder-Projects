package com.pathfinder.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class EmployeeRegistrationDTO {

	private String groupid;

	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z ]*")
	private String empName;

	@NotEmpty
	@Pattern(regexp = "[0-9]*")
	private String empPhone;

	@NotBlank
	@Email
	private String empEmailid;

	@NotNull
	@Pattern(regexp = "[0-9]*")
	@Min(value = 18)
	@Max(value = 100)
	private String age;

	@NotEmpty
	private String sex;
	private String additionalInfo;
	private String employeeId;
	private String actionType;// for showgroups

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpEmailid() {
		return empEmailid;
	}

	public void setEmpEmailid(String empEmailid) {
		this.empEmailid = empEmailid;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

}
