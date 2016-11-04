package com.pathfinder.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pathfinder.dao.LoginFilterDao;
import com.pathfinder.filter.SessionBean;

@Component
public class LoginFilterBusiness {

	@Autowired
	private LoginFilterDao loginFilterDao;

	public SessionBean getSessionDetails(String userName) {

		SessionBean sessionbean = loginFilterDao.getSessionDetails(userName);
		return sessionbean;
	}
}
