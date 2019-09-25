package by.itClass.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.itClass.constants.SQLConstants;
import by.itClass.model.beans.User;
import by.itClass.model.db.ConnectionManager;
import by.itClass.model.interfaces.IUserDAO;

public class UserImpl implements IUserDAO {

	@Override
	public User getUser(String login, String password) throws SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null; 
		User user = null;
		try {
			cn = ConnectionManager.createConnection();
			pst = cn.prepareStatement(SQLConstants.SELECT_USER);
			pst.setString(1, login);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while(rs.next()) {
				String log = rs.getString(SQLConstants.LOGIN_LABLE);
				String firstName = rs.getString(SQLConstants.FNAME_LABEL);
				String secondName = rs.getString(SQLConstants.SNAME_LABEL);
				String email = rs.getString(SQLConstants.EMAIL_LABEL);
				int id = rs.getInt(SQLConstants.ID_LABLE);
				user = new User(log, id, firstName, secondName, email);
			}
		}
		finally {
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(pst);
			ConnectionManager.closeConnection();
		}
		return user;
	}

	@Override
	public boolean isAddUser(User user, String password) throws SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		boolean result = false;
		try {
		cn = ConnectionManager.createConnection();
		pst = cn.prepareStatement(SQLConstants.INSERT_USER);
		pst.setString(1, user.getLogin());
		pst.setString(2, password);
		pst.setString(3, user.getFirstName());
		pst.setString(4,  user.getSecondName());
		pst.setString(5, user.getEmail());

		synchronized (UserImpl.class) {
			if(!isFoundLogin(user.getLogin())) {
				pst.executeUpdate();
				result = true;
			}
		}
		}finally {
			ConnectionManager.closeStatement(pst);
			ConnectionManager.closeConnection();
		}
		
		return result;
	}
	
	private boolean isFoundLogin(String login) throws SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null; 
		boolean result = false;
		try {
			cn = ConnectionManager.createConnection();
			pst = cn.prepareStatement(SQLConstants.FOUND_LOGIN);
			pst.setString(1, login);
			
			rs = pst.executeQuery();
			result = rs.next();
		}
		finally {
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(pst);
		}
		return result;
	}

	

}
