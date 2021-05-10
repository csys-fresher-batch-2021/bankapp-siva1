package in.siva.app;

import java.util.List;
import java.util.ArrayList;

public class UserManagement {
	
	public static List<User> userList = new ArrayList<User>();

	static {
		User user1 = new User();
		user1.accNo = 1234l;
		user1.name = "Siva";
		user1.email = "vishvajith257@gmail.com";
		user1.password = "Siva@123";
		user1.blockedAcc = false;
		user1.balance = 10000;
		userList.add(user1);

		User user2 = new User();
		user2.accNo = 2345l;
		user2.name = "Ramesh";
		user2.email = "gururam12@gmail.com";
		user2.password = "gkram@123";
		user2.blockedAcc = false;
		user2.balance = 50000;
		userList.add(user2);
	}

	public static boolean loginValidation(String userName, String userPassword) {

		boolean valid = false;
		if (Validation.nameValidation(userName) && Validation.passwordValidation(userPassword)) {

			for (User validation : userList) {
				if (validation.name.equals(userName) && validation.password.equals(userPassword)) {

					valid = true;
					break;
				}

			}
		}
		return valid;
	}

}
