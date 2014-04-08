package edu.iastate.cs362.project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	private double balance;
	private String accountID;
	private boolean lockStatus;
	private List<String> statementLog;

	protected Account(String id) {
		balance = 0;
		accountID = id;
		lockStatus = false;
	}

	protected String getAccountID() {
		return accountID;
	}

	protected double getBalance() {
		return balance;
	}

	protected void lockAccount() {
		lockStatus = true;
	}

	protected void unlockAccount() {
		lockStatus = false;
	}

	protected boolean checkLockStatus() {
		return lockStatus;
	}

	protected boolean deposit(double amount) {
		if (amount <= 0)
			return false;
		balance += amount;
		return true;
	}

	protected boolean withdraw(double amount) {
		if (amount <= 0)
			return false;
		if (balance >= amount) {
			balance -= amount;
			return true;
		} else
			return false;
	}

	protected void addToLogWithTimestamp(String text) {
		statementLog.add(text + " -- " + new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime()));
	}
}
