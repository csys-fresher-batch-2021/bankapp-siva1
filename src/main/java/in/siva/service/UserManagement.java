package in.siva.service;

import java.util.List;

import in.siva.model.User;
import in.siva.validator.UserValidation;

import java.util.ArrayList;

public class UserManagement {

	private UserManagement() {
		// Default Constructor
	}

	public static List<User> userList = new ArrayList<User>();

	static {
		User user1 = new User();
		user1.accNo = 1234l;
		user1.name = "Siva";
		user1.email = "vishvajith257@gmail.com";
		user1.password = "Siva@123";
		userList.add(user1);

		User user2 = new User();
		user2.accNo = 2345l;
		user2.name = "Ramesh";
		user2.email = "gururam12@gmail.com";
		user2.password = "Gkram@123";
		userList.add(user2);
	}

	public static boolean loginValidation(String userName, String userPassword) {
		boolean valid = false;
		if (UserValidation.nameValidation(userName) && UserValidation.passwordValidation(userPassword)) {

			for (User validation : userList) {
				if (validation.name.equalsIgnoreCase(userName) && validation.password.equals(userPassword)) {

					valid = true;
					break;
				}

			}
		}

		return valid;
	}

}
