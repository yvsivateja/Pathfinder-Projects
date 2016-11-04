package com.pathfinder.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.pathfinder.dao.GroupDao;
import com.pathfinder.dto.GroupRegistrationDTO;
import com.pathfinder.dto.GroupsResultDTO;
import com.pathfinder.util.UserAccessManagementUtil;

@Component
public class ManageGroupBusiness {

	@Autowired
	GroupDao groupDao;

	public void saveorMergeGroup(GroupRegistrationDTO groupRegistrationDTO,
			BindingResult result, Model model) {
		if (UserAccessManagementUtil.checkIsEmpty(groupRegistrationDTO.getGroupid()))
			groupDao.saveGroup(groupRegistrationDTO);
		else
			groupDao.updateGroup(groupRegistrationDTO);
	}

	public GroupsResultDTO getGroups(GroupRegistrationDTO groupRegistrationDTO,
			BindingResult result, Model model) {
		if (UserAccessManagementUtil.checkIsEmpty(groupRegistrationDTO.getGroupid())) {
			groupRegistrationDTO.setGroupid("%");
		}
		GroupsResultDTO groupsResultDTO = groupDao
				.getGroup(groupRegistrationDTO);
		return groupsResultDTO;
	}

	public void deleteGroup(GroupRegistrationDTO groupRegistrationDTO) {
		if (!UserAccessManagementUtil.checkIsEmpty(groupRegistrationDTO.getGroupid())) {
			groupDao.deleteGroup(groupRegistrationDTO);
		}
	}

	

}
