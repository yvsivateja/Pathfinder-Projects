package com.pathfinder.dto;

import java.util.Date;

public class DeviceDTO {

	private String id;
	private String deviceID;
	private String deviceName;
	private String phoneNumber;
	private String companyShortKey;
	private String userIdentifier;
	private String companyID;
	private Date createdTime;
	private String addtionalInfo;
	private boolean canStart;
	private boolean canStop;
	private boolean canUninstall;
	private String employeeID;
	private boolean approved;
	private String approvedBy;
	private String actionType;

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompanyShortKey() {
		return companyShortKey;
	}

	public void setCompanyShortKey(String companyShortKey) {
		this.companyShortKey = companyShortKey;
	}

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getAddtionalInfo() {
		return addtionalInfo;
	}

	public void setAddtionalInfo(String addtionalInfo) {
		this.addtionalInfo = addtionalInfo;
	}

	public boolean isCanStart() {
		return canStart;
	}

	public void setCanStart(boolean canStart) {
		this.canStart = canStart;
	}

	public boolean isCanStop() {
		return canStop;
	}

	public void setCanStop(boolean canStop) {
		this.canStop = canStop;
	}

	public boolean isCanUninstall() {
		return canUninstall;
	}

	public void setCanUninstall(boolean canUninstall) {
		this.canUninstall = canUninstall;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

}
