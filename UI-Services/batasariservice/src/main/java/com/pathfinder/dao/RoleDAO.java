package com.pathfinder.dao;

import com.pathfinder.dto.RolesDTO;
import com.pathfinder.dto.RolesResultDTO;

public interface RoleDAO {

	public void saveRoles(RolesDTO rolesDTO);

	public void updateRole(RolesDTO rolesDTO);

	public RolesResultDTO getRole(RolesDTO rolesDTO);

	public void deleteRole(RolesDTO rolesDTO);
}
