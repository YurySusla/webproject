package by.itClass.controllers;

import by.itClass.constants.Constants;
import by.itClass.model.beans.User;
import by.itClass.model.impl.UserImpl;
import by.itClass.model.interfaces.IUserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public LoginController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(Constants.LOGIN);
		String password = request.getParameter(Constants.PASSWORD);
		//
		if(login == null || password == null) {
			jumpError(request, response, Constants.LOGIN_JSP, Constants.NULL_MESSAGE);
			return;
		}
		

		login = login.trim();
		password = password.trim();
		
		//
		if(login.equals(Constants.EMPTY) || password.equals(Constants.EMPTY)) {
			jumpError(request, response, Constants.LOGIN_JSP, Constants.EMPTY_MESS);
			return;
		}
		
		IUserDAO userDAO = new UserImpl();
		User user = null;
		try {
			user = userDAO.getUser(login, password);
			if(user != null) {
				HttpSession session= request.getSession(true);
				//session.setMaxInactiveInterval(60); - change live session interval
				//session.invalidate(); - end session
				session.setAttribute(Constants.USER, user);
				jump(request, response, Constants.CONF_CONTR);
			}
			else {
				jumpError(request, response, Constants.LOGIN_JSP, Constants.USER_NOT_FOUND);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			jumpError(request, response, Constants.LOGIN_JSP, e.getMessage());
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
