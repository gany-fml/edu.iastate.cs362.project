package edu.iastate.cs362.project;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class BankController implements Serializable{

	private Bank bank;
	private static final long serialVersionUID = 1L;
	
	public BankController(File bankData) throws IOException
	{
		bank = new Bank(bankData);
	}
	
	public void translate()
	{
		boolean exit = false;
		Scanner inputScanner = new Scanner(System.in);
		while(!exit)
		{
			String line = "";
			while(!inputScanner.hasNextLine());
			line = inputScanner.nextLine();
			
			String command;
			if(line.contains(" "))
			{
				String input[] = line.split(" ");
				command = input[0].toLowerCase();
				if(command.equals("create"))
				{
					if(input[1].toLowerCase().equals("user"))
						createUser(inputScanner);
					else if(input[1].toLowerCase().equals("account"))
						createAccount(inputScanner);
					else	System.out.println("invalid command");
				}
				
				else if(command.equals("get"))
				{
					if(input[1].toLowerCase().equals("balance"))
						getbalance(inputScanner);
					else if(input[1].toLowerCase().equals("phoneNumber"))
						getPhoneNumber(inputScanner);
					else if(input[1].toLowerCase().equals("totalBalance"))
						getTotalBalance(inputScanner);
					else	System.out.println("invalid command");
				}
				
				else if(command.equals("lock") && input[1].toLowerCase().equals("account"))
					lockAccount(inputScanner);
				
				else if(command.equals("unlock") && input[1].toLowerCase().equals("account"))
					unlockAccount(inputScanner);
				else System.out.println("invalid command");
			}
			else
			{
				command = line;
				if(command.equals("login"))
				{
					login(inputScanner);
				}
				
				else if(command.equals("logout"))
				{
					logout();
				}
				
				else if(command.equals("exit"))
				{
					exit = true;
				}
				
				else System.out.println("Invalid command");
			}
		}
		inputScanner.close();
	}
	
	public void getPhoneNumber(Scanner inputScanner)
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		else if(!bank.hasPermission())
			bank.getPhoneNumber(bank.getLoginUserName());
		else
		{
			System.out.println("Please enter the username");
			String username = inputScanner.next();
			bank.getPhoneNumber(username);
		}
	}
	
	public void getTotalBalance(Scanner inputScanner)
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		System.out.println("Please enter the username");
		String username = inputScanner.next();
		if(bank.hasPermission())
		{
			bank.getTotalBalance(username);
		}
		else
		{
			if(username.equals(bank.getLoginUserName()))
				bank.getTotalBalance(username);
			else
				System.out.println("Request Deny");
		}
	}

	public void getbalance(Scanner inputScanner)
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		System.out.println("Please enter the accountID");
		String accountID = inputScanner.next();
		if(bank.hasPermission())
		{
			bank.getBalance(accountID);
		}
		else
		{
			if(accountID.split("-")[0].equals(bank.getLoginUserName()))
				bank.getBalance(accountID);
			else
				System.out.println("Request Deny");
		}
	}

	
	public void lockAccount(Scanner inputScanner)
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		if(bank.hasPermission())
		{
			System.out.println("Please enter the accountID");
			String accountID = inputScanner.next();
			bank.lockAccount(accountID);
		}
		else
		{
			System.out.println("Request Deny");
		}
	}
	
	public void unlockAccount(Scanner inputScanner)
	{
		if(!bank.hasLogin())
		{
			System.out.println("Login first!");
			return;
		}
		if(bank.hasPermission())
		{
			System.out.println("Please enter the accountID");
			String accountID = inputScanner.next();
			bank.unlockAccount(accountID);
		}
		
		else
		{
			System.out.println("Request Deny");
		}
	}

	
	public void createAccount(Scanner inputScanner)
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
		System.out.println("Please enter your username");
		String username = inputScanner.nextLine();
		bank.createAccount(username);
	}
	
	public void createUser(Scanner inputScanner)
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
		System.out.println("User created");
	}
	
	public void login(Scanner inputScanner)
	{
		if(bank.hasLogin())
		{
			System.out.println("Has already Login, Log out first");
			return;
		}
		System.out.println("Please enter your user name");
		String userName = inputScanner.nextLine();
		System.out.println("Please enter your password");
		String pass = inputScanner.nextLine();
		bank.userLogin(userName, pass);
	}
	
	public void logout()
	{
		bank.logout();
	}
	
}
