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
	private Loan loan;
	private CertificateOfDeposit cd;

	protected User(String name, String phone, String username, String password,
			boolean hasPermission) {
		this.name = name;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.hasPermission = hasPermission;
		accounts = new ArrayList<Account>();
	}

	protected List<Account> getAccounts() {
		return accounts;
	}

	protected boolean addAccount() {
		return accounts.add(new Account(Integer.toString(accounts.size() + 1)));
	}

	/**
	 * Get the specific account
	 * 
	 * @param id
	 *            : only the second part of account id
	 * @return the specific account
	 */
	protected Account getAccount(String id) {
		try {
			int accountNumber = Integer.parseInt(id);
			if (accounts.size() < accountNumber || accountNumber <= 0)
				return null;
			else
				return accounts.get(accountNumber - 1);
		} catch (java.lang.NumberFormatException e) {
			return null;
		}
	}

	protected void changePassword(String newPassword) {
		this.password = newPassword;
	}

	protected boolean comparePassword(String password) {
		return this.password.equals(password);
	}

	protected String getName() {
		return name;
	}

	protected String getUsername() {
		return username;
	}

	protected boolean getPermission() {
		return hasPermission;
	}

	protected String getPhoneNumber() {
		return phone;
	}

	protected double getTotalBalance() {
		double ret = 0;
		for (Account a : accounts)
			ret += a.getBalance();
		return ret;
	}

	protected void updatePhoneNumber(String newPhoneNumber) {
		this.phone = newPhoneNumber;
	}

	protected void deleteAccount(String accountID) {
		int accountNumber = Integer.parseInt(accountID);
		accounts.remove(accountNumber - 1);
	}

	protected boolean putLoan(Loan newLoan) {
		loan = newLoan;
		System.out.println("Loan amount of: " + loan.getLoanAmount() + " for: " + username + " has been requested!");
		return true;
	}

	protected Loan getLoan() {
		return loan;
	}

	public CertificateOfDeposit fixedDeposit(double amount, int months) {
		return cd = new CertificateOfDeposit(amount, months);
	}
}
