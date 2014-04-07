package edu.iastate.cs362.project;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	private double balance;
	private String accountID;
	private boolean lockStatus;

	public Account(String id) {
		balance = 0;
		accountID = id;
		lockStatus = false;
	}

	public String getAccountID() {
		return accountID;
	}

	public double getBalance() {
		return balance;
	}

	public void lockAccount() {
		lockStatus = true;
	}

	public void unlockAccount() {
		lockStatus = false;
	}

	public boolean checkLockStatus() {
		return lockStatus;
	}

	public boolean deposit(double amount) {
		if (amount <= 0)
			return false;
		balance += amount;
		return true;
	}

	public boolean withdraw(double amount) {
		if (amount <= 0)
			return false;
		if (balance >= amount) {
			balance -= amount;
			return true;
		} else
			return false;
	}
}
