package com.pathfinder.dto;

import java.util.List;

public class GroupsResultDTO {

	private List<GroupRegistrationDTO> groupRegistrationDTOs;
	private boolean showImpersonate;

	public boolean isShowImpersonate() {
		return showImpersonate;
	}

	public void setShowImpersonate(boolean showImpersonate) {
		this.showImpersonate = showImpersonate;
	}

	public List<GroupRegistrationDTO> getGroupRegistrationDTOs() {
		return groupRegistrationDTOs;
	}

	public void setGroupRegistrationDTOs(
			List<GroupRegistrationDTO> groupRegistrationDTOs) {
		this.groupRegistrationDTOs = groupRegistrationDTOs;
	}
}
