package in.siva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.siva.connectionutil.ConnectionUtil;
import in.siva.model.User;


public class UserManagementDAO {
	private UserManagementDAO() {
		// Default Constructor
	}

	private static final List<User> userList = new ArrayList<>();// created ArrayList
	// Details of user
	static {
		User user1 = new User();
		user1.setAccNo(100000001l);
		user1.setName("Siva");
		user1.setEmail("vishvajith257@gmail.com");
		user1.setPassword("Siva@123");
		user1.setMobileNo(9789231919l);
		user1.setAddress("Palayamkottai");
		user1.setBalance(50000);
		userList.add(user1);

		User user2 = new User();
		user2.setAccNo(100000002l);
		user2.setName("Ramesh");
		user2.setEmail("gururam12@gmail.com");
		user2.setPassword("Gkram@123");
		user2.setAddress("Chennai");
		user2.setMobileNo(9361363167l);
		user2.setBalance(25000);

		userList.add(user2);
	}

	public static List<User> getList() {
		return userList;
	}

	/**
	 * Validation for Login
	 * 
	 * @param userName     // name of the user
	 * @param userPassword //password given by the user
	 * @return
	 * @throws SQLException
	 */

	public static boolean login(String email, String password) throws SQLException {
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

			if (pst != null) {

				pst.close();
			}

			if (connection != null) {

				connection.close();

			}
			if (rs != null) {
				rs.close();
			}
		}

		return valid;
	}

	/**
	 * Register with user details Add the user Details in ArrayList
	 * 
	 * @param list //Details of user
	 * @throws SQLException
	 */

	public static boolean register(User user) throws SQLException {

		boolean register = false;
		Connection connection = null;
		PreparedStatement pst = null;

		try {
			// Get the connection
			connection = ConnectionUtil.getConnection();

			// Query
			if (connection != null) {
				String sql = "insert into bank_userdetails(name,password,email,address,balance,mobileno)values(?,?,?,?,?,?)";

				// Execute
				pst = connection.prepareStatement(sql);
				pst.setString(1, user.getName());
				pst.setString(2, user.getPassword());
				pst.setString(3, user.getEmail());
				pst.setString(4, user.getAddress());
				pst.setFloat(5, user.getBalance());
				pst.setLong(6, user.getMobileNo());

				pst.executeUpdate();
				register = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (pst != null) {

				pst.close();

			}
			if (connection != null) {

				connection.close();

			}
		}

		return register;
	}

	/**
	 * Method to get all user details in arrayList
	 * 
	 * @param name //name of user
	 * @return
	 */

	public static List<User> getUsers(String email) throws ClassNotFoundException, SQLException {
		List<User> list = new ArrayList<>();

		Connection connection = ConnectionUtil.getConnection();
		String sql = "select name,email,address,balance,mobileno,accno from bank_userdetails where email=? ";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			User user = new User();
			String name = rs.getString(1);
			String emailId = rs.getString(2);
			String address = rs.getString(3);
			float balance = rs.getFloat(4);
			long mobileno = rs.getLong(5);
			int accno = rs.getInt(6);
			user.setName(name);
			user.setEmail(emailId);
			user.setAddress(address);
			user.setBalance(balance);
			user.setMobileNo(mobileno);
			user.setAccNo(accno);

			list.add(user);
		}

		ConnectionUtil.close(rs, pst, connection);
		return list;
		
	}

	
	
}
