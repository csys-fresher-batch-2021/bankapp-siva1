package in.siva.service;

import static org.junit.Assert.*;

import org.junit.Test;

import in.siva.exception.ValidException;





public class DepositTest {

	/**
	 * Test Case for Successful Deposit
	 */
	@Test
	public void isDepositAmountSuccess() {

		String email = "vishvajith257@gmail.com";
		float amount = 1000;
		double balance = TransactionManagement.depositAmount(email, amount);
		assertEquals(51000, balance,0.01);
	}
	
	/**
	 * Test Case for Failed Deposit
	 */
	@Test
	public void isDepositFailed() {

		String email = "gururam12@gmail.com";
		float amount = -1000;
		try {
			TransactionManagement.depositAmount(email, amount);
			fail();
		} catch (ValidException e) {

			assertEquals("Enter a valid amount", e.getMessage());
		}
	}

	
	

}