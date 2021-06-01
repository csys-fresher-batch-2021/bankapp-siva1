package in.siva.exception;

public class ValidException extends RuntimeException {

	/**
	 * User defined Exception
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception for Invalid Details
	 * 
	 * @param message
	 */
	public ValidException(String message) {
		super(message);
	}

	/**
	 * Exception for invalid details
	 * 
	 * @param e
	 * @param message
	 */
	public ValidException(Exception e, String message) {
		super(message, e);
	}
}
