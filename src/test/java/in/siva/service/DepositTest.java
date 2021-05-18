package in.siva.service;

import static org.junit.Assert.*;

import org.junit.Test;





public class DepositTest {

	/**
	 * Test Case for Successful Deposit
	 */
	@Test
	public void isDepositAmountSuccess() {

		String name = "Siva";
		float amount = 1000;
		double balance = TransactionManagement.depositAmount(name, amount);
		assertEquals(51000, balance,0.01);
	}
	
	/**
	 * Test Case for Failed Deposit
	 */
	@Test
	public void isDepositFailed() {

		String name = "ramesh";
		float amount = -1000;
		try {
			TransactionManagement.depositAmount(name, amount);
			fail();
		} catch (RuntimeException e) {

			assertEquals("Enter a valid amount", e.getMessage());
		}
	}

	
	

}
