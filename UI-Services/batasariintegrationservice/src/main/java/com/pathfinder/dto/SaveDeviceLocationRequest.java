package com.pathfinder.dto;

public class SaveDeviceLocationRequest {

	private String deviceId;
	private String longitude;
	private String latitude;
	private String mapURL;
	private String addInfo;
	private String dateTriggerd;

	public String getDateTriggerd() {
		return dateTriggerd;
	}

	public void setDateTriggerd(String dateTriggerd) {
		this.dateTriggerd = dateTriggerd;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getMapURL() {
		return mapURL;
	}

	public void setMapURL(String mapURL) {
		this.mapURL = mapURL;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

}
