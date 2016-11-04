package com.pathfinder.dto;

import java.util.Date;

public class DeviceLocationDTO {

	private String id;
	private String deviceId;
	private String companyId;
	private String latitude;
	private String longitude;
	private String mapURL;
	private Date dateTriggerd;
	private String onDate;
	private Date createdTimestamp;
	private String prevlatitude;
	private String prevlongitude;
	private String distance;
	private String address;
	private String addInfo;

	public String getOnDate() {
		return onDate;
	}

	public void setOnDate(String onDate) {
		this.onDate = onDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMapURL() {
		return mapURL;
	}

	public void setMapURL(String mapURL) {
		this.mapURL = mapURL;
	}

	public Date getDateTriggerd() {
		return dateTriggerd;
	}

	public void setDateTriggerd(Date dateTriggerd) {
		this.dateTriggerd = dateTriggerd;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getPrevlatitude() {
		return prevlatitude;
	}

	public void setPrevlatitude(String prevlatitude) {
		this.prevlatitude = prevlatitude;
	}

	public String getPrevlongitude() {
		return prevlongitude;
	}

	public void setPrevlongitude(String prevlongitude) {
		this.prevlongitude = prevlongitude;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

}
