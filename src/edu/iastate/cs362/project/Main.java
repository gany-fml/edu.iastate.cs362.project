package edu.iastate.cs362.project;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class Main implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static void main(String arg[]) throws IOException
	{
		File file = new File("1dad");
		BankController test = new BankController(file);
		
		
		String userID =JOptionPane.showInputDialog(
			    "Enter user ID");
		
		String password =JOptionPane.showInputDialog(
			    "Enter password");
		
		
		String operationNumber=JOptionPane.showInputDialog(
				"Logged in as 'staff or user' \n"+
			    "Enter operation number \n"+
			    " 1. Create User\n"+
			    " 2. Create Account\n"+
			    " 3. Manage Accounts");
		int code = Integer.parseInt(operationNumber);
		
		while(true)
		{
			test.translate();
		}
		
	}

}
