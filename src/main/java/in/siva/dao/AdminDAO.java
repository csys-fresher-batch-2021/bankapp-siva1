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
import in.siva.model.User;
import in.siva.util.ConnectionUtil;

public class AdminDAO {

	private static AdminDAO instance = new AdminDAO();

	/**
	 * This method returns the instance of AdminDAO
	 * 
	 * @return
	 */
	public static AdminDAO getInstance() {
		return instance;
	}

	/**
	 * Login method for admin
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws DBException
	 */
	public boolean adminLogin(String userName, String password) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean valid = false;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select * from admin where userName =? and password = ?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, userName);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				valid = true;

			}
			return valid;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Invalid Login Credentials");
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}

	}

	/**
	 * This method shows all the users detail for admin
	 * 
	 * @return
	 * @throws DBException
	 */

	public List<User> getAllUsers() throws DBException {

		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select name,email,address,balance,mobileno,accno,created_date,active from bank_userdetails order by accno asc";
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
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
				userList.add(user);

			}
			return userList;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Invalid details");
		} finally {
			ConnectionUtil.close(rs, pst, connection);

		}

	}

}
