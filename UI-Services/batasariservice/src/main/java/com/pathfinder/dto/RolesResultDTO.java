package com.pathfinder.dto;

import java.util.List;
import java.util.Map;

public class RolesResultDTO {

	private List<RolesDTO> rolesList;
	private Map<String, RolesDTO> rolesMap;

	public Map<String, RolesDTO> getRolesMap() {
		return rolesMap;
	}

	public void setRolesMap(Map<String, RolesDTO> rolesMap) {
		this.rolesMap = rolesMap;
	}

	public List<RolesDTO> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<RolesDTO> rolesList) {
		this.rolesList = rolesList;
	}

}
