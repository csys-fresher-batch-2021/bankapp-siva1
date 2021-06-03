package in.siva.service;

import java.util.List;

import in.siva.dao.AdminDAO;
import in.siva.exception.ValidException;
import in.siva.model.User;

import in.siva.validator.UserValidation;

public class AdminService {
	private AdminService() {
		// Default Constructor
	}

	private static AdminDAO adminDAO = new AdminDAO();

	public static boolean checkAdminLogin(String username, String password) {

		boolean valid = false;
		try {
			if (UserValidation.nameValidation(username)&&UserValidation.passwordValidation(password)) {
				valid = adminDAO.adminLogin(username, password);
			}
			return valid;
		} catch (Exception e) {
			throw new ValidException("Invalid Login Credentials");
		}
	}

	public static List<User> getAllUser() {
		List<User> userList = null;
		try {
			userList = adminDAO.getAllUsers();
		} catch (Exception e) {
			throw new ValidException("Details Not Available");
		}
		return userList;
	}
}
