package by.itClass.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.itClass.constants.Constants;
import by.itClass.model.beans.Conference;
import by.itClass.model.beans.Event;
import by.itClass.model.beans.User;
import by.itClass.model.factories.EventFactory;
import by.itClass.model.impl.ConferenceImpl;
import by.itClass.model.interfaces.IconferenceDAO;


@WebServlet("/addConf")
public class AddConferenceController extends AbstractController {
	private static final long serialVersionUID = 1L;
       
    
    public AddConferenceController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter(Constants.TITLE);
		String descr = request.getParameter(Constants.DESCR);
		String place = request.getParameter(Constants.PLACE);
		String date = request.getParameter(Constants.DATE);
		
		if(title == null || descr == null || place == null || date == null) {
			jumpError(request, response, Constants.ADD_CONF_JSP, Constants.NULL_MESSAGE);
			return;
		}
		
		if(title.equals(Constants.EMPTY)) {
			jumpError(request, response, Constants.ADD_CONF_JSP, Constants.EMPTY_MESS);
			return;
		}
		
		try {
			Conference conference = new Conference(title, descr, place, date);
			HttpSession session = request.getSession();
			User user =(User) session.getAttribute(Constants.USER);
			IconferenceDAO confDAO = new ConferenceImpl();
			int idConf = confDAO.addConference(conference, user);
			if (idConf != -1) {
				String[] titlesEv = request.getParameterValues(Constants.TITLE_EVENT);
				String[] timesEv  = request.getParameterValues(Constants.TIME);
				List<Event> events = EventFactory.getEvents(titlesEv, timesEv);
				confDAO.addConferenceEvents(events, idConf);
				jump(request, response, Constants.CONF_CONTR);
			} else {
				jumpError(request, response, Constants.ADD_CONF_JSP, Constants.WRONG_ADD_CONF);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			jumpError(request, response, Constants.ADD_CONF_JSP, Constants.INCORRECT_DATE);
		} catch (SQLException e) {
			e.printStackTrace();
			jumpError(request, response, Constants.ADD_CONF_JSP, e.getMessage());
		}
		return;
	}
	}

