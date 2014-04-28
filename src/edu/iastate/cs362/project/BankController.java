package edu.iastate.cs362.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class BankController implements Serializable {

	private Bank bank;
	private File bankData;
	private static final long serialVersionUID = 1L;

	public BankController(File bankData) {
		this.bankData = bankData;
		bank = new Bank(bankData);
	}

	public boolean userLogin(String username, String password) {
		return bank.userLogin(username, password);
	}

	public boolean userLogout() {
		return bank.logout();
	}

	public User getLoginUser() {
		return bank.getLoginUser();
	}

	public boolean createUser(String name, String phone, String username, String password,
			boolean hasPermission) {
		return bank.createUser(name, phone, username, password, hasPermission);
	}

	public boolean createAccount(String username) {
		return bank.createAccount(username);
	}

	public double getBalance(String accountID) {
		return bank.getBalance(accountID);
	}

	public double getTotalBalance(String accountID) {
		return bank.getTotalBalance(accountID);
	}

	public String getPhoneNumber(String username) {
		return bank.getPhoneNumber(username);
	}

	public boolean lockAccount(String accountID) {
		return bank.lockAccount(accountID);
	}

	public boolean unlockAccount(String accountID) {
		return bank.unlockAccount(accountID);
	}

	public boolean changePassword(String username, String newPassword) {
		return bank.changePassword(username, newPassword);
	}

	public boolean updatePhoneNumber(String username, String newPhoneNumber) {
		return bank.updatePhoneNumber(username, newPhoneNumber);
	}

	public boolean depositMoney(String accountID, double depositAmount) {
		return bank.depositMoney(accountID, depositAmount);
	}

	public boolean withdrawMoney(String accountID, double withdrawAmount) {
		return bank.withdrawMoney(accountID, withdrawAmount);
	}

	public boolean transferMoney(String accountFrom, String accountTo, double transferAmount) {
		return bank.transferMoney(accountFrom, accountTo, transferAmount);
	}
	
	public boolean approveLoan(String username){
		return bank.approveLoan(username);
	}
	
	public boolean requestLoan(double loanAmount){
		return bank.requestLoan(loanAmount);
	}
	
	public boolean checkLoanStatus(String username){
		return bank.checkLoanStatus(username);
	}
	
	public boolean closeAccount(String accountID, String accountTransferID){
		return bank.closeAccount(accountID, accountTransferID);
	}
	
	public boolean fixedDeposit(String accountID, double amount, int months){
		return bank.fixedDeposit(accountID, amount, months);
	}
	
	public List<String> searchTransactionLog(String accountID, String date){
		return bank.searchTransactionLog(accountID, date);
	}
	
	public void updateDatabase() {
		try {
			FileOutputStream outStream = new FileOutputStream(bankData);
			ObjectOutputStream outObject = new ObjectOutputStream(outStream);
			outObject.writeObject(bank.getDatabase());
			outObject.close();
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		bank.logout();
	}

}
