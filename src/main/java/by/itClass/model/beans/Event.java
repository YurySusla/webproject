package by.itClass.model.beans;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.itClass.constants.Constants;

public class Event {
	private int id;
	private String title;
	private Time time;
	
	public Event() {
	}
	
	public Event(int id, String title, Time time) {
		this.id = id;
		this.title = title;
		this.time = time;
	}
	
	public Event(int id, String title, String time) throws ParseException {
		this.id = id;
		this.title = title;
		setTime(time);
	}

	public Event(String title, String time) throws ParseException {
		this.title = title;
		setTime(time);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
	public void setTime(String time) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(Constants.TIME_PATTERN);
		Date tempTime = format.parse(time);
		this.time = new Time(tempTime.getTime());
	}
}
