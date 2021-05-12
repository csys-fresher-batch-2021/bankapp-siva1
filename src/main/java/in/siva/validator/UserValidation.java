package in.siva.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {

	private UserValidation() {

	}

	/**
	 * To validate the Users Name
	 * 
	 * @param name // Name of user
	 * @return
	 */
	public static boolean nameValidation(String name) {

		boolean valid = false;
		// Name Pattern Declaration
		String regx = "^\\w{4,10}$";

		if (name != null) {
			Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(name);// Matches the pattern with the name
			valid = matcher.find();
		}

		return valid;

	}

	/**
	 * Validation for password
	 * 
	 * @param password // password of user
	 * @return
	 */

	public static boolean passwordValidation(String password) {

		boolean valid = false;
		// Password Pattern Declaration
		String check = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
		if (password != null) {

			Pattern passwordPattern = Pattern.compile(check);
			Matcher passwordMatcher = passwordPattern.matcher(password);// Matches the given pattern with the password
			valid = passwordMatcher.matches();

		}
		return valid;
	}
}
