package com.pathfinder.dto;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.NotEmpty;

public class UserProfileDTO {

	private String groupId;
	private String employeeId;
	private String userId;
	private String empName;
	private String empPhone;
	private String empEmailid;
	private String age;
	private String sex;
	private String username;

	@NotEmpty
	private String password;

	@NotEmpty
	private String repassword;
	
	@SuppressWarnings("unused")
	private boolean rePasswordValid;

	private String additionalInfo;

	@AssertTrue
	public boolean isRePasswordValid() {
		try {
			if (this.password.contentEquals(this.repassword))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public void setRePasswordValid(boolean rePasswordValid) {
		this.rePasswordValid = rePasswordValid;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
