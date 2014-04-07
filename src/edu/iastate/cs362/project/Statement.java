package edu.iastate.cs362.project;

public class Statement {

	User user;

	protected Statement(User u) {
		user = u;
	}

	protected Statement generateStatement() {
		for (Account a : user.getAccounts()){
			
		}
		return null;
	}

}
