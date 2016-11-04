package com.pathfinder.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pathfinder.dao.UserAccountDAO;

@Component
public class UserAccountDirector {

	@Autowired
	private UserAccountDAO accountDAO;

	public void changePassword(String newPassword, String loggedInUserName) {
		String encryptedPassword = generatePassword(newPassword);
		accountDAO.updatePassword(encryptedPassword,
				loggedInUserName);
	}

	private String generatePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}
}
