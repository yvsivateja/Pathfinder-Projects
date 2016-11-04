package com.pathfinder.dao;

import org.springframework.transaction.annotation.Transactional;

import com.pathfinder.filter.SessionBean;

@Transactional
public interface LoginFilterDao {
	public SessionBean getSessionDetails(String userName);
}
