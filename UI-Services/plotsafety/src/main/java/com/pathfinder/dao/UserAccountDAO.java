package com.pathfinder.dao;

public interface UserAccountDAO {

	public void updatePassword(String hashedPassword, String loggedInUserName);

}
