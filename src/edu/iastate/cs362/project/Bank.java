package edu.iastate.cs362.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

public class Bank implements Serializable {

	private static final long serialVersionUID = 1L;
	private Database bankDatabase;
	private boolean hasPermission;
	private boolean hasLogin;
	private User loginUser;

	protected Bank(File file1) {
		File file = file1;
		hasLogin = false;
		loginUser = null;
		hasPermission = false;
		try {
			if (!file.isFile()) {
				System.out.println("Bank data file not exist, creating new one...");
				file.createNewFile();
				Database init = new Database(file);
				User root = new User("root", "123", "root", "root", true);
				init.putUser("root", root);
			}
			FileInputStream iFile = new FileInputStream(file);
			ObjectInputStream oFile = new ObjectInputStream(iFile);
			bankDatabase = (Database) oFile.readObject();
			oFile.close();
			iFile.close();
		} catch (ClassNotFoundException e) {
			System.out.println("File type not correct, system exit");
		} catch (IOException e) {
			System.out.println("File can not be read");
		}
	}

	protected boolean userLogin(String username, String password) {
		User u = bankDatabase.getUser(username);
		if (u != null && u.comparePassword(password)) {
			this.loginUser = u;
			hasLogin = true;
			hasPermission = u.getPermission();
			return true;
		} else {
			return false;
		}
	}

	protected boolean createUser(String name, String phone, String username, String password,
			boolean hasPermission) {
		User u = new User(name, phone, username, password, hasPermission);
		return bankDatabase.putUser(username, u);
	}

	protected boolean createAccount(String username) {
		User u = bankDatabase.getUser(username);
		if (u != null) {
			u.addAccount();
			return this.bankDatabase.putUser(username, u);
		} else {
			return false;
		}
	}

	protected Double getBalance(String accountID) {
		String username = accountID.split("-")[0];
		User u = bankDatabase.getUser(username);
		if (u == null) {
			return null;
		}
		Account account = u.getAccount((accountID.split("-")[1]));
		if (account == null) {
			return null;
		} else {
			return account.getBalance();
		}
	}

	protected Double getTotalBalance(String username) {
		User u = bankDatabase.getUser(username);
		if (u == null) {
			return null;
		} else
			return u.getTotalBalance();
	}

	protected String getPhoneNumber(String username) {
		User u = bankDatabase.getUser(username);
		if (u == null) {
			return null;
		} else
			return u.getPhoneNumber();
	}

	protected boolean lockAccount(String accountID) {
		if (!accountID.contains("-")) {
			return false;
		}
		User u = bankDatabase.getUser(accountID.split("-")[0]);
		if (u == null) {
			return false;
		}
		Account account = u.getAccount(accountID.split("-")[1]);
		if (account == null) {
			return false;
		} else {
			account.lockAccount();
			return this.bankDatabase.putUser(accountID.split("-")[0], u);
		}
	}

	protected boolean unlockAccount(String accountID) {
		User u = bankDatabase.getUser(accountID.split("-")[0]);
		if (u == null) {
			return false;
		}
		Account account = u.getAccount(accountID.split("-")[1]);
		if (account == null) {
			return false;
		} else {
			account.unlockAccount();
			return this.bankDatabase.putUser(accountID.split("-")[0], u);
		}
	}

	protected boolean changePassword(String username, String newPassword) {
		User u = bankDatabase.getUser(username);
		if (u != null) {
			u.changePassword(newPassword);
			return this.bankDatabase.putUser(username, u);
		} else {
			return false;
		}

	}

	protected boolean updatePhoneNumber(String username, String newPhoneNumber) {
		User u = bankDatabase.getUser(username);
		if (u != null) {
			u.updatePhoneNumber(newPhoneNumber);
			return this.bankDatabase.putUser(username, u);
		} else {
			return false;
		}
	}

	protected boolean depositMoney(String accountID, double depositAmount) {
		User u = bankDatabase.getUser(accountID.split("-")[0]);
		if (u == null) {
			return false;
		}
		Account account = u.getAccount(accountID.split("-")[1]);
		if (account == null) {
			return false;
		} else {
			if (account.deposit(depositAmount)) {
				account.addToLogWithTimestamp("Deposit +$" + depositAmount);
				return this.bankDatabase.putUser(accountID.split("-")[0], u);
			} else {
				return false;
			}

		}
	}

