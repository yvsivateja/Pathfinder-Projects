package com.pathfinder.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.pathfinder.validator.Unique;
import com.pathfinder.validator.UniqueShortKey;

public class GroupRegistrationDTO {

	private String groupid;

	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z ]*")
	private String groupName;

	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z]*")
	@UniqueShortKey
	private String shortKey;

	@NotEmpty
	private String address1;
	private String address2;
	private String address3;

	@NotEmpty
	private String city;

	@NotEmpty
	private String country;

	@NotEmpty
	private String state;

	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z ]*")
	private String empName;

	@NotEmpty
	@Unique
	private String username;

	private String userNameDisplay;

	@NotEmpty
	@Pattern(regexp = "[0-9]*")
	private String empPhone;

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
	private String groupEmployeeId;
	private String actionType;// for showgroups

	public String getUserNameDisplay() {
		return userNameDisplay;
	}

	public void setUserNameDisplay(String userNameDisplay) {
		this.userNameDisplay = userNameDisplay;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getShortKey() {
		return shortKey;
	}

	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getGroupEmployeeId() {
		return groupEmployeeId;
	}

	public void setGroupEmployeeId(String groupEmployeeId) {
		this.groupEmployeeId = groupEmployeeId;
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

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
