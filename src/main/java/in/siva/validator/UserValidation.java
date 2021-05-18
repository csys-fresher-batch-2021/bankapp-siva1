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
		String regx = "^\\w{4,10}$";

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

	
	public static boolean isValidInitialAmount(float amount) {
		boolean valid=false;
		if(amount>0) {
			valid=true;
		}
		return valid;
	}


public static boolean isValidUser(User user)
{
	boolean valid=false;
	boolean validEmail = UserValidation.emailValidation(user.getEmail());
	boolean validMobile = UserValidation.mobileNumberValidation(user.getMobileNo());
	boolean validName = UserValidation.nameValidation(user.getName());
	boolean validPass = UserValidation.passwordValidation(user.getPassword());
	boolean validAmount = UserValidation.isValidInitialAmount(user.getBalance());
	if(validAmount&&validEmail&&validName&&validMobile&&validPass)
	{
		valid=true;
	}
	return valid;
	

}


	/**
	 * To validate the bank account number
	 * 
	 * @param accountNo // account number of the user
	 * @return
	 */
	public static boolean accountNumberValidation(long accountNo) {
		String accNo = Long.toString(accountNo);
		boolean valid = false;
		String check = "[0-9]{9,18}";
		if (accNo != null) {
			Pattern accPattern = Pattern.compile(check); // compiles the given pattern
			Matcher accMatcher = accPattern.matcher(accNo); // matches the given pattern with the account number
			valid = accMatcher.matches();

		}
		return valid;
	}


}
