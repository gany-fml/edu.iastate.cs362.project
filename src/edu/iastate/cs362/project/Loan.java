package edu.iastate.cs362.project;

import java.io.Serializable;

public class Loan implements Serializable {

	private static final long serialVersionUID = 1L;
	private double loanAmount;
	private boolean loanStatus;

	public Loan(double loanAmount) {
		this.loanAmount = loanAmount;
		loanStatus = false;
	}

	protected double getLoanAmount(){
		return loanAmount;
	}
	
	protected boolean getStatus() {
		return loanStatus;
	}
	
	protected boolean approveLoan(){
		loanStatus = true;
		return true;
	}
}
