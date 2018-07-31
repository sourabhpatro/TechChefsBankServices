package com.srb.util;

public class Account {
	private String accountType;
	private String accountNumber;

	public Account(String accountType, String accountNumber) {
		this.accountType = accountType;
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
