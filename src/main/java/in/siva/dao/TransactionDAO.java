package in.siva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import in.siva.exception.DBException;
import in.siva.model.Transaction;
import in.siva.model.User;
import in.siva.util.ConnectionUtil;

public class TransactionDAO {

	private static TransactionDAO instance = new TransactionDAO();

	/**
	 * This method gets the instance of TransactionDAO
	 * 
	 * @return TransactionDAO
	 */
	public static TransactionDAO getInstance() {
		return instance;
	}

	/**
	 * Deposit an Amount for User account
	 * 
	 * @param name   // name of user
	 * @param amount // amount to deposit
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws DBException
	 */

	public double deposit(int accno, float amount) throws ClassNotFoundException, SQLException, DBException {
		double accountBalance = 0;
		Connection connection = null;
		PreparedStatement pst = null;

		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select balance from bank_userdetails where accno = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, accno);
			rs = pst.executeQuery();
			while (rs.next()) {
				float balance = rs.getFloat(1);
				accountBalance = balance + amount;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to Deposit");

		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}

		return accountBalance;
	}

	/**
	 * Withdraw money from account
	 * 
	 * @param name   // name of user
	 * @param amount // amount withdraw by user
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws DBException
	 */

	public double withdraw(int accno, float amount) throws ClassNotFoundException, SQLException, DBException {
		double accountBalance = 0;

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select balance from bank_userdetails where accno = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, accno);
			rs = pst.executeQuery();
			while (rs.next()) {
				float balance = rs.getFloat(1);
				if (balance > amount && amount > 0) {
					accountBalance = balance - amount;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new DBException("Unable to Withdraw");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return accountBalance;
	}

	/**
	 * Transfer fund from own account to another
	 * 
	 * @param fromAccNo
	 * @param toAccNo
	 * @param amount
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DBException
	 */
	public synchronized double  fundTransfer(int fromAccNo, int toAccNo, float amount)
			throws ClassNotFoundException, SQLException, DBException {
		double balance = 0;
		balance = withdraw(fromAccNo, amount);
		deposit(toAccNo, amount);
		return balance;

	}

	/**
	 * This method gets the account number of the user from database
	 * 
	 * @param accNo
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DBException
	 */
	public boolean exists(int accNo) throws ClassNotFoundException, SQLException, DBException {
		boolean exists = false;
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select accno from bank_userdetails where accNo = ?";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, accNo);

			rs = pst.executeQuery();
			if (rs.next()) {
				exists = true;
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new DBException("Invalid Account");
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}
		return exists;

	}

	/**
	 * This method stores the Transaction details of the user
	 * 
	 * @param accno
	 * @param trans
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public void transactionDetails(int accno, Transaction trans) throws ClassNotFoundException, SQLException {

		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			if (connection != null) {
				String sql = "insert into transaction_details(accno,amount,transaction_type,comments,transaction_time)values(?,?,?,?,?)";
				pst = connection.prepareStatement(sql);
				LocalDateTime dateTime = trans.getTransactionDate();
				Timestamp transactionDate = Timestamp.valueOf(dateTime);

				pst.setInt(1, accno);
				pst.setFloat(2, trans.getAmount());
				pst.setString(3, trans.getTransactiontype());
				pst.setString(4, trans.getComments());
				pst.setTimestamp(5, transactionDate);
				pst.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closed(connection, pst);
		}
	}

	/**
	 * This method shows the Transaction Statement of the user
	 * 
	 * @param accno
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Transaction> summary(int accno) throws ClassNotFoundException, SQLException {

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Transaction> list = new ArrayList<>();

		try {

			connection = ConnectionUtil.getConnection();
			String sql = "select bank.accno,bank.name,tr.amount,tr.comments,"
					+ "tr.transaction_type,tr.transaction_time from bank_userdetails bank,"
					+ "transaction_details tr where bank.accno=tr.accno and bank.accno=?"
					+ " order by tr.transaction_time desc";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, accno);
			rs = pst.executeQuery();
			while (rs.next()) {

				User user = new User();
				Transaction trans = new Transaction();
				user.setAccNo(rs.getInt(1));
				user.setName(rs.getString(2));
				trans.setAmount(rs.getFloat(3));
				trans.setComments(rs.getString(4));
				trans.setTransactiontype(rs.getString(5));
				Timestamp transTime = rs.getTimestamp(6);
				LocalDateTime transferTime = transTime.toLocalDateTime();
				trans.setTransactionDate(transferTime);
				trans.setUser(user);
				list.add(trans);
			}
			return list;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Invalid details");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
	}
	public List<Transaction> miniStatement(int accno) throws ClassNotFoundException, SQLException {

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Transaction> list = new ArrayList<>();

		try {

			connection = ConnectionUtil.getConnection();
			String sql = "select bank.accno,bank.name,tr.amount,tr.comments,"
					+ "tr.transaction_type,tr.transaction_time from bank_userdetails bank,"
					+ "transaction_details tr where bank.accno=tr.accno and bank.accno=?"
					+ " order by tr.transaction_time desc limit 5";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, accno);
			rs = pst.executeQuery();
			while (rs.next()) {

				User user = new User();
				Transaction trans = new Transaction();
				user.setAccNo(rs.getInt(1));
				user.setName(rs.getString(2));
				trans.setAmount(rs.getFloat(3));
				trans.setComments(rs.getString(4));
				trans.setTransactiontype(rs.getString(5));
				Timestamp transTime = rs.getTimestamp(6);
				LocalDateTime transferTime = transTime.toLocalDateTime();
				trans.setTransactionDate(transferTime);
				trans.setUser(user);
				list.add(trans);
			}
			return list;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Invalid details");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
	}
}
