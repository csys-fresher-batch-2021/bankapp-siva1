package in.siva.connectionutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

	private ConnectionUtil() {
		// Default Constructor
	}
	private static final String DRIVERCLASS = System.getenv("spring.datasource.driver-class-name");
	private static final String URL = System.getenv("spring.datasource.url");
	private static final String USERNAME = System.getenv("spring.datasource.username");
	private static final String PASSWORD = System.getenv("spring.datasource.password");


	public static Connection getConnection() throws ClassNotFoundException, SQLException {


		// Loading the JDBC driver in memory
		Class.forName(DRIVERCLASS);

		// Getting the connection
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);

	}

	/**
	 * Method overloading concept to close connection , preparedstatement,resultset
	 * 
	 * @param rs
	 * @param statement
	 * @param connection
	 */

	public static void close(ResultSet rs, PreparedStatement statement, Connection connection) {
		try {

			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to close connection and prepared statement
	 * 
	 * @param connection
	 * @param statement
	 */
	public static void closed(Connection connection, PreparedStatement statement) {
		try {
			if (connection != null) {
				connection.close();

			}
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
