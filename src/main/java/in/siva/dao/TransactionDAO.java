package in.siva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.siva.connectionutil.ConnectionUtil;

public class TransactionDAO {

	private TransactionDAO() {
		// Default Constructor
	}

	/**
	 * Deposit an Amount for User account
	 * 
	 * @param name   // name of user
	 * @param amount // amount to deposit
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public static double deposit(String email, double amount) throws ClassNotFoundException, SQLException {
		double accountBalance = 0;
		Connection connection = null;
		PreparedStatement pst = null;

		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select balance from bank_userdetails where email = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, email);
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

	/**
	 * Withdraw money from account
	 * 
	 * @param name   // name of user
	 * @param amount // amount withdraw by user
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public static double withdraw(String email, double amount) throws ClassNotFoundException, SQLException {
		double accountBalance = 0;

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select balance from bank_userdetails where email = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, email);
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