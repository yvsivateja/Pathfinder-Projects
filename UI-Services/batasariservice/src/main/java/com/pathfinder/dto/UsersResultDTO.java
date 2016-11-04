package com.pathfinder.dto;

import java.util.List;

public class UsersResultDTO {
	List<UserRegistrationDTO> userResults;

	public List<UserRegistrationDTO> getUserResults() {
		return userResults;
	}

	public void setUserResults(List<UserRegistrationDTO> userResults) {
		this.userResults = userResults;
	}

}
