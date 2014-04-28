package edu.iastate.cs362.project;

public class Loan {

	private double loanAmount;
	private boolean loanStatus;

	public Loan(double loanAmount) {
		this.loanAmount = loanAmount;
		loanStatus = false;
	}

	public double getLoanAmount(){
		return loanAmount;
	}
	
	public boolean getStatus() {
		return loanStatus;
	}
	
	public boolean approveLoan(){
		loanStatus = true;
		return true;
	}
}
