package by.itClass.model.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import by.itClass.constants.SQLConstants;
import by.itClass.model.beans.Conference;
import by.itClass.model.beans.Event;
import by.itClass.model.beans.User;
import by.itClass.model.db.ConnectionManager;
import by.itClass.model.enums.SectionKind;
import by.itClass.model.interfaces.IconferenceDAO;
import by.itClass.model.managers.ConferenceManager;

public class ConferenceImpl implements IconferenceDAO{

	@Override
	public List<Conference> getConferences(Enum<?> section, User user) throws SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
				
		SectionKind sect = (SectionKind) section;
		String sql = sect.getSql();
		
		List<Conference> conferences = new ArrayList<Conference>();
		try {
			cn = ConnectionManager.createConnection();
			pst = cn.prepareStatement(sql);
			if(sect == SectionKind.CONF_BY_USER) {
				pst.setInt(1, user.getId());
			}
			rs = pst.executeQuery();
			while(rs.next()){
				int id = rs.getInt(SQLConstants.ID_LABLE);
				String title = rs.getString(SQLConstants.TITLE_LABLE);
				String descr = rs.getString(SQLConstants.DESCRIPTION_LABLE);
				String place = rs.getString(SQLConstants.PLACE_LABLE);
				Date date = rs.getDate(SQLConstants.DATE_LABLE);
				conferences.add(new Conference(id, title, descr, place, date));
			}		
		}finally {
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(pst);
			ConnectionManager.closeConnection();
		}
		return conferences;
	}

	@Override
	public int fillConferencEvents(List<Conference> conferences, String idConf) throws SQLException, ParseException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		int indexConf = ConferenceManager.getIndex(conferences, idConf);
		
		if(indexConf == -1) return indexConf;
		
		try {
			cn = ConnectionManager.createConnection();
			pst = cn.prepareStatement(SQLConstants.SELECT_EVENTS);
			pst.setString(1, idConf);
			rs = pst.executeQuery();
			List<Event> events = new ArrayList<Event>();
			while(rs.next()) {
				int id = rs.getInt(SQLConstants.ID_LABLE);
				String title = rs.getString(SQLConstants.TITLE_LABLE);
				String time = rs.getString(SQLConstants.TIME_LABEL);
				events.add(new Event(id, title, time));
			}
			conferences.get(indexConf).setEvents(events);
			
		} finally {
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(pst);
			ConnectionManager.closeConnection();
		}
		
		return indexConf;
	}

	@Override
	public int addConference(Conference conference, User user) throws SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int idConf = -1;
		
		try {
			cn = ConnectionManager.createConnection();
			pst = cn.prepareStatement(SQLConstants.INSERT_CONF, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, user.getId());
			pst.setString(2, conference.getTitle());
			pst.setString(3, conference.getDescr());
			pst.setString(4, conference.getPlace());
			System.out.println(conference.getDate());
			pst.setDate(5, conference.getDate());
			if(pst.executeUpdate() != 0) {
				rs = pst.getGeneratedKeys();
				if(rs.next()) {
					idConf = rs.getInt(1);
				}
			}
		} finally {
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(pst);
			ConnectionManager.closeConnection();
		}		
		return idConf;
	}

	@Override
	public void addConferenceEvents(List<Event> events, int idConf) throws SQLException {
		Connection cn = null;
		PreparedStatement pst = null;
		
		try {
			cn = ConnectionManager.createConnection();
			pst = cn.prepareStatement(SQLConstants.INSERT_EVENTS);
			pst.setInt(1, idConf);
			for (Event event : events) {
				pst.setString(2, event.getTitle());
				pst.setTime(3, event.getTime());
				pst.executeUpdate();
			}
			
		} finally {
			ConnectionManager.closeStatement(pst);
			ConnectionManager.closeConnection();
		}
		
	}
	
}
