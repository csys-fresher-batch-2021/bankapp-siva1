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
	private static final String driverClass = System.getenv("spring.datasource.driver-class-name");
	private static final String url = System.getenv("spring.datasource.url");
	private static final String username = System.getenv("spring.datasource.username");
	private static final String password = System.getenv("spring.datasource.password");


	public static Connection getConnection() throws ClassNotFoundException, SQLException {


		// Loading the JDBC driver in memory
		Class.forName(driverClass);

		// Getting the connection
		return DriverManager.getConnection(url, username, password);

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
