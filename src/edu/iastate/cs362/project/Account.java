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

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		balance -= amount;
	}
}
