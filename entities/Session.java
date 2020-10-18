package entities;

/* Each session refers to a particular class for a module with its specified date, time and venue.*/

import java.util.Calendar;

public class Session {
	private String sessionType; //such as lecture, tutorial, or lab
	private Calendar sessionStart;
	private Calendar sessionEnd;
	private String venue;
	
	public Session(String sessionType, Calendar sessionStart, Calendar sessionEnd, String venue) {
		this.sessionType = sessionType;
		this.sessionStart = sessionStart;
		this.sessionEnd = sessionEnd;
		this.venue = venue;
	}
	
	public String getSessionType() {
		return sessionType;
	}

	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}

	public Calendar getSessionStart() {
		return sessionStart;
	}

	public void setSessionStart(Calendar sessionStart) {
		this.sessionStart = sessionStart;
	}

	public Calendar getSessionEnd() {
		return sessionEnd;
	}

	public void setSessionEnd(Calendar sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
	
}
