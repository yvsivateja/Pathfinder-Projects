package com.pathfinder.dto;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.NotEmpty;

import com.pathfinder.validator.Unique;

public class UserRegistrationDTO {

	private String userId;

	@NotEmpty
	@Unique
	private String username;

	private String usernameDisplay;

	@NotEmpty
	private String password;

	@NotEmpty
	private String repassword;

	private boolean active;
	private String actionType;// to identify update/delete action

	@NotEmpty
	private String employeeId;

	private String employeeName;
	private String groupId;
	private boolean isSuperAdmin;
	private String companyShortKey;

	@NotEmpty
	private List<String> selectedroles;

	private Map<String, String> availableEmployees;
	private Map<String, String> selectedRolesTODisplay;
	private List<String> availableroles;
	private Map<String, String> availableRolesTODisplay;
	private String rolesDisplay;// to display roles as a single variable in grid
	
	@SuppressWarnings("unused")
	private boolean rePasswordValid;

	public String getUsernameDisplay() {
		return usernameDisplay;
	}

	public void setUsernameDisplay(String usernameDisplay) {
		this.usernameDisplay = usernameDisplay;
	}

	public String getCompanyShortKey() {
		return companyShortKey;
	}

	public void setCompanyShortKey(String companyShortKey) {
		this.companyShortKey = companyShortKey;
	}

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

	public Map<String, String> getAvailableEmployees() {
		return availableEmployees;
	}

	public void setAvailableEmployees(Map<String, String> availableEmployees) {
		this.availableEmployees = availableEmployees;
	}

	public Map<String, String> getSelectedRolesTODisplay() {
		return selectedRolesTODisplay;
	}

	public void setSelectedRolesTODisplay(
			Map<String, String> selectedRolesTODisplay) {
		this.selectedRolesTODisplay = selectedRolesTODisplay;
	}

	public Map<String, String> getAvailableRolesTODisplay() {
		return availableRolesTODisplay;
	}

	public void setAvailableRolesTODisplay(
			Map<String, String> availableRolesTODisplay) {
		this.availableRolesTODisplay = availableRolesTODisplay;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRolesDisplay() {
		return rolesDisplay;
	}

	public void setRolesDisplay(String rolesDisplay) {
		this.rolesDisplay = rolesDisplay;
	}

	public String getRepassword() {
		return repassword;
	}

	public List<String> getSelectedroles() {
		return selectedroles;
	}

	public void setSelectedroles(List<String> selectedroles) {
		this.selectedroles = selectedroles;
	}

	public List<String> getAvailableroles() {
		return availableroles;
	}

	public void setAvailableroles(List<String> availableroles) {
		this.availableroles = availableroles;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
