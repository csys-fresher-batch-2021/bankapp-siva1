package in.siva.connectionutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private ConnectionUtil() {
		//Default Constructor
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		String driverClass = "org.postgresql.Driver";
		String url = "jdbc:postgresql://projecttracker.ck1ayq0lncmp.ap-south-1.rds.amazonaws.com/bankapp_db";
		String username = "prod_user";
		String password = "prod_user";

		// Loading the JDBC driver in memory
		Class.forName(driverClass);

		// Getting the connection
		return DriverManager.getConnection(url, username, password);

	}

	/**
	 * Method overloading concept to close connection
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

}
