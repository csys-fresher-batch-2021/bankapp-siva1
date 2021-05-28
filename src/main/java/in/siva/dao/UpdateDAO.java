package in.siva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.siva.connectionutil.ConnectionUtil;

public class UpdateDAO {

	

	private static UpdateDAO instance = new UpdateDAO();

	/**
	 * This method gets instance of UpdateDAO
	 * 
	 * @return UpdateDAO
	 */
	public static UpdateDAO getInstance() {
		return instance;
	}

	public boolean updateBalance(int accno, double amount, String type) throws ClassNotFoundException, SQLException {

		boolean isUpdated = false;
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = null;

			if (type.equals("DEPOSIT")) {
				sql = "update bank_userdetails set balance = balance + ? where accno = ?";
			} else if (type.equals("WITHDRAW")) {
				sql = "update bank_userdetails set balance =balance - ? where accno = ?";
			}
			pst = connection.prepareStatement(sql);

			pst.setDouble(1, amount);
			pst.setInt(2, accno);
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
