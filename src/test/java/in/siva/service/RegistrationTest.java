package in.siva.service;

import static org.junit.Assert.*;

import org.junit.Before;

import in.siva.model.User;
import org.junit.Test;

public class RegistrationTest {
	static User user = null;

	@Before
	public void setup() {

		user = new User();
		user.setName("siva");
		user.setPassword("Siva@1234");
		user.setMobileNo(9361363167l);
		user.setAccNo(98249824897l);
		user.setEmail("siva123@gmail.com");

	}

	/**
	 * Test case for valid registration
	 */
	@Test
	public void isValidRegistration() {

		boolean valid = UserManagement.registerDetails(user);
		assertTrue(valid);
	}

	/**
	 * Test case for wrong Email Id
	 */
	@Test
	public void isInvalidEmail() {

		user.setEmail("@gmail.com");
		boolean valid = UserManagement.registerDetails(user);
		assertFalse(valid);

	}

	/**
	 * Test case for Invalid User name
	 */
	@Test
	public void isInvalidName() {

		user.setName("ss");
		boolean valid = UserManagement.registerDetails(user);
		assertFalse(valid);

	}

	/**
	 * Test case for Invalid User password
	 */
	@Test
	public void isInvalidPassword() {

		user.setPassword("sivw");
		boolean valid = UserManagement.registerDetails(user);
		assertFalse(valid);

	}

	/**
	 * Test case for invalid account number
	 */
	@Test
	public void isInvalidAccountNumber() {

		user.setAccNo(84566l);
		boolean valid = UserManagement.registerDetails(user);
		assertFalse(valid);

	}

	/**
	 * Test case for invalid mobile number
	 */
	@Test
	public void isInvalidMobileNumber() {

		user.setMobileNo(9361363l);
		boolean valid = UserManagement.registerDetails(user);
		assertFalse(valid);

	}
}
