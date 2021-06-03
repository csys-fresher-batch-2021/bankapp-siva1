package in.siva.service;

import java.sql.SQLException;

import in.siva.dao.TransactionDAO;
import in.siva.dao.UpdateDAO;

import in.siva.exception.ValidException;

import in.siva.validator.AmountValidator;
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

			if (!UserValidation.isValidAmount(amount)) {
				throw new ValidException("Invalid Amount");
			}
			if (!transactionDAO.exists(accNo)) {
				throw new ValidException("Invalid  Account Number");
			}

				balance = transactionDAO.deposit(accNo, amount);
				updateDAO.updateBalance(accNo, amount, "DEPOSIT");

			
			
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

			if (!UserValidation.isValidAmount(amount)) {
				throw new ValidException("Invalid Amount");
			}
			if (AmountValidator.isSufficientAmount(accNo, amount)) {
				throw new ValidException("InSufficient Balance in Your Account");
			}
			if (!transactionDAO.exists(accNo)) {
				throw new ValidException("Invalid Account Number");
			}
			balance = transactionDAO.withdraw(accNo, amount);
			updateDAO.updateBalance(accNo, amount, "WITHDRAW");

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return balance;
	}

	public static void transferAmount(int senderAccNo, int receiverAccNo, float amount) {

		try {
			if (!UserValidation.isValidAmount(amount)) {
				throw new ValidException("Invalid Amount");
			}
			if (AmountValidator.isSufficientAmount(senderAccNo, amount)) {
				throw new ValidException("InSufficient Balance in Your Account");
			}
			if (!transactionDAO.exists(receiverAccNo)) {
				throw new ValidException("Invalid Receiver Account Number");
			}
			if(senderAccNo == receiverAccNo) {
				throw new ValidException("Enter Receiver Account Number");
			}
			if (!transactionDAO.exists(senderAccNo)) {
				throw new ValidException("Invalid Account Number");
			}
			transactionDAO.fundTransfer(senderAccNo, receiverAccNo, amount);
			updateDAO.updateBalance(receiverAccNo, amount, "DEPOSIT");

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

}
