package in.siva.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class loginTest {

	@Test
	public void correctUsernameAndPassword() {
		String userName = "Ramesh";
		String password = "Gkram@123";
		boolean valid = UserManagement.loginValidation(userName, password);
		assertTrue(valid);

	}

	@Test
	public void correctUsernameWrongPassword() {
		String userName = "Siva";
		String password = "1234";
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);
	}

	@Test
	public void correctPasswordWrongUsername() {
		String userName = "Arun";
		String password = "Siva@123";
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);
	}

	@Test
	public void wrongUsernameAndPassword() {
		String userName = "ramesh";
		String password = "gkram@123";
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);

	}

	@Test
	public void isUsernameAndPasswordNull() {
		String userName = null;
		String password = null;
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);
	}

	@Test
	public void isUsernameNull() {
		String userName = null;
		String password = "Siva@123";
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);
	}

	@Test
	public void PasswordNull() {
		String userName = "Siva";
		String password = null;
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);
	}
}
