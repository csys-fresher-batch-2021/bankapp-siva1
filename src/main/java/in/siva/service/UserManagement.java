package in.siva.service;

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

	public static boolean loginValidation(String email, String userPassword) {
		boolean valid = false;
		// Condition for name and password validation
		if (UserValidation.emailValidation(email) && UserValidation.passwordValidation(userPassword)) {
			valid = UserManagementDAO.login(email, userPassword);
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
			register = UserManagementDAO.register(user);
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
		// Condition for valid email
		if (UserValidation.emailValidation(email)) {
			display = UserManagementDAO.getUsers(email);
		}
		else
		{
			throw new ValidException("Enter Your Correct Account Name");
		}
		return display;
	}
}
