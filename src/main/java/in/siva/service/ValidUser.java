package in.siva.service;

import in.siva.model.User;
import in.siva.validator.UserValidation;

public class ValidUser {
	
	
	private ValidUser() {
		//Default Constructor
	}
	/**
	 * Validation for user details
	 * 
	 * @param user // details of user
	 * @return
	 */
	public static boolean isValidUser(User user) {
		boolean valid = false;
		boolean validAccno = UserValidation.accountNumberValidation(user.getAccNo());
		boolean validEmail = UserValidation.emailValidation(user.getEmail());
		boolean validMobile = UserValidation.mobileNumberValidation(user.getMobileNo());
		boolean validName = UserValidation.nameValidation(user.getName());
		boolean validPass = UserValidation.passwordValidation(user.getPassword());
		if (validAccno && validEmail && validName && validMobile && validPass) {
			valid = true;
		}
		return valid;

	}
}
