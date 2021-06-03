package in.siva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import in.siva.connectionutil.ConnectionUtil;
import in.siva.exception.DBException;

import in.siva.model.User;

public class UserManagementDAO {

	private static UserManagementDAO instance = new UserManagementDAO();

	/**
	 * This method used to get instance of the UsermanagementDAO
	 * 
	 * @return UserManagementDAO
	 */
	public static UserManagementDAO getInstance() {
		return instance;
	}

	/**
	 * Validation for Login
	 * 
	 * @param userName     // name of the user
	 * @param userPassword //password given by the user
	 * @return
	 * @throws SQLException
	 */

	public boolean login(String email, String password) throws SQLException {
		PreparedStatement pst = null;
		Connection connection = null;
		ResultSet rs = null;
		boolean valid = false;
		try {

			connection = ConnectionUtil.getConnection();
			String sql = "select email,password from bank_userdetails where email = ? and password =?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				valid = true;
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to Login");
		} finally {

			ConnectionUtil.close(rs, pst, connection);
		}

		return valid;
	}

	/**
	 * Register with user details Add the user Details in ArrayList
	 * 
	 * @param list //Details of user
	 * @throws SQLException
	 */

	public boolean register(User user) throws SQLException {

		boolean register = false;
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			// Get the connection
			connection = ConnectionUtil.getConnection();

			// Query
			if (connection != null) {
				String sql = "insert into bank_userdetails(name,password,email,address,balance,mobileno,created_date)values(?,?,?,?,?,?,?)";
				LocalDateTime dateTime = user.getCreatedDate();
				Timestamp date = Timestamp.valueOf(dateTime);
				// Execute
				pst = connection.prepareStatement(sql);
				pst.setString(1, user.getName());
				pst.setString(2, user.getPassword());
				pst.setString(3, user.getEmail());
				pst.setString(4, user.getAddress());
				pst.setFloat(5, user.getBalance());
				pst.setLong(6, user.getMobileNo());
				pst.setTimestamp(7, date);
				pst.executeUpdate();
				register = true;
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new DBException("Unable to Register");
		} finally {

			ConnectionUtil.closed(connection, pst);
		}

		return register;
	}

	/**
	 * Method to get all user details in arrayList
	 * 
	 * @param name //name of user
	 * @return
	 */

	public User getUsers(int accNo) throws ClassNotFoundException, SQLException {
		User user = null;

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select name,email,address,balance,mobileno,accno,created_date,active from bank_userdetails where accNo=? ";
			pst = connection.prepareStatement(sql);
			pst.setInt(1, accNo);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				String name = rs.getString(1);
				String emailId = rs.getString(2);
				String address = rs.getString(3);
				float balance = rs.getFloat(4);
				long mobileno = rs.getLong(5);
				int accno = rs.getInt(6);
				Timestamp date = rs.getTimestamp(7);
				boolean active = rs.getBoolean(8);
				LocalDateTime dateTime = date.toLocalDateTime();
				user.setName(name);
				user.setEmail(emailId);
				user.setAddress(address);
				user.setBalance(balance);
				user.setMobileNo(mobileno);
				user.setAccNo(accno);
				user.setCreatedDate(dateTime);
				user.setActive(active);

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new DBException("Unable to List User Details");
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return user;

	}

	public void status(int accNo, boolean status) {

		Connection connection = null;
		PreparedStatement pst = null;

		try {
			connection = ConnectionUtil.getConnection();
			String sql = "update bank_userdetails set active= ? where accno = ?";
			pst = connection.prepareStatement(sql);
			pst.setBoolean(1, status);
			pst.setInt(2, accNo);
			pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
			throw new DBException("Unable to View the Status");
		} finally {
			ConnectionUtil.closed(connection, pst);
		}
	}

	public int getAccountNo(String email) throws ClassNotFoundException, SQLException {

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select accno from bank_userdetails where email = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			int accno = 0;
			if (rs.next()) {

				accno = rs.getInt("accno");
			}
			return accno;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Invalid Account Number");

		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
	}

}
