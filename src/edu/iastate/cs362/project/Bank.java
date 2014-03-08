package edu.iastate.cs362.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Bank implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Database bankDatabase;
	private boolean hasPermission;
	private boolean hasLogin;
	private User loginUser;
	
	public Bank(File file1) throws IOException
	{
		File file = file1;
		hasLogin = false;
		loginUser = null;
		hasPermission = false;
		try {
			if(!file.isFile())
			{
				System.out.println("Bank data file not exist, creating new one...");
				file.createNewFile();
				FileOutputStream outStream = new FileOutputStream(file);
				ObjectOutputStream outObject= new ObjectOutputStream(outStream);
				Database init = new Database();
				User root = new User("root", "123", "root", "root", true);
				init.putUser("root", root);
				outObject.writeObject(init);
				outObject.close();
				outStream.close();
			}
			FileInputStream iFile = new FileInputStream(file);
			ObjectInputStream oFile = new ObjectInputStream(iFile);
			bankDatabase = (Database)oFile.readObject();
			oFile.close();
			iFile.close();
		} catch (ClassNotFoundException e) {
			System.out.println("File type not correct, system exit");
		} catch (IOException e) {
			System.out.println("File can not be read");
		}
	}
		
	public boolean hasPermission()
	{
		return hasPermission;
	}
	
	public boolean hasLogin()
	{
		return hasLogin;
	}
	
	public void logout()
	{
		hasLogin = false;
		loginUser = null;
		hasPermission = false;
	}
	
	public void userLogin(String userName, String password)
	{
		User loginUser = bankDatabase.getUser(userName);
		if(loginUser == null)
		{
			System.out.println("User not exist");
			return;
		}
		else if(loginUser.comparePassword(password))
		{
			System.out.println("System login successfully.");
			System.out.println("Welcome back " + loginUser.getName() + " !");
			hasLogin = true;
			hasPermission = loginUser.getPermission();
			this.loginUser = loginUser;
			return;
		}
		
		else
		{
			System.out.println("Username or password not correct");
			return;
		}
	}
	
	public String getLoginUserName()
	{
		return loginUser.getUsername();
	}
	
	public void createUser(String name, String phone, String username, String password, boolean hasPermission)
	{
		User user2Add = new User(name, phone, username, password, hasPermission);
//		user2Add.writeUserID(userID);
		bankDatabase.putUser(username, user2Add);
		System.out.println("User has been created");
	}
	
	public boolean createAccount(String userName)
	{
		User user2CreateAccount = bankDatabase.getUser(userName);
		if(user2CreateAccount == null)
		{
			System.out.println("User not exist");
			return false;
		}
		else
		{
			user2CreateAccount.addAccount();
			System.out.println("Account has been created, account number is " + userName + "-" + user2CreateAccount.getNumAccount());
			return true;
		}
	}
	
	public String getPhoneNumber(String userID)
	{
		User user = bankDatabase.getUser(userID);
		if(user == null)
		{
			System.out.println("User not exist");
			return null;
		}
		else	return user.getPhoneNumber();
	}
	
	public boolean lockAccount(String accountID)
	{
		String userID = accountID.split("-")[0];
		User user = bankDatabase.getUser(userID);
		if(user == null)
		{
			System.out.println("User not exist");
			return false;
		}
		Account account = user.getAccount(accountID.split("-")[1]);
		if(account == null)
		{
			System.out.println("Account not exist");
			return false;
		}
		else
		{
			account.lockAccount();
			System.out.println("Account " + accountID + " has been locked");
			return true;
		}
	}
	
	public boolean unlockAccount(String accountID)
	{
		String userID = accountID.split("-")[0];
		User user = bankDatabase.getUser(userID);
		if(user == null)
		{
			System.out.println("User not exist");
			return false;
		}
		Account account = user.getAccount(accountID.split("-")[1]);
		if(account == null)
		{
			System.out.println("Account not exist");
			return false;
		}
		else
		{
			account.unlockAccount();
			System.out.println("Account " + accountID + " has been unlocked");
			return true;
		}
	}
	
	public double getBalance(String accountID)
	{
		String userID = accountID.split("-")[0];
		User user = bankDatabase.getUser(userID);
		if(user == null)
		{
			System.out.println("User not exist");
			return 0;
		}
		Account account = user.getAccount(accountID.split("-")[1]);
		if(account == null)
		{
			System.out.println("Account not exist");
			return 0;
		}
		else
		{
			return account.getBalance();
		}
	}

	public double getTotalBalance(String userID)
	{
		User user = bankDatabase.getUser(userID);
		if(user == null)
		{
			System.out.println("User not exist");
			return 0;
		}
		else return user.getTotalBalance();
	}
}
