package in.siva.dao;

import java.util.ArrayList;
import java.util.List;

import in.siva.model.User;
import in.siva.service.UserManagement;

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
	 */

	public static boolean login(String email, String password) {

		boolean valid = false;
		for (User validation : userList) {
			// condition for matching the given name and password
			if (validation.getEmail().equalsIgnoreCase(email) && validation.getPassword().equals(password)) {

				valid = true;
				break;
			}

		}
		return valid;
	}

	/**
	 * Register with user details Add the user Details in ArrayList
	 * 
	 * @param list //Details of user
	 */

	public static boolean register(User user) {
		boolean register = false;
		int id = userList.size() + 1000001;
		user.setAccNo(id);
		userList.add(user);
		register = true;

		return register;
	}

	/**
	 * Method to get all user details in arrayList
	 * 
	 * @param name //name of user
	 * @return
	 */

	public static List<User> getUsers(String email) {
		List<User> search = new ArrayList<>();

		for (User display : userList) {
			if (display.getEmail().equals(email)) {
				search.add(display);
				break;
			}
		}
		return search;
	}

	/**
	 * Deposit an Amount for User account
	 * 
	 * @param name   // name of user
	 * @param amount // amount to deposit
	 */

	public static double deposit(String name, double amount) {
		double balance = 0;

		for (User deposit : getList()) {

			if (deposit.getEmail().equals(name)) {

				balance = amount + deposit.getBalance();// deposit calculation

				break;
			}

		}
		return balance;
	}

	/**
	 * Withdraw money from account
	 * 
	 * @param name   // name of user
	 * @param amount // amount withdraw by user
	 * @return
	 */

	public static double withdraw(String email, double amount) {
		double balance = 0;

		for (User withdraw : UserManagement.getAllUser(email)) {
			if (withdraw.getEmail().equals(email)) {
				balance = withdraw.getBalance() - amount;
				break;
			}
		}
		return balance;
	}

}
