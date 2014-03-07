package edu.iastate.cs362.project;

import java.io.Serializable;

public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private double balance;
	private String accountID;
	private boolean lock;
	
	public Account(String id)
	{
		balance = 0;
		accountID = id;
		lock = false;
	}
	
	public String getAccountID()
	{
		return accountID;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public void lockAccount()
	{
		lock = true;
	}
	
	public void unlockAccount()
	{
		lock = false;
	}
	
	public boolean checkLockStatus()
	{
		return lock;
	}
	
	public void deposit(double amount)
	{
		balance += amount;
	}
	
	public void withdraw(double amount)
	{
		balance -= amount;
	}
}
