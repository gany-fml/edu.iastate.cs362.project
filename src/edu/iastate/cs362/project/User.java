package edu.iastate.cs362.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private boolean hasPermission;
	private List<Account> account;
	private String phone;
	private String password;
	private String username;
	private String name;
//	private String userID;
	
	public User(String name1, String phone1, String username1, String password1, boolean hasPermission1)
	{
		username = username1;	
		phone = phone1;
		password = password1;
		name = name1;
		account = new ArrayList<Account>();
		hasPermission = hasPermission1;
	}
	
	public int getNumAccount()
	{
		return account.size();
	}
	
//	public void writeUserID(String userID)
//	{
//		this.userID = userID;
//	}
//	
//	public String getUserID()
//	{
//		return userID;
//	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean getPermission()
	{
		return hasPermission;
	}
	
	public void addAccount()
	{
		account.add(new Account(Integer.toString(account.size())+1));
	}
	
	public boolean comparePassword(String password1)
	{
		return password.equals(password);
	}
	
	public String getPhoneNumber()
	{
		return phone;
	}
	
	/**
	 * Get the specific account
	 * @param id: only the second part of account id
	 * @return the specific account
	 */
	public Account getAccount(String id)
	{
		return account.get(Integer.parseInt(id)-1);
	}
	
	public double getTotalBalance()
	{
		double ret = 0;
		for(Account a : account)	ret += a.getBalance();
		return ret;
	}
}
