package edu.iastate.cs362.project;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Main implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static void main(String arg[]) throws IOException
	{
		File file = new File("1dad");
		BankController test = new BankController(file);
		
		while(true)
		{
			test.translate();
		}
		
	}

}
