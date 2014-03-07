package edu.iastate.cs362.project;

import java.io.File;
import java.util.Scanner;

public class BankController {

	private Bank bank;
	
	public BankController(File bankData)
	{
		bank = new Bank(bankData);
	}
	
	public void translate()
	{
		Scanner inputScanner = new Scanner(System.in);
		String[] input = inputScanner.nextLine().split(" ");
		String command = input[0].toLowerCase();
		
		if(command.equals("login"))
		{
			login();
		}
		
		else if(command.equals("logout"))
		{
			logout();
		}
		
		else if(command.equals("create"))
		{
			if(input[1].toLowerCase().equals("user"))
				createUser();
			else if(input[1].toLowerCase().equals("account"))
				createAccount();
			else	System.out.println("invalid command");
		}
		
		else if(command.equals("get"))
		{
			if(input[1].toLowerCase().equals("balance"))
				getbalance();
			else if(input[1].toLowerCase().equals("phoneNumber"))
				getPhoneNumber();
			else if(input[1].toLowerCase().equals("totalBalance"))
				getTotalBalance();
			else	System.out.println("invalid command");
		}
		
		else if(command.equals("lock") && input[1].toLowerCase().equals("account"))
			lockAccount();
		
		else if(command.equals("unlock") && input[1].toLowerCase().equals("account"))
			unlockAccount();
		
		else System.out.println("Invalid command");
		
		inputScanner.close();
		
	}
	
	public void getPhoneNumber()
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		else if(!bank.hasPermission())
			bank.getPhoneNumber(bank.getLoginUserID());
		else
		{
			System.out.println("Please enter the userID");
			Scanner input = new Scanner(System.in);
			String userID = input.next();
			bank.getPhoneNumber(userID);
			input.close();
		}
	}
	
	public void getTotalBalance()
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the userID");
		String userID = input.next();
		if(bank.hasPermission())
		{
			bank.getTotalBalance(userID);
		}
		else
		{
			if(userID.equals(bank.getLoginUserID()))
				bank.getTotalBalance(userID);
			else
				System.out.println("Request Deny");
		}
		input.close();
	}

	public void getbalance()
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the accountID");
		String accountID = input.next();
		if(bank.hasPermission())
		{
			bank.getBalance(accountID);
		}
		else
		{
			if(accountID.split("-")[0].equals(bank.getLoginUserID()))
				bank.getBalance(accountID);
			else
				System.out.println("Request Deny");
		}
		input.close();
	}

	
	public void lockAccount()
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		if(bank.hasPermission())
		{
			System.out.println("Please enter the accountID");
			Scanner input = new Scanner(System.in);
			String accountID = input.next();
			bank.lockAccount(accountID);
			input.close();
		}
		else
		{
			System.out.println("Request Deny");
		}
	}
	
	public void unlockAccount()
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		if(bank.hasPermission())
		{
			System.out.println("Please enter the accountID");
			Scanner input = new Scanner(System.in);
			String accountID = input.next();
			bank.unlockAccount(accountID);
			input.close();
		}
		
		else
		{
			System.out.println("Request Deny");
		}
	}

	
	public void createAccount()
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		if(!bank.hasPermission())
		{
			System.out.println("Request Deny");
			return;
		}
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Please enter your userID");
		String userID = inputScanner.nextLine();
		bank.createAccount(userID);
		inputScanner.close();
	}
	
	public void createUser()
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		if(!bank.hasPermission())
		{
			System.out.println("Request Deny");
			return;
		}
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Please enter your name");
		String name = inputScanner.nextLine();
		System.out.println("Please enter your phone Number");
		String phoneNumber = inputScanner.nextLine();
		System.out.println("Please enter your username");
		String username = inputScanner.nextLine();
		System.out.println("Please enter your password");
		String password = inputScanner.nextLine();
		System.out.println("Is this an employee account?[yes/non]");
		boolean hasPermission = false, validAnswer = false;
		String permission = inputScanner.next();
		while(!validAnswer)
		{
			if(permission.equals("yes"))
			{
				hasPermission = true;
				validAnswer = true;
			}
			else if(permission.equals("no"))
			{
				hasPermission = false;
				validAnswer = true;
			}
			else
			{
				System.out.println("Please enter yes or no");
				permission = inputScanner.next();
			}
		}
		
		bank.createUser(name, phoneNumber, username, password, hasPermission);
		inputScanner.close();
	}
	
	public void login()
	{
		if(bank.hasLogin())
		{
			System.out.println("Has already Login, Log out first");
			return;
		}
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Please enter your user name");
		String userName = inputScanner.nextLine();
		System.out.println("Please enter your password");
		String pass = inputScanner.nextLine();
		bank.userLogin(userName, pass);
		inputScanner.close();
	}
	
	public void logout()
	{
		bank.logout();
	}
	
}
