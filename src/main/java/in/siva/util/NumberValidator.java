package in.siva.util;

import in.siva.exception.ValidException;

public class NumberValidator {

	private NumberValidator() {
		// Default Constructor
	}

	/**
	 * To Convert the String into Float values
	 * 
	 * @param value
	 * @param message
	 * @return
	 */
	public static float parseFloat(String value, String message) {
		try {
			return Float.parseFloat(value);
		} catch (NumberFormatException e) {
			throw new ValidException(message);
		}
	}

	public static int parseInteger(String value, String message) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new ValidException(message);
		}
	}
	public static long parseLong(String value,String message) {
		try {
			return Long.parseLong(value);
		}catch(NumberFormatException e) {
			throw new ValidException(message);
		}
	}
}
