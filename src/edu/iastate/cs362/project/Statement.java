package edu.iastate.cs362.project;

import java.util.List;

public class Statement {

	User user;
	private String name;
	private String phoneNumber;
	private double totalAmount;

	protected Statement(User u) {
		user = u;
		name = user.getName();
		phoneNumber = user.getPhoneNumber();
		totalAmount = user.getTotalBalance();
	}

	protected Statement generateStatement() {
		for (Account a : user.getAccounts()) {

		}
		return null;
	}

	protected String getName() {
		return name;
	}

	protected String getPhoneNumber() {
		return phoneNumber;
	}

	protected List<Account> getAccounts() {
		return user.getAccounts();
	}

	protected double getTotalAmount() {
		return totalAmount;
	}

}
