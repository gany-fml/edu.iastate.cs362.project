package edu.iastate.cs362.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean hasPermission;
	private List<Account> accounts;
	private String phone;
	private String password;
	private String username;
	private String name;

	public User(String name, String phone, String username, String password, boolean hasPermission) {
		this.name = name;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.hasPermission = hasPermission;
		accounts = new ArrayList<Account>();
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public boolean addAccount() {
		return accounts.add(new Account(Integer.toString(accounts.size()) + 1));
	}

	/**
	 * Get the specific account
	 * 
	 * @param id
	 *            : only the second part of account id
	 * @return the specific account
	 */
	public Account getAccount(String id) {
		try {
			int accountNumber = Integer.parseInt(id);
			if (this.getNumberOfAccounts() < accountNumber || accountNumber <= 0)
				return null;
			else
				return accounts.get(accountNumber - 1);
		} catch (java.lang.NumberFormatException e) {
			return null;
		}
	}

	public void changePassword(String newPassword) {
		this.password = newPassword;
	}

	public boolean comparePassword(String password) {
		return this.password.equals(password);
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public boolean getPermission() {
		return hasPermission;
	}

	public String getPhoneNumber() {
		return phone;
	}

	public double getTotalBalance() {
		double ret = 0;
		for (Account a : accounts)
			ret += a.getBalance();
		return ret;
	}

	public void updatePhoneNumber(String newPhoneNumber) {
		this.phone = newPhoneNumber;
	}
}
