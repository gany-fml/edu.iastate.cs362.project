package edu.iastate.cs362.project;

import java.io.Serializable;
import java.util.HashMap;

public class Database implements Serializable {
	private static final long serialVersionUID = 1L;
	private HashMap<String, User> user;

	public Database() {
		user = new HashMap<String, User>();
	}

	public boolean putUser(String username, User usr) {
		user.put(username, usr);
		return true;
	}

	public User getUser(String username) {
		return user.get(username);
	}
}
