package edu.iastate.cs362.project;

import java.io.Serializable;

public class CertificateOfDeposit implements Serializable {

	private static final long serialVersionUID = 1L;
	private double amount;
	private int months;

	public CertificateOfDeposit(double amount, int months) {
		this.amount = amount;
		this.months = months;
	}

	protected double calculateInterest() {
		// TODO Iteration 4
		return 0;
	}
	
	protected double closeDeposit() {
		// TODO Iteration 4
		return 0;
	}
}
