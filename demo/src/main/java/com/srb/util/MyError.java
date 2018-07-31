package com.srb.util;

public class MyError {
	private String status;
	private String errorMessage;
	private String registered;

	public MyError(String status, String errorMessage, String registered) {
		this.status = status;
		this.errorMessage = errorMessage;
		this.registered = registered;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRegistered() {
		return registered;
	}

	public void setRegistered(String registered) {
		this.registered = registered;
	}
}
