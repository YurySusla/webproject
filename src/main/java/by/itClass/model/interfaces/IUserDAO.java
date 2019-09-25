package by.itClass.model.interfaces;

import java.sql.SQLException;

import by.itClass.model.beans.User;

public interface IUserDAO {
	User getUser(String login, String password) throws SQLException;
	boolean isAddUser(User user, String password) throws SQLException;
}