	protected boolean withdrawMoney(String accountID, double withdrawAmount) {
		User u = bankDatabase.getUser(accountID.split("-")[0]);
		if (u == null) {
			return false;
		}
		Account account = u.getAccount(accountID.split("-")[1]);
		if (account == null) {
			return false;
		} else {
			if (account.withdraw(withdrawAmount)) {
				account.addToLogWithTimestamp("Withdraw -$" + withdrawAmount);
				return this.bankDatabase.putUser(accountID.split("-")[0], u);
			} else {
				return false;
			}

		}
	}

	protected boolean transferMoney(String accountFrom, String accountTo, double transferAmount) {
		User u = bankDatabase.getUser(accountFrom.split("-")[0]);
		if (u == null) {
			return false;
		}
		Account accountF = u.getAccount(accountFrom.split("-")[1]);
		Account accountT = u.getAccount(accountTo.split("-")[1]);
		if (accountF == null || accountT == null) {
			return false;
		} else {
			// /Round the values to get a correct comparison
			transferAmount = (double) Math.round(transferAmount * 100) / 100;
			double accFBalance = (double) Math.round(accountF.getBalance() * 100) / 100;
			if (accFBalance >= transferAmount && transferAmount > 0) {
				accountF.withdraw(transferAmount);
				accountF.addToLogWithTimestamp("Transfer -$" + transferAmount);
				accountT.deposit(transferAmount);
				accountT.addToLogWithTimestamp("Transfer +$" + transferAmount);
				return this.bankDatabase.putUser(accountFrom.split("-")[0], u);
			} else {
				return false;
			}

		}
	}

	protected Database getDatabase() {
		return bankDatabase;
	}

	protected User getLoginUser() {
		return this.loginUser;
	}

	protected boolean hasPermission() {
		return hasPermission;
	}

	protected boolean hasLogin() {
		return hasLogin;
	}

	protected boolean logout() {
		hasLogin = false;
		loginUser = null;
		hasPermission = false;
		return true;
	}

	protected boolean approveLoan(String username) {
		User u = bankDatabase.getUser(username);
		if (u == null) {
			return false;
		}
		Loan loan = u.getLoan();
		loan.approveLoan();
		if(loan.getStatus())
		{
			System.out.println("Loan amount of: " + loan.getLoanAmount() + " for: " + username + " is already approved!");
		}
		else
		{
			System.out.println("Loan amount of: " + loan.getLoanAmount() + " for: " + username + " has been approved!");
		}	
		if (loan.getStatus())
			return this.bankDatabase.putUser(username, u);
		else
			return false;

	}

	protected boolean requestLoan(String username, double loanAmount) {
		User u = bankDatabase.getUser(username);
		if (u == null) {
			return false;
		}
		Loan newLoan = new Loan(loanAmount);
		if (u.putLoan(newLoan))
			return this.bankDatabase.putUser(username, u);
		else
			return false;
	}

	protected boolean checkLoanStatus(String username) {
		User u = bankDatabase.getUser(username);
		if (u == null) {
			return false;
		}
		return u.getLoan().getStatus();
	}

	protected boolean closeAccount(String accountID, String accountTransferID) {
		User u = bankDatabase.getUser(accountID.split("-")[0]);
		if (u == null) {
			return false;
		}
		Account account = u.getAccount(accountID.split("-")[1]);
		Account accountTransfer = u.getAccount(accountTransferID.split("-")[1]);
		if (account == null || accountTransfer == null) {
			return false;
		} else {
			if (this.transferMoney(accountID, accountTransferID, account.getBalance())) {
				u.deleteAccount(accountID.split("-")[1]);
				return this.bankDatabase.putUser(accountID.split("-")[0], u);
			} else {
				return false;
			}
		}
	}

	protected boolean fixedDeposit(String accountID, double amount, int months) {
		User u = bankDatabase.getUser(accountID.split("-")[0]);
		if (u == null) {
			return false;
		}
		Account account = u.getAccount(accountID.split("-")[1]);
		if (account == null) {
			return false;
		} else {
			if (account.withdraw(amount)) {
				u.fixedDeposit(amount, months);
				System.out.println("A fixed deposit is successful amount: " + amount + " for: " + months + " months");
				return this.bankDatabase.putUser(accountID.split("-")[0], u);
			} else
				return false;
			
		}
	}

	protected List<String> searchTransactionLog(String accountID, String date) {
		User u = bankDatabase.getUser(accountID.split("-")[0]);
		if (u == null) {
			return null;
		}
		Account account = u.getAccount(accountID.split("-")[1]);
		if (account == null) {
			return null;
		} else {
			return account.searchLogs(date);
		}
	}
}
