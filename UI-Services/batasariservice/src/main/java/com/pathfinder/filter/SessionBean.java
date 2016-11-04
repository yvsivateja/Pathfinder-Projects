package com.pathfinder.filter;

import java.util.Map;

public class SessionBean {
	
	private String companyid;
	private String userCompanyid;
	private boolean impersonated;
	private String companyName;
	private String companyShortKey;
	private String loggedInUserId;
	private String loggedInUserName;
	private boolean isSuperAdmin;
	private Map<Integer, String> roles;
	private Map<String, ModuleBean> modulePermissions;
	private String homePage;
	
	

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public boolean isImpersonated() {
		return impersonated;
	}

	public void setImpersonated(boolean impersonated) {
		this.impersonated = impersonated;
	}

	public String getUserCompanyid() {
		return userCompanyid;
	}

	public void setUserCompanyid(String userCompanyid) {
		this.userCompanyid = userCompanyid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyShortKey() {
		return companyShortKey;
	}

	public void setCompanyShortKey(String companyShortKey) {
		this.companyShortKey = companyShortKey;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public String getLoggedInUserName() {
		return loggedInUserName;
	}

	public void setLoggedInUserName(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}

	public Map<Integer, String> getRoles() {
		return roles;
	}

	public void setRoles(Map<Integer, String> roles) {
		this.roles = roles;
	}

	public Map<String, ModuleBean> getModulePermissions() {
		return modulePermissions;
	}

	public void setModulePermissions(Map<String, ModuleBean> modulePermissions) {
		this.modulePermissions = modulePermissions;
	}

}
