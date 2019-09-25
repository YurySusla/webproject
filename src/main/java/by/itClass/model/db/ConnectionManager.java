package by.itClass.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.itClass.constants.Constants;

public class ConnectionManager {
	private static Connection cn = null;

	static {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection createConnection() throws SQLException {
		if (cn == null || cn.isClosed()) {
			try {
				cn = DriverManager.getConnection(Constants.URL_DB, Constants.USER_DB, Constants.PASSWORD_DB);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cn;
	}
	
	public static void closeConnection() {
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
