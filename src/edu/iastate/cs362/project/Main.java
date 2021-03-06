package edu.iastate.cs362.project;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Main implements Serializable {

	private static final long serialVersionUID = 1L;
	public static BankController controller;

	public static void main(String arg[]) throws IOException {
		File file = new File("Database.bankdata");
		controller = new BankController(file);
		MainFrame.startApp();	
	}
}
