package com.security.demo;

public class StandardResponse {
	

	String text;
	Boolean errorneous;

	public StandardResponse(String text, Boolean errorneous) {
		super();
		this.text = text;
		this.errorneous = errorneous;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	
	public Boolean getErrorneous() {
		return errorneous;
	}

	public void setErrorneous(Boolean errorneous) {
		this.errorneous = errorneous;
	}

}
