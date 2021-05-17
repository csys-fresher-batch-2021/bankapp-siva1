
package in.siva.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginTest {

	@Test
	/**
	 * Test case for correct username and password
	 */
	public void correctUsernameAndPassword() {
		String userName = "Ramesh";
		String password = "Gkram@123";
		boolean valid = UserManagement.loginValidation(userName, password);
		assertTrue(valid);

	}

	@Test
	/**
	 * Test case for correct username and wrong password
	 */
	public void correctUsernameWrongPassword() {
		String userName = "Siva";
		String password = "1234";
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);
	}

	@Test
	/**
	 * Test case for correct password and wrong username
	 * 
	 */

	public void correctPasswordWrongUsername() {
		String userName = "Arun";
		String password = "Siva@123";
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);
	}

	@Test
	/**
	 * Test case for wrong username and password
	 */
	public void wrongUsernameAndPassword() {
		String userName = "ramesh";
		String password = "gkram@123";
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);

	}

	@Test
	/**
	 * Test case for null password and username
	 */
	public void isUsernameAndPasswordNull() {
		String userName = null;
		String password = null;
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);
	}

	@Test
	/**
	 * Test case for null username and correct password
	 */
	public void isUsernameNull() {
		String userName = null;
		String password = "Siva@123";
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);
	}

	@Test
	/**
	 * Test case for correct username and wrong password
	 */
	public void PasswordNull() {
		String userName = "Siva";
		String password = null;
		boolean valid = UserManagement.loginValidation(userName, password);
		assertFalse(valid);
	}
}
