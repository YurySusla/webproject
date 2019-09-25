package by.itClass.model.managers;

import java.util.List;

import by.itClass.model.beans.Conference;

public class ConferenceManager {
	public static int getIndex(List<Conference> conferences, String idConf) {
		int id;
		try {
			id = Integer.parseInt(idConf);
			
		}catch (NumberFormatException e) {
			return -1;
		}
		int index;
		for (index = 0; index < conferences.size(); index++) {
			if(id == conferences.get(index).getId()) {
				break;
			}
		}
		if(index == conferences.size()) {
			index = -1;
		}
		
		return index;
	}
}
