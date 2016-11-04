package com.pathfinder.dto;

public class WSDeviceAuthenticateResponse {

	private boolean success;
	private String message;
	private boolean canStart;
	private boolean canStop;
	private boolean canUninstall;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

}
