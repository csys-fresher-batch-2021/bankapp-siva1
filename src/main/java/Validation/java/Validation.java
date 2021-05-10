package in.siva.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	public static boolean nameValidation(String name) {

		// Name Pattern Declaration
		String regx = "[a-zA-Z]+\\.?";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(name);// Matches the pattern with the name
		return matcher.find();

	}
	public static boolean passwordValidation(String password) {

		// Password Pattern Declaration
		String check = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
		if (password == null) {
			return false;
		}
		Pattern passwordPattern = Pattern.compile(check);
		Matcher passwordMatcher = passwordPattern.matcher(password);// Matches the given pattern with the password
		return passwordMatcher.matches();

	}
}
