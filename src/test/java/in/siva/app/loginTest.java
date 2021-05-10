package in.siva.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class loginTest {

	@Test
	public void correctUsernameAndPassword() {
		String userName = "Siva";
		String password = "Siva@123";
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
}
