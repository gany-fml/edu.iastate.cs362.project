package edu.iastate.cs362.project;

public class Loan {

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
