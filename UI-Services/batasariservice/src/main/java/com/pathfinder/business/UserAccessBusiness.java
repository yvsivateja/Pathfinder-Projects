package com.pathfinder.business;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pathfinder.dao.UserDAO;
import com.pathfinder.dto.UserProfileDTO;
import com.pathfinder.dto.UserRegistrationDTO;
import com.pathfinder.dto.UsersResultDTO;
import com.pathfinder.util.UserAccessManagementUtil;

@Component
public class UserAccessBusiness {

	@Autowired
	UserDAO userDAO;

	public Map<String, String> getAvailableEmployees(String companyid) {

		return userDAO.getAvailableEmployees(companyid);

	}

	public void saveOrMergeUser(UserRegistrationDTO userRegistrationDTO) {
		// TODO Auto-generated method stub

		userRegistrationDTO.setSuperAdmin(false);

		if (UserAccessManagementUtil.checkIsEmpty(userRegistrationDTO
				.getUserId()))
			userDAO.saveUser(userRegistrationDTO);
		else
			userDAO.updateUser(userRegistrationDTO);
	}

	public Map<String, String> getAvailableRoles(String companyid) {
		// TODO Auto-generated method stub
		return userDAO.getAvailableRoles(companyid);
	}

	public UsersResultDTO getUsers(UserRegistrationDTO userRegistrationDTO) {
		if (UserAccessManagementUtil.checkIsEmpty(userRegistrationDTO
				.getUserId())) {
			userRegistrationDTO.setUserId("%");
		}
		UsersResultDTO usersResultDTO = userDAO.getUser(userRegistrationDTO);
		return usersResultDTO;
	}

	public UserRegistrationDTO getUserRoles(
			UserRegistrationDTO userRegistrationDTO) {
		userRegistrationDTO = userDAO.getUserRoles(userRegistrationDTO);
		return userRegistrationDTO;
	}

	public void deleteUser(UserRegistrationDTO userRegistrationDTO,
			String loggedInuserId) {
		if (!userRegistrationDTO.getUserId().contentEquals(loggedInuserId))
			userDAO.deleteUser(userRegistrationDTO);
	}

	public UserProfileDTO getUserProfile(String loggedInUserId) {
		return userDAO.getUserProfile(loggedInUserId);
	}

	public void changeUserPassword(UserProfileDTO userProfileDTO) {
		userDAO.changeUserPassword(userProfileDTO);
	}

}
