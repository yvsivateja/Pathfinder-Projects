package com.pathfinder.dto;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class RolesDTO {

	private String roleId;
	private String companyId;

	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z ]*")
	private String roleName;
	private Boolean status;

	@NotEmpty
	private List<ModuleDTO> modules;

	private String actionType;

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<ModuleDTO> getModules() {
		return modules;
	}

	public void setModules(List<ModuleDTO> modules) {
		this.modules = modules;
	}

}
