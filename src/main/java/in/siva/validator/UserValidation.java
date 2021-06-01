package in.siva.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.siva.model.User;

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
		String regx = "^[a-zA-Z\\s]+\\w{4,29}$";

		if (name != null) {
			Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);// Compiles the given pattern
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

			Pattern passwordPattern = Pattern.compile(check);// Compiles the given regex pattern
			Matcher passwordMatcher = passwordPattern.matcher(password);// Matches the given pattern with the password
			valid = passwordMatcher.matches();

		}
		return valid;
	}

	/**
	 * To validate the Users Email
	 * 
	 * @param email //Mail Id
	 * @return
	 */
	public static boolean emailValidation(String email) {

		boolean valid = false;
		// Email Pattern declaration
		String check = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
		// Business Logic
		if (email != null) {

			Pattern patternForEmail = Pattern.compile(check);// Compiles the given pattern
			Matcher matcherForEmail = patternForEmail.matcher(email);// Matches the pattern with the Email
			valid = matcherForEmail.matches();
		}
		return valid;
	}

	/**
	 * To validate mobile number of the user
	 * 
	 * @param mobileNo //mobile number given by the user
	 * @return
	 */
	public static boolean mobileNumberValidation(long mobileNo) {
		String mobile = Long.toString(mobileNo);
		boolean valid = false;
		String check = "^\\d{10}$";
		if (mobile != null) {
			Pattern numberPattern = Pattern.compile(check);// Compiles the given pattern
			Matcher numberMatcher = numberPattern.matcher(mobile); // Matches the given pattern with mobile no
			valid = numberMatcher.matches();
		}
		return valid;
	}

	/**
	 * TO validate initial amount deposit by user
	 * 
	 * @param amount
	 * @return
	 */
	public static boolean isValidAmount(float amount) {
		boolean valid = false;
		if (amount > 0) {
			valid = true;
		}
		return valid;
	}
	/**
	 * To validate user account number 
	 * @param accNo
	 * @return
	 */
	public static boolean isValidAccount(int accNo) {
		boolean valid = false;
		if (accNo > 0) {
			valid = true;
		}
		return valid;
	}

	/**
	 * To validate user details
	 * 
	 * @param user
	 * @return
	 */
	public static boolean isValidUser(User user) {
		boolean valid = false;
		boolean validEmail = UserValidation.emailValidation(user.getEmail());
		boolean validMobile = UserValidation.mobileNumberValidation(user.getMobileNo());
		boolean validName = UserValidation.nameValidation(user.getName());
		boolean validPass = UserValidation.passwordValidation(user.getPassword());
		boolean validAmount = UserValidation.isValidAmount(user.getBalance());
		if (validAmount && validEmail && validName && validMobile && validPass) {
			valid = true;
		}
		return valid;

	}

}
