package in.siva.service;

import in.siva.model.User;

public class TransactionManagement {

	private TransactionManagement() {
		// Default Constructor
	}

	/**
	 * Deposit an Amount for User account
	 * 
	 * @param user   // details of user
	 * @param amount // amount to deposit
	 */

	public static double depositAmount(String name, double amount) {
		double balance = 0;

		if (amount != 0 && amount > 0) {
			for (User deposit : UserManagement.getAllUser(name)) {

				if (deposit.getName().equals(name)) {

					balance = amount + deposit.getBalance();// deposit calculation

					break;
				}

			}
		} else {

			throw new RuntimeException("Enter a valid amount");

		}
		return balance;
	}

}
