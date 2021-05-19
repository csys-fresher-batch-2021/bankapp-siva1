package in.siva.service;

import in.siva.dao.UserManagementDAO;
import in.siva.exception.ValidException;
import in.siva.validator.UserValidation;

public class TransactionManagement {

	private TransactionManagement() {
		// Default Constructor
	}

	/**
	 * Deposit an Amount for User account
	 * 
	 * @param name   // name of user
	 * @param amount // amount to deposit
	 */

	public static double depositAmount(String email, float amount) {
		double balance = 0;
		if (UserValidation.emailValidation(email) && UserValidation.isValidAmount(amount)) {
			balance = UserManagementDAO.deposit(email, amount);
		}
		
		else
		{
			throw new ValidException("Enter a valid amount");
		}return balance;
	}

	/**
	 * Withdraw money from account
	 * 
	 * @param name   // name of user
	 * @param amount // amount withdraw by user
	 * @return
	 */

	public static double withdrawAmount(String email, float amount) {
		double balance = 0;
		if (UserValidation.emailValidation(email) && UserValidation.isValidAmount(amount)) {
			balance = UserManagementDAO.withdraw(email, amount);
		}
		else {
			throw new ValidException("Enter a valid amount");
		}
		return balance;
	}
}
