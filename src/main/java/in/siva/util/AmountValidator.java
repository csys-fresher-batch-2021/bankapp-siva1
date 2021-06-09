package in.siva.util;

import in.siva.model.User;
import in.siva.service.UserService;

public class AmountValidator {

	private AmountValidator() {
		// Default Constructor
	}

	/**
	 * This method validates the amount
	 * 
	 * @param accNo
	 * @param amount
	 * @return
	 */
	public static boolean isSufficientAmount(int accNo, float amount) {

		boolean isSufficient = false;
		User user = UserService.getUser(accNo);
		if (user.getBalance() < amount) {
			isSufficient = true;

		}
		return isSufficient;
	}
}