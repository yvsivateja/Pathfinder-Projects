package com.pathfinder.resultsetextractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.pathfinder.dto.ModuleDTO;
import com.pathfinder.dto.RolesDTO;
import com.pathfinder.dto.RolesResultDTO;

@Component
public class RoleResultSetExtractor implements
		ResultSetExtractor<RolesResultDTO> {

	public RolesResultDTO extractData(ResultSet resultSet) throws SQLException,
			DataAccessException {
		RolesResultDTO rolesResultDTO = new RolesResultDTO();
		List<RolesDTO> rolesDTOList = new ArrayList<RolesDTO>();
		String previousRoleId = "";
		ModuleDTO moduleDTO = new ModuleDTO();
		List<ModuleDTO> modules = new ArrayList<ModuleDTO>();
		RolesDTO rolesDTO = new RolesDTO();
		Map<String, RolesDTO> rolesMap = new HashMap<String, RolesDTO>();

		while (resultSet.next()) {
			String roleId = resultSet.getString("roleid");
			if (!previousRoleId.contentEquals(roleId)) {
				previousRoleId = roleId;

				rolesDTO = new RolesDTO();
				rolesDTO.setCompanyId(resultSet.getString("roleid"));
				rolesDTO.setRoleId(roleId);
				rolesDTO.setRoleName(resultSet.getString("rolename"));
				rolesDTO.setStatus(resultSet.getBoolean("status"));

				modules = new ArrayList<ModuleDTO>();

				moduleDTO = setModuleBean(resultSet, roleId);
			} else {
				moduleDTO = setModuleBean(resultSet, roleId);
			}
			modules.add(moduleDTO);
			rolesDTO.setModules(modules);
			rolesMap.put(roleId, rolesDTO);
		}

		for (String key : rolesMap.keySet()) {
			rolesDTOList.add(rolesMap.get(key));
		}
		rolesResultDTO.setRolesList(rolesDTOList);
		rolesResultDTO.setRolesMap(rolesMap);
		return rolesResultDTO;
	}

	private ModuleDTO setModuleBean(ResultSet resultSet, String roleId)
			throws SQLException {
		ModuleDTO moduleDTO;
		moduleDTO = new ModuleDTO();
		moduleDTO.setCanCreate(resultSet.getBoolean("cancreate"));
		moduleDTO.setCanUpdate(resultSet.getBoolean("canupdate"));
		moduleDTO.setCanDelete(resultSet.getBoolean("candelete"));
		moduleDTO.setCanView(resultSet.getBoolean("canview"));
		moduleDTO.setModuleId(resultSet.getString("rolesaclid"));
		moduleDTO.setModuleName(resultSet.getString("module"));
		moduleDTO.setRoleId(roleId);
		return moduleDTO;
	}
}
