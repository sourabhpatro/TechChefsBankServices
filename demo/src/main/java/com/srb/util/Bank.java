package com.srb.util;

public class Bank {
	private String bankName;
	private String userName;
	private String password;
	private String corpId;

	public Bank() {
	}

	public Bank(String bankName, String userName, String password, String corpId) {
		this.bankName = bankName;
		this.userName = userName;
		this.password = password;
		this.corpId = corpId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

}
