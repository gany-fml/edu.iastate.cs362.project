package edu.iastate.cs362.project;

import java.io.Serializable;
import java.util.HashMap;

public class Database implements Serializable
{
	private static final long serialVersionUID = 1L;
	private HashMap<String, User> user;
	
	public Database()
	{
		user = new HashMap<String, User>();
	}
	
	public void putUser(String userName, User usr)
	{
		user.put(userName, usr);
	}
	
	public User getUser(String userName)
	{
		return user.get(userName);
	}
}
