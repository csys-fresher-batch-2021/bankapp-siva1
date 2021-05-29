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
	private static TransactionDAO transactionDAO = new TransactionDAO();
	private static UpdateDAO updateDAO = new UpdateDAO();

	public static double depositAmount(int accNo, float amount) {
		double balance = 0;

		// condition to validate email and amount
		if (UserValidation.isValidAmount(amount)) {
			try {
				balance = transactionDAO.deposit(accNo, amount);
				updateDAO.updateBalance(accNo, amount, "DEPOSIT");
			} catch (ClassNotFoundException | SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			throw new ValidException("Enter a valid details");
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

	public static double withdrawAmount(int accNo, float amount) {
		double balance = 0;
		// condition to validate email and amount
		if (UserValidation.isValidAmount(amount)) {
			try {

				balance = transactionDAO.withdraw(accNo, amount);
				updateDAO.updateBalance(accNo, amount, "WITHDRAW");
			} catch (ClassNotFoundException | SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new ValidException("Enter a valid details");
		}
		return balance;
	}

	public static void transferAmount(int senderAccNo, int receiverAccNo, float amount) {

		if (UserValidation.isValidAmount(amount)) {
			try {
				transactionDAO.fundTransfer(senderAccNo, receiverAccNo, amount);
				updateDAO.updateBalance(receiverAccNo, amount, "DEPOSIT");

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
		} else {

			throw new ValidException("Enter valid details");
		}
	}

}
