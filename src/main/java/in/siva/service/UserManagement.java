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
		if (UserValidation.emailValidation(email) && UserValidation.passwordValidation(userPassword)) {

			try {
				valid = userDAO.login(email, userPassword);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		} else {
			throw new ValidException("Invalid User Details");
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
			throw new ValidException("Invalid Details");
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

			e.printStackTrace();
			throw new ValidException("Unable to fetch details");

		}
		return display;
	}

	public static boolean accountStatus(int accno, boolean status) {
		boolean isValid = false;

		try {
			if (accno > 0) {

				isValid = true;
				userDAO.status(accno, status);
			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new ValidException("Unable to fetch status");
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
			throw new ValidException("Unable to get Account Number");
		}
		return accNo;
	}
}
