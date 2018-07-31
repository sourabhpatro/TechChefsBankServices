package com.srb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.srb.util.Account;
import com.srb.util.Bank;
import com.srb.util.MyError;
import com.srb.util.Transaction;
import com.srb.util.User;

public class BankDAO {

	public boolean registerUser(User user) {
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_schema", "sourabh",
					"$Unny1993");
			PreparedStatement ps = con.prepareStatement("select count(*) from USERS where USER_NAME = ?");
			ps.setString(1, user.getUserName());
			ResultSet rs = ps.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				return false;
			}
			ps = con.prepareStatement("INSERT INTO USERS VALUES(?,?)");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.execute();
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ResponseEntity<Object> login(User user) {
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_schema", "sourabh",
					"$Unny1993");
			PreparedStatement ps = con.prepareStatement("select PASSWORD from USERS where USER_NAME = ?");
			ps.setString(1, user.getUserName());
			ResultSet rs = ps.executeQuery();
			if (rs.next() && rs.getString(1) != null) {
				if (rs.getString(1).equals(user.getPassword())) {
					return new ResponseEntity("OK", HttpStatus.OK);

				} else {
					return new ResponseEntity(new MyError("BAD_REQUEST", "Invalid Credentials", "YES"),
							HttpStatus.BAD_REQUEST);
				}

			} else {
				return new ResponseEntity(new MyError("BAD_REQUEST", "User not found", "NO"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new MyError("BAD_REQUEST", e.getMessage(), "NO"), HttpStatus.BAD_REQUEST);
		}
	}

	public List<String> getBankList() {
		List<String> list = new ArrayList<String>();
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_schema", "sourabh",
					"$Unny1993");
			PreparedStatement ps = con.prepareStatement("select BANK_NAME from BANKS");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Bank getBankData(String bankName) {
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_schema", "sourabh",
					"$Unny1993");
			PreparedStatement ps = con
					.prepareStatement("select BANK_NAME,USER_NAME,PASSWORD,CORP_ID from BANKS where BANK_NAME=?");
			ps.setString(1, bankName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Bank(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			} else {
				return new Bank("", "", "", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Bank("", "", "", "");
		}
	}

	public boolean loginToBank(Bank bank) {
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_schema", "sourabh",
					"$Unny1993");
			PreparedStatement ps = con.prepareStatement(
					"select count(*) from BANKS where BANK_NAME=? AND USER_NAME=? AND PASSWORD=? AND CORP_ID=?");
			ps.setString(1, bank.getBankName());
			ps.setString(2, bank.getUserName());
			ps.setString(3, bank.getPassword());
			ps.setString(4, bank.getCorpId());
			ResultSet rs = ps.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				ps = con.prepareStatement(
						"UPDATE BANKS SET LOGGED_IN=1 where BANK_NAME=? AND USER_NAME=? AND PASSWORD=? AND CORP_ID=?");
				ps.setString(1, bank.getBankName());
				ps.setString(2, bank.getUserName());
				ps.setString(3, bank.getPassword());
				ps.setString(4, bank.getCorpId());
				ps.executeUpdate();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ResponseEntity<Object> getAccounts(String bankName) {
		List<Account> list = new ArrayList<Account>();
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_schema", "sourabh",
					"$Unny1993");
			PreparedStatement ps = con.prepareStatement(
					"select ACCOUNT_TYPE, ACCOUNT_NUMBER from Accounts acc, banks bnk where acc.bank_name=bnk.bank_name and bnk.BANK_NAME=? and bnk.logged_in=1");
			ps.setString(1, bankName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Account(rs.getString(1), rs.getString(2)));
			}
			if (list.size() > 0)
				return new ResponseEntity(list, HttpStatus.OK);
			else
				return new ResponseEntity(new MyError("BAD_REQUEST", "You are not logged into " + bankName, "NO"),
						HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Transaction> getAccountTransactions(String accountNumber) {
		List<Transaction> list = new ArrayList<Transaction>();
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_schema", "sourabh",
					"$Unny1993");
			PreparedStatement ps = con.prepareStatement(
					"select TRANSACTION_TS, TRANSACTION_AMOUNT,TRANSACTION_TYPE from TRANSACTIONS WHERE ACCOUNT_NUMBER=?");
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Transaction(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
