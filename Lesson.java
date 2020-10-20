package cz2002_Project;

import java.util.Calendar;

public class Lesson {
	private String sessionType; // type of session
	private String venue;		// location of session
	private Calendar sessionStart; // start time
	private Calendar sessionEnd; // end time
	
	public Lesson(String sessionType, String venue, Calendar sessionStart, Calendar sessionEnd) {
		this.sessionType = sessionType;
		this.venue = venue;
		this.sessionStart = sessionStart;
		this.sessionEnd = sessionEnd;
	}
	
	// ------ accessor methods ------
	public String getSessionType() {
		return sessionType;
	}
	
	public String getVenue() {
		return venue;
	}
	
	public Calendar getSessionStart() {
		return sessionStart;
	}
	
	public Calendar getSessionEnd() {
		return sessionEnd;
	}
	
	// ------ mutator methods ------
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public void setSessionStart(Calendar sessionStart) {
		this.sessionStart = sessionStart;
	}
	
	public void setSessionEnd(Calendar sessionEnd) {
		this.sessionEnd = sessionEnd;
	}
}
