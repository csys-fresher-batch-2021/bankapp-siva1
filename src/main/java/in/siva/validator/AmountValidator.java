package in.siva.validator;


import in.siva.model.User;
import in.siva.service.UserManagement;

public class AmountValidator {
	
	private AmountValidator() {
		//Default Constructor
	}

	public static boolean isSufficientAmount(int accNo,float amount) {
		
	boolean isSufficient = false;
	User user = UserManagement.getUser(accNo);
	if(user.getBalance()<amount) {
		isSufficient = true;
		
	}
	return isSufficient;
}
}