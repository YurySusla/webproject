package by.itClass.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.itClass.constants.Constants;
import by.itClass.model.beans.Conference;
import by.itClass.model.beans.User;
import by.itClass.model.enums.SectionKind;
import by.itClass.model.enums.SectionManager;
import by.itClass.model.impl.ConferenceImpl;
import by.itClass.model.interfaces.IconferenceDAO;

@WebServlet(urlPatterns = {"/conf"})
public class ConferenceController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    public ConferenceController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter(Constants.SECTION);

		if(param == null) {
			param = Constants.TODAY;
		}
		try {
			IconferenceDAO confDAO = new ConferenceImpl();
			Enum<?> section = SectionManager.getSectionKind(param);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(Constants.USER);
			List<Conference> conferences = confDAO.getConferences(section, user);
			
			session.setAttribute(Constants.CONF_LIST, conferences);
			
			if(section == SectionKind.CONF_BY_USER) {
				jump(request, response, Constants.HOME_JSP);
			}
			else {
				jump(request, response, Constants.INDEX_JSP);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			jumpError(request, response, Constants.INDEX_JSP, e.getMessage());
			return;
		}
		
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
