package in.siva.exception;

public class DBException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception thrown for error in Database and its Connection
	 * 
	 * @param message
	 */
	public DBException(String message) {
		super(message);

	}

	/**
	 * Exception thrown for error in Database and its Connection
	 * 
	 * @param e       // Exception message to pass
	 * @param message
	 */
	public DBException(Exception e, String message) {
		super(message, e);
	}
}
