package in.siva.service;

import java.util.List;

import in.siva.model.User;
import in.siva.validator.UserValidation;

import java.util.ArrayList;

public class UserManagement {

	private UserManagement() {
		// Default Constructor
	}

	private static final List<User> userList = new ArrayList<>();// created ArrayList
	// Details of user
	static {
		User user1 = new User();
		user1.setAccNo(1234l);
		user1.setName("Siva");
		user1.setEmail("vishvajith257@gmail.com");
		user1.setPassword("Siva@123");
		userList.add(user1);

		User user2 = new User();
		user2.setAccNo(2345l);
		user2.setName("Ramesh");
		user2.setEmail("gururam12@gmail.com");
		user2.setPassword("Gkram@123");
		userList.add(user2);
	}

	/**
	 * Validation for Login
	 * 
	 * @param userName     // name of the user
	 * @param userPassword //password given by the user
	 * @return
	 */

	public static boolean loginValidation(String userName, String userPassword) {
		boolean valid = false;
		// Condition for name and password validation
		if (UserValidation.nameValidation(userName) && UserValidation.passwordValidation(userPassword)) {

			for (User validation : userList) {
				// condition for matching the given name and password
				if (validation.getName().equalsIgnoreCase(userName) && validation.getPassword().equals(userPassword)) {

					valid = true;
					break;
				}

			}
		}

		return valid;
	}

	
}
