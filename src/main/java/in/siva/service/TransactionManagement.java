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

		// condition to validate accno and amount
		try {

			if (UserValidation.isValidAmount(amount) && transactionDAO.exists(accNo)) {

				balance = transactionDAO.deposit(accNo, amount);
				updateDAO.updateBalance(accNo, amount, "DEPOSIT");

			}

			else {
				throw new ValidException("Enter a valid details");
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
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

		try {
			if (UserValidation.isValidAmount(amount) && transactionDAO.exists(accNo)) {

				balance = transactionDAO.withdraw(accNo, amount);
				updateDAO.updateBalance(accNo, amount, "WITHDRAW");

			} else {
				throw new ValidException("Enter  valid details");
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return balance;
	}

	public static void transferAmount(int senderAccNo, int receiverAccNo, float amount) {

		try {
			if (UserValidation.isValidAmount(amount) && transactionDAO.exists(senderAccNo)
					&& transactionDAO.exists(receiverAccNo)) {

				transactionDAO.fundTransfer(senderAccNo, receiverAccNo, amount);
				updateDAO.updateBalance(receiverAccNo, amount, "DEPOSIT");
			} else {

				throw new ValidException("Enter valid details");
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

}
