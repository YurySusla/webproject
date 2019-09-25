package by.itClass.model.factories;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import by.itClass.model.beans.Event;

public class EventFactory {
	
	public static List<Event> getEvents(String[] titlesEv, String[] timesEv) throws ParseException {
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < timesEv.length; i++) {
			String title = titlesEv[i];
			String time = timesEv[i];
			try {
				events.add(new Event(title, time));
			} catch (ParseException e) {
				throw e;
			}
		}
		return events;
	}
}
