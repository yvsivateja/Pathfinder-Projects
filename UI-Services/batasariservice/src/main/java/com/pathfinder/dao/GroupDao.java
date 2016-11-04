package com.pathfinder.dao;

import com.pathfinder.dto.GroupRegistrationDTO;
import com.pathfinder.dto.GroupsResultDTO;

public interface GroupDao {

	public void saveGroup(GroupRegistrationDTO groupRegistrationDTO);

	public GroupsResultDTO getGroup(GroupRegistrationDTO groupRegistrationDTO);

	public void updateGroup(GroupRegistrationDTO groupRegistrationDTO);

	public void deleteGroup(GroupRegistrationDTO groupRegistrationDTO);
	
	public boolean isUniqueShortKey(String shortkey);
}
