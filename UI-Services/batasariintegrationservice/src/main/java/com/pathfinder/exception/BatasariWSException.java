package com.pathfinder.exception;

public class BatasariWSException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String message;
	private boolean success=false;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		success=false;
		this.success = success;
	}

	public BatasariWSException(String errorCode, String message, boolean success) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.success = false;
	}

}
