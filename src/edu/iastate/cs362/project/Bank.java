package edu.iastate.cs362.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Bank {
	
	private Database bankDatabase;
	private boolean hasPermission;
	private boolean hasLogin;
	private User loginUser;
	
	public Bank(File file1)
	{
		File file = file1;
		hasLogin = false;
		loginUser = null;
		hasPermission = false;
		if(!file.isFile())
		{
			System.out.println("Bank data file not exist, creating new one...");
		}
		
		try {
			file.createNewFile();
			FileInputStream iFile = new FileInputStream(file);
			ObjectInputStream oFile = new ObjectInputStream(iFile);
			bankDatabase = (Database)oFile.readObject();
			oFile.close();
			iFile.close();
		} catch (IOException e) {
			System.out.println("System cannot create data file, system exit");
		} catch (ClassNotFoundException e) {
			System.out.println("File type not correct, system exit");
		}
	}
		
	public boolean hasPermission()
	{
		return hasPermission;
	}
	
	public boolean hasLogin()
	{
		return hasLogin();
	}
	
	public void logout()
	{
		hasLogin = false;
		loginUser = null;
		hasPermission = false;
	}
	
	public void userLogin(String userid, String password)
	{
		User loginUser = bankDatabase.getUser(userid);
		if(loginUser.comparePassword(password))
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
	
	public String getLoginUserID()
	{
		return loginUser.getUserID();
	}
	
	public boolean createUser(String name, String phone, String username, String password, boolean hasPermission)
	{
		if(hasLogin == false)
		{
			System.out.println("Has Not Login, Please Login first");
			return false;
		}
		
		
		else
		{
			User user2Add = new User(name, phone, username, password, hasPermission);
			String userID = user2Add.hashCode() + "";
			user2Add.writeUserID(userID);
			bankDatabase.putUser(userID, user2Add);
			System.out.println("User has been created, userID is" + userID);
			return true;
		}
	}
	
	public boolean createAccount(String userID)
	{
		if(hasLogin == false)
		{
			System.out.println("Has Not Login, Please Login first");
			return false;
		}
				
		else
		{
			User user2CreateAccount = bankDatabase.getUser(userID);
			user2CreateAccount.addAccount();
			System.out.println("Account has been created");
			return true;
		}
	}
	
	public String getPhoneNumber(String userID)
	{
		if(hasLogin == false)
		{
			System.out.println("Has Not Login, Please Login first");
			return null;
		}
				
		else
		{
			User user = bankDatabase.getUser(userID);
			return user.getPhoneNumber();
		}
	}
	
	public boolean lockAccount(String accountID)
	{
		if(hasLogin == false)
		{
			System.out.println("Has Not Login, Please Login first");
			return false;
		}
		
		else
		{
			String userID = accountID.split("-")[0];
			User user = bankDatabase.getUser(userID);
			Account account = user.getAccount(accountID.split("-")[1]);
			if(account == null)
				return false;
			else
			{
				account.lockAccount();
				System.out.println("Account " + accountID + " has been locked");
				return true;
			}
		}
	}
	
	public boolean unlockAccount(String accountID)
	{
		if(hasLogin == false)
		{
			System.out.println("Has Not Login, Please Login first");
			return false;
		}
				
		else
		{
			String userID = accountID.split("-")[0];
			User user = bankDatabase.getUser(userID);
			Account account = user.getAccount(accountID.split("-")[1]);
			if(account == null)
				return false;
			else
			{
				account.unlockAccount();
				System.out.println("Account " + accountID + " has been unlocked");
				return true;
			}
		}
	}
	
	public double getBalance(String accountID)
	{
		if(hasLogin == false)
		{
			System.out.println("Has Not Login, Please Login first");
			return 0;
		}
				
		else
		{
			String userID = accountID.split("-")[0];
			User user = bankDatabase.getUser(userID);
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
	}

	public double getTotalBalance(String userID)
	{
		if(hasLogin == false)
		{
			System.out.println("Has Not Login, Please Login first");
			return 0;
		}
				
		else
		{
			User user = bankDatabase.getUser(userID);
			return user.getTotalBalance();
		}
	}
}
