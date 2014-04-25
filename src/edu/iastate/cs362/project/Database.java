package edu.iastate.cs362.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Database implements Serializable {
	private static final long serialVersionUID = 1L;
	private HashMap<String, User> user;
	private transient FileOutputStream outStream;
	private transient ObjectOutputStream outObject;
	private File outputFile;

	protected Database(File file) {
		outputFile = file;
		user = new HashMap<String, User>();
	}

	protected boolean putUser(String username, User usr) {
		user.put(username, usr);
		try
		{
			outStream = new FileOutputStream(outputFile);
			outObject = new ObjectOutputStream(outStream);
			outObject.writeObject(this);
			outObject.close();
			outStream.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	protected User getUser(String username) {
		return user.get(username);
	}
}
