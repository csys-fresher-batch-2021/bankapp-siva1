package in.siva.service;

import static org.junit.Assert.*;

import org.junit.Test;

import in.siva.exception.ValidException;

public class WithdrawTest {




	/**
	 * Test Case for Successful Deposit
	 */
	@Test
	public void isWithdrawalSuccess() {

		String email = "vishvajith257@gmail.com";
		float amount = 1000;
		double balance = TransactionManagement.withdrawAmount(email, amount);
		assertEquals(49000, balance,0.01);
	}
	
	/**
	 * Test Case for Failed Deposit
	 */
	@Test
	public void isWithdrawalFailed() {

		String email = "gururam12@gmail.com";
		float amount = -1000;
		try {
			TransactionManagement.withdrawAmount(email, amount);
			fail();
		} catch (ValidException e) {

			assertEquals("Enter a valid amount", e.getMessage());
		}
	}

}
