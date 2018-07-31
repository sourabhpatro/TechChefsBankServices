package com.security.demo;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srb.dao.BankDAO;
import com.srb.util.Account;
import com.srb.util.Bank;
import com.srb.util.MyError;
import com.srb.util.Transaction;
import com.srb.util.User;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/home")
public class RestController {
	BankDAO bankDAO = new BankDAO();

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<Object> register(@RequestBody User user) {
		if (bankDAO.registerUser(user)) {
			return new ResponseEntity("OK", HttpStatus.OK);
		} else
			return new ResponseEntity(new MyError("BAD_REQUEST", "UserName already Exists", "YES"),
					HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Object> login(@RequestBody User user) {
		return bankDAO.login(user);
	}

	@RequestMapping(value = "/getbanklist", produces = { "application/JSON" }, method = RequestMethod.GET)
	@ResponseBody
	public List<String> getBankList() {
		return bankDAO.getBankList();
	}

	@GetMapping("/getbankdata/{bankName}")
	@ResponseBody
	public Bank getBankData(@PathVariable("bankName") String bankName) {
		return bankDAO.getBankData(bankName);
	}

	@PostMapping("/logintobank/{bankName}")
	@ResponseBody
	public ResponseEntity<String> getBankData(@PathVariable("bankName") String bankName, @RequestBody Bank bank) {
		bank.setBankName(bankName);
		if (bankDAO.loginToBank(bank)) {
			return new ResponseEntity("OK", HttpStatus.OK);
		} else {
			return new ResponseEntity("You are not Authorized to log into " + bankName, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getaccounts/{bankname}")
	@ResponseBody
	public ResponseEntity<Object> getAccounts(@PathVariable("bankname") String bankName) {
		return bankDAO.getAccounts(bankName);
	}

	@GetMapping("/gettransactiondata/{accountNumber}")
	@ResponseBody
	public List<Transaction> getAccountTransactions(@PathVariable("accountNumber") String accountNumber) {
		return bankDAO.getAccountTransactions(accountNumber);
	}

}
