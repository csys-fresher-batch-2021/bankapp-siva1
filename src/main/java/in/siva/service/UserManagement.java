package in.siva.service;

import java.sql.SQLException;
import java.util.List;

import in.siva.dao.UserManagementDAO;
import in.siva.exception.ValidException;
import in.siva.model.User;
import in.siva.validator.UserValidation;

public class UserManagement {

	private UserManagement() {
		// Default Constructor
	}

	/**
	 * Validation for Login
	 * 
	 * @param userName     // name of the user
	 * @param userPassword //password given by the user
	 * @return
	 */
	private static UserManagementDAO userDAO = new UserManagementDAO();

	public static boolean loginValidation(String email, String userPassword) {
		boolean valid = false;
		// Condition for name and password validation
		if (UserValidation.emailValidation(email) && UserValidation.passwordValidation(userPassword)) {

			valid = userDAO.login(email, userPassword);

		} else {
			throw new ValidException("Enter your registered details");
		}

		return valid;
	}

	/**
	 * Register with user details Add the user Details in ArrayList
	 * 
	 * @param list //Details of user
	 */
	public static boolean registerDetails(User user) {
		boolean register = false;
		// Condition for valid details
		if (UserValidation.isValidUser(user)) {
			try {
				register = userDAO.register(user);
			} catch (SQLException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new ValidException("Give correct details");
		}
		return register;

	}

	/**
	 * Method to get all user details in arrayList
	 * 
	 * @param name //name of user
	 * @return
	 */
	public static List<User> getAllUser(String email) {

		List<User> display = null;
		try {
			if (UserValidation.emailValidation(email)) {
				display = userDAO.getUsers(email);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();

		}
		return display;
	}

	public static boolean accountStatus(int accno, boolean status) {
		boolean isValid = false;

		if (accno > 0) {

			isValid = true;
			userDAO.status(accno, status);
		}
		return isValid;
	}

	public static int getAccNo(String email) {
		int accNo = 0;
		try {
			if (UserValidation.emailValidation(email)) {
				accNo = userDAO.getAccountNo(email);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return accNo;
	}
}
