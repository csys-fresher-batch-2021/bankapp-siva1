package in.siva.validator;

import java.util.List;

import in.siva.exception.ValidException;
import in.siva.model.User;
import in.siva.service.AdminService;

public class ExistingDetailValidator {
	private ExistingDetailValidator() {
		// Default Constructor
	}

	public static void isExistingDetails(String email, long mobileno) throws ValidException {

		List<User> list = AdminService.getAllUser();

		for (User user : list) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new ValidException("Email Id Already Exists");

			}
			if(user.getMobileNo() == mobileno) {
				throw  new ValidException("Mobile Number Already Exists");
			}

		}
	}
}
