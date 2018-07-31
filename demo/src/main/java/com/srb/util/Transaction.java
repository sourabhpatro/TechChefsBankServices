package com.srb.util;

public class Transaction {
	private String timeStamp;
	private String amount;
	private String transactionType;

	public Transaction(String timeStamp, String amount, String transactionType) {
		this.timeStamp = timeStamp;
		this.amount = amount;
		this.transactionType = transactionType;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
