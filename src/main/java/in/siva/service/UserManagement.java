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

	/**
	 * Register with user details Add the user Details in ArrayList
	 * 
	 * @param list //Details of user
	 */
	public static boolean registerDetails(User user) {
		boolean register = false;
		// Condition for valid details

		if (UserValidation.isValidUser(user)) {

			int id = userList.size() + 1000001;
			user.setAccNo(id);
			userList.add(user);
			register = true;
		}
		return register;

	}

	/**
	 * Method to return arrayList
	 * 
	 * @return
	 */
	public static List<User> getList() {
		return userList;
	}

	/**
	 * Method to get all user details in arrayList
	 * 
	 * @param name //name of user
	 * @return
	 */
	public static List<User> getAllUser(String name) {
		List<User> search = new ArrayList<>();
		if (UserValidation.nameValidation(name)) {
			for (User display : userList) {
				if (display.getName().equalsIgnoreCase(name)) {
					search.add(display);
					break;
				}
			}
			return search;
		} else {
			throw new RuntimeException("Enter Your Correct Account Name");
		}

	}
}
