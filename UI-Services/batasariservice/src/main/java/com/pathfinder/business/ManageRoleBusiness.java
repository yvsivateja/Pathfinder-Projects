package com.pathfinder.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.pathfinder.dao.RoleDAO;
import com.pathfinder.dto.RolesDTO;
import com.pathfinder.dto.RolesResultDTO;
import com.pathfinder.util.UserAccessManagementUtil;

@Component
public class ManageRoleBusiness {

	@Autowired
	RoleDAO roleDAO;

	public void saveorMergeRole(RolesDTO rolesDTO, BindingResult result,
			Model model) {
		if (UserAccessManagementUtil.checkIsEmpty(rolesDTO.getRoleId()))
			roleDAO.saveRoles(rolesDTO);
		else
			roleDAO.updateRole(rolesDTO);

	}

	public RolesResultDTO getRoles(RolesDTO rolesDTO, BindingResult result,
			Model model) {
		if (UserAccessManagementUtil.checkIsEmpty(rolesDTO.getRoleId())) {
			rolesDTO.setRoleId("%");
		}

		RolesResultDTO rolesResultDTO = roleDAO.getRole(rolesDTO);
		return rolesResultDTO;
	}

	public void deleteRole(RolesDTO rolesDTO) {
		if (!UserAccessManagementUtil.checkIsEmpty(rolesDTO.getRoleId())) {
			roleDAO.deleteRole(rolesDTO);
		}
	}

}
