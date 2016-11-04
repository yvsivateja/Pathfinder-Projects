package com.pathfinder.dao;

import java.util.Map;

import com.pathfinder.dto.UserProfileDTO;
import com.pathfinder.dto.UserRegistrationDTO;
import com.pathfinder.dto.UsersResultDTO;

public interface UserDAO {

	public void saveUser(UserRegistrationDTO userRegistrationDTO);

	public UsersResultDTO getUser(UserRegistrationDTO userRegistrationDTO);
	
	boolean isUniqueUserName(String userName);

	public void updateUser(UserRegistrationDTO userRegistrationDTO);

	public void deleteUser(UserRegistrationDTO userRegistrationDTO);

	public Map<String, String> getAvailableEmployees(String companyid);

	public Map<String, String> getAvailableRoles(String companyid);

	public UserRegistrationDTO getUserRoles(UserRegistrationDTO userRegistrationDTO);

	public UserProfileDTO getUserProfile(String loggedInUserId);
	
	public void changeUserPassword(UserProfileDTO userProfileDTO);

}
