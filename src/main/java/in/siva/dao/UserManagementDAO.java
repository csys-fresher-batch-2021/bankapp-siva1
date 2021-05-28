package in.siva.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import in.siva.connectionutil.ConnectionUtil;
import in.siva.exception.ValidException;
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

	public boolean login(String email, String password){
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
			// Auto-generated catch block
			e.printStackTrace();
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

				// Execute
				pst = connection.prepareStatement(sql);
				pst.setString(1, user.getName());
				pst.setString(2, user.getPassword());
				pst.setString(3, user.getEmail());
				pst.setString(4, user.getAddress());
				pst.setFloat(5, user.getBalance());
				pst.setLong(6, user.getMobileNo());
				pst.setDate(7, user.getCreated_date());
				pst.executeUpdate();
				register = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
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

	public List<User> getUsers(String email) throws ClassNotFoundException, SQLException {
		List<User> list = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select name,email,address,balance,mobileno,accno,created_date,active from bank_userdetails where email=? ";
			pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				String name = rs.getString(1);
				String emailId = rs.getString(2);
				String address = rs.getString(3);
				float balance = rs.getFloat(4);
				long mobileno = rs.getLong(5);
				int accno = rs.getInt(6);
				Date date = rs.getDate(7);
				boolean active = rs.getBoolean(8);
				user.setName(name);
				user.setEmail(emailId);
				user.setAddress(address);
				user.setBalance(balance);
				user.setMobileNo(mobileno);
				user.setAccNo(accno);
				user.setCreated_date(date);
				user.setActive(active);
				list.add(user);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
		return list;

	}

	public void status(int accNo, boolean status){

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
			throw new ValidException("Invalid Account Number");

		} finally {
			ConnectionUtil.close(rs, pst, connection);
		}
	}

}
