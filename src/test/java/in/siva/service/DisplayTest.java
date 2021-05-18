package in.siva.service;

import static org.junit.Assert.*;


import org.junit.Test;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DisplayTest {

	/**
	 * To display All User Details
	 */

	@Test
	public void displayUserDetails() {
		String name = "Ramesh";
		UserManagement.getAllUser(name);
		int size = UserManagement.getAllUser(name).size();

		assertEquals(size, 3);

	}

	@Test
	public void isDisplayFailed() {
		String name = "yu";

		try {
			UserManagement.getAllUser(name);
			fail();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			assertEquals("Enter Your Correct Account Name", e.getMessage());
		}

	}

}
