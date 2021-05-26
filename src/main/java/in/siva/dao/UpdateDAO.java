package in.siva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.siva.connectionutil.ConnectionUtil;

public class UpdateDAO {

	private UpdateDAO() {
		// Default Constructor
	}

	public static boolean updateBalance(String email, double amount, String type)
			throws ClassNotFoundException, SQLException {

		boolean isUpdated = false;
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = null;

			if (type.equals("DEPOSIT")) {
				sql = "update bank_userdetails set balance = balance + ? where email = ?";
			} else if (type.equals("WITHDRAW")) {
				sql = "update bank_userdetails set balance =balance - ? where email = ?";
			}
			pst = connection.prepareStatement(sql);

			pst.setDouble(1, amount);
			pst.setString(2, email);
			double updatedBalance = pst.executeUpdate();
			if (updatedBalance == 1) {
				isUpdated = true;
			}

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionUtil.closed(connection, pst);
		}
		return isUpdated;
	}

}
