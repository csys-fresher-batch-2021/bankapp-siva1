package in.siva.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginTest {

	@Test
	/**
	 * Test case for correct username and password
	 */
	public void correctEmailAndPassword() {
		String email = "vishvajith257@gmail.com";
		String password = "Siva@123";
		boolean valid = UserManagement.loginValidation(email, password);
		assertTrue(valid);

	}

	@Test
	/**
	 * Test case for correct username and wrong password
	 */
	public void correctEmailWrongPassword() {
		String email = "gururam12@gmail.com";
		String password = "1234";
		boolean valid = UserManagement.loginValidation(email, password);
		assertFalse(valid);
	}

	@Test
	/**
	 * Test case for correct password and wrong username
	 * 
	 */

	public void correctPasswordWrongEmail() {
		String email = "siva@gmail";
		String password = "Siva@123";
		boolean valid = UserManagement.loginValidation(email, password);
		assertFalse(valid);
	}

	@Test
	/**
	 * Test case for wrong username and password
	 */
	public void wrongEmailAndPassword() {
		String email = "arun@email.com";
		String password = "Arun@123";
		boolean valid = UserManagement.loginValidation(email, password);
		assertFalse(valid);

	}

	@Test
	/**
	 * Test case for null password and username
	 */
	public void isEmailAndPasswordNull() {
		String email = null;
		String password = null;
		boolean valid = UserManagement.loginValidation(email, password);
		assertFalse(valid);
	}

	@Test
	/**
	 * Test case for null username and correct password
	 */
	public void isEmailNull() {
		String email = null;
		String password = "Siva@123";
		boolean valid = UserManagement.loginValidation(email, password);
		assertFalse(valid);
	}

	@Test
	/**
	 * Test case for correct username and wrong password
	 */
	public void PasswordNull() {
		String email = "vishvajith257@gmail.com";
		String password = null;
		boolean valid = UserManagement.loginValidation(email, password);
		assertFalse(valid);
	}
}
