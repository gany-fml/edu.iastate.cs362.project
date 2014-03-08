package edu.iastate.cs362.project;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class BankTests {

	@Test
	public void test() throws IOException {
		File file = new File("");
		BankController bc1 = new BankController(file);
	}

}
