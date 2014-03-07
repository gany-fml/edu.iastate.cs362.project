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
	
	public void putUser(String id1, User usr)
	{
		user.put(id1, usr);
	}
	
	public User getUser(String id1)
	{
		return user.get(id1);
	}
}
