package in.siva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.siva.connectionutil.ConnectionUtil;

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
	 */

	public double deposit(int accno, double amount) throws ClassNotFoundException, SQLException {
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
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}

		return accountBalance;
	}

	public void fundTransfer(int fromAccNo, int toAccNo, float amount) throws ClassNotFoundException, SQLException {
		withdraw(fromAccNo, amount);
		deposit(toAccNo, amount);
	}

	/**
	 * Withdraw money from account
	 * 
	 * @param name   // name of user
	 * @param amount // amount withdraw by user
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public double withdraw(int accno, double amount) throws ClassNotFoundException, SQLException {
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
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return accountBalance;
	}

}
