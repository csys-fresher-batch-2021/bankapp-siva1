package in.siva.service;

import java.sql.SQLException;

import in.siva.dao.TransactionDAO;
import in.siva.dao.UpdateDAO;

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

		// condition to validate email and amount
		if (UserValidation.emailValidation(email) && UserValidation.isValidAmount(amount)) {
			try {
				balance = TransactionDAO.deposit(email, amount);
				UpdateDAO.updateBalance(email, amount,"DEPOSIT");
			} catch (ClassNotFoundException | SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			throw new ValidException("Enter a valid amount");
		}
		return balance;
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
		// condition to validate email and amount
		if (UserValidation.emailValidation(email) && UserValidation.isValidAmount(amount)) {
			try {

				balance = TransactionDAO.withdraw(email, amount);
				UpdateDAO.updateBalance(email, amount,"WITHDRAW");
			} catch (ClassNotFoundException | SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new ValidException("Enter a valid amount");
		}
		return balance;
	}

}
