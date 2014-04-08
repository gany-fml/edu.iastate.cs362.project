package edu.iastate.cs362.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
				FileOutputStream outStream = new FileOutputStream(file);
				ObjectOutputStream outObject = new ObjectOutputStream(outStream);
				Database init = new Database();
				User root = new User("root", "123", "root", "root", true);
				init.putUser("root", root);
				outObject.writeObject(init);
				outObject.close();
				outStream.close();
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
		User loginUser = bankDatabase.getUser(username);
		if (loginUser == null) {
			System.out.println("User not exist");
			return false;
		} else if (loginUser.comparePassword(password)) {
			System.out.println("Welcome back " + loginUser.getName() + " !");
			this.loginUser = loginUser;
			hasLogin = true;
			hasPermission = loginUser.getPermission();
			return true;
		} else {
			System.out.println("username or password not correct");
			return false;
		}
	}

	protected boolean createUser(String name, String phone, String username, String password,
			boolean hasPermission) {
		User u = new User(name, phone, username, password, hasPermission);
		System.out.println("User has been created");
		return bankDatabase.putUser(username, u);
	}

	protected boolean createAccount(String username) {
		User u = bankDatabase.getUser(username);
		if (u != null) {
			u.addAccount();
			System.out.println("Account has been created");
			return this.bankDatabase.putUser(username, u);
		} else {
			System.out.println("User not exist");
			return false;
		}
	}

	protected Double getBalance(String accountID) {
		String username = accountID.split("-")[0];
		User u = bankDatabase.getUser(username);
		if (u == null) {
			System.out.println("User not exist");
			return null;
		}
		Account account = u.getAccount((accountID.split("-")[1]));
		if (account == null) {
			System.out.println("Account not exist");
			return null;
		} else {
			return account.getBalance();
		}
	}

	protected Double getTotalBalance(String username) {
		User u = bankDatabase.getUser(username);
		if (u == null) {
			System.out.println("User not exist");
			return null;
		} else
			return u.getTotalBalance();
	}

	protected String getPhoneNumber(String username) {
		User u = bankDatabase.getUser(username);
		if (u == null) {
			System.out.println("User not exist");
			return null;
		} else
			return u.getPhoneNumber();
	}

	protected boolean lockAccount(String accountID) {
		if (accountID.contains("-")) {
			System.out.println("Wrong account format");
			return false;
		}
		User u = bankDatabase.getUser(accountID.split("-")[0]);
		if (u == null) {
			System.out.println("User not exist");
			return false;
		}
		Account account = u.getAccount(accountID.split("-")[1]);
		if (account == null) {
			System.out.println("Account not exist");
			return false;
		} else {
			account.lockAccount();
			System.out.println("Account " + accountID + " has been locked");
			return this.bankDatabase.putUser(accountID.split("-")[0], u);
		}
	}

	protected boolean unlockAccount(String accountID) {
		User u = bankDatabase.getUser(accountID.split("-")[0]);
		if (u == null) {
			System.out.println("User not exist");
			return false;
		}
		Account account = u.getAccount(accountID.split("-")[1]);
		if (account == null) {
			System.out.println("Account not exist");
			return false;
		} else {
			account.unlockAccount();
			System.out.println("Account " + accountID + " has been unlocked");
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
			if (accountF.getBalance() >= transferAmount && transferAmount > 0) {
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
	
	protected Statement viewStatement(String username){
		User u = bankDatabase.getUser(username);
		if (u == null) {
			return null;
		}
		Statement s = new Statement(u);
		return s.generateStatement();
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
}
