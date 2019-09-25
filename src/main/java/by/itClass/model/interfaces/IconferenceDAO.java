package by.itClass.model.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import by.itClass.model.beans.Conference;
import by.itClass.model.beans.Event;
import by.itClass.model.beans.User;

public interface IconferenceDAO {
	List<Conference> getConferences(Enum<?> section, User user) throws SQLException;
	int fillConferencEvents(List<Conference> conferences, String idConf) throws SQLException, ParseException;
	int addConference(Conference conference, User user) throws SQLException;
	void addConferenceEvents(List<Event> events,int idConf) throws SQLException;
}
