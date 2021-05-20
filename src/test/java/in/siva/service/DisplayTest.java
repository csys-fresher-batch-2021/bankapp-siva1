package in.siva.service;

import static org.junit.Assert.*;


import org.junit.Test;

import in.siva.exception.ValidException;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DisplayTest {

	/**
	 * To display All User Details
	 */

	@Test
	public void displayUserDetails() {
		String email = "vishvajith257@gmail.com";
		UserManagement.getAllUser(email);
		int size = UserManagement.getAllUser(email).size();

		assertEquals(1,size);

	}

	@Test
	public void isDisplayFailed() {
		String email = "Siva@mail";

		try {
			UserManagement.getAllUser(email);
			fail();
		} catch (ValidException e) {
			// TODO Auto-generated catch block
			assertEquals("Enter Your Correct Account Name", e.getMessage());
		}

	}

}
