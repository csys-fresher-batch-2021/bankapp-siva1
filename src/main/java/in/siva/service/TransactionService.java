package in.siva.service;

import java.sql.SQLException;
import java.util.List;

import in.siva.dao.TransactionDAO;
import in.siva.dao.UpdateDAO;

import in.siva.exception.ValidException;
import in.siva.model.Transaction;
import in.siva.util.AmountValidator;
import in.siva.validator.UserValidation;

public class TransactionService {

	private TransactionService() {
		// Default Constructor
	}
	private static TransactionDAO transactionDAO = new TransactionDAO();
	private static UpdateDAO updateDAO = new UpdateDAO();

	/**
	 * Deposit an Amount for User account
	 * 
	 * @param name   // name of user
	 * @param amount // amount to deposit
	 */
	
	public static double depositAmount(int accNo, Transaction transaction) {
		double balance = 0;

		// condition to validate accno and amount
		try {

			if (!UserValidation.isValidAmount(transaction.getAmount())) {
				throw new ValidException("Invalid Amount");
			}
			if (!transactionDAO.exists(accNo)) {
				throw new ValidException("Invalid  Account Number!");
			}

			balance = transactionDAO.deposit(accNo, transaction.getAmount());
			updateDAO.updateBalance(accNo, transaction.getAmount(), "DEPOSIT");
			transactionDAO.transactionDetails(accNo, transaction);

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

	public static double withdrawAmount(int accNo, Transaction transaction) {
		double balance = 0;
		// condition to validate email and amount

		try {

			if (!UserValidation.isValidAmount(transaction.getAmount())) {
				throw new ValidException("Amount Invalid");
			}
			if (AmountValidator.isSufficientAmount(accNo, transaction.getAmount())) {
				throw new ValidException("InSufficient Balance in Your Account!");
			}
			if (!transactionDAO.exists(accNo)) {
				throw new ValidException("Invalid Account Number!");
			}
			balance = transactionDAO.withdraw(accNo, transaction.getAmount());
			updateDAO.updateBalance(accNo, transaction.getAmount(), "WITHDRAW");
			transactionDAO.transactionDetails(accNo, transaction);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return balance;
	}

	/**
	 * This method transfers amount to another user account
	 * 
	 * @param senderAccNo
	 * @param receiverAccNo
	 * @param transfer
	 * @return
	 */
	public static double transferAmount(int senderAccNo, int receiverAccNo, Transaction transfer) {
		double balance = 0;
		try {
			if (!UserValidation.isValidAmount(transfer.getAmount())) {
				throw new ValidException("Invalid Amount");
			}
			if (AmountValidator.isSufficientAmount(senderAccNo, transfer.getAmount())) {
				throw new ValidException("InSufficient Balance in Your Account!");
			}
			if (!transactionDAO.exists(receiverAccNo)) {
				throw new ValidException("Invalid Receiver Account Number!");
			}
			if (senderAccNo == receiverAccNo) {
				throw new ValidException("Enter Receiver Account Number!");
			}
			if (!transactionDAO.exists(senderAccNo)) {
				throw new ValidException("Invalid Account Number!");
			}
			balance = transactionDAO.fundTransfer(senderAccNo, receiverAccNo, transfer.getAmount());
			updateDAO.updateBalance(receiverAccNo, transfer.getAmount(), "DEPOSIT");
			updateDAO.updateBalance(senderAccNo, transfer.getAmount(), "WITHDRAW");
			transactionDAO.transactionDetails(senderAccNo, transfer);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return balance;
	}

	/**
	 * This method gives the bank statement of the user
	 * 
	 * @param accno
	 * @return
	 */
	public static List<Transaction> transactionSummary(int accno) {
		List<Transaction> display = null;
		try {
			if (UserValidation.isValidAccount(accno)) {
				display = transactionDAO.summary(accno);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new ValidException("Unable to fetch details");

		}
		return display;
	}
	/**
	 * This method shows the last few transactions of the User
	 * @param accno
	 * @return
	 */
	public static List<Transaction> statement(int accno) {
		List<Transaction> display = null;
		try {
			if (UserValidation.isValidAccount(accno)) {
				display = transactionDAO.miniStatement(accno);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new ValidException("Unable to fetch details");

		}
		return display;
	}

}
