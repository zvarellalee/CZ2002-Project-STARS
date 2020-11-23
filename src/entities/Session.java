/**
 * Represents a class for a module.
 * Each session can be a lecture, tutorial or lab.
 * Each session has a venue, start time and end time.
 * @author Stanley Ho
 * @version 1.0
 * @since 2020-11-20
 */

package entities;

import java.io.Serializable;
import java.util.Calendar;

/* Each session refers to a particular class for a module with its specified date, time and venue.*/

public class Session implements Serializable{
	/**
	 * Stores the type of Session such as
	 * LEC (Lecture), TUT (Tutorial) or LAB (Lab).
	 */
	private String sessionType; // type of session
	/**
	 * Stores the location that Session is held.
	 */
	private String venue;		// location of session
	/**
	 * Stores the start time of Session.
	 */
	private Calendar sessionStart; // start time
	/**
	 * Stores the end time of Session.
	 */
	private Calendar sessionEnd; // end time
	
	/**
	 * Initialising the Session
	 * @param sessionType Type of Session
	 * @param venue Location of Session
	 * @param sessionStart Start time of Session
	 * @param sessionEnd End time of Session
	 */
	public Session(String sessionType, String venue, Calendar sessionStart, Calendar sessionEnd) {
		this.sessionType = sessionType;
		this.venue = venue;
		this.sessionStart = sessionStart;
		this.sessionEnd = sessionEnd;
	}
	
	// ------ accessor methods ------
	/**
	 * Gets the type of Session 
	 * @return sessionType Type of Session 
	 */
	public String getSessionType() {
		return sessionType;
	}
	
	/**
	 * Gets the location of Session
	 * @return venue Location of Session
	 */
	public String getVenue() {
		return venue;
	}
	
	/**
	 * Gets the start time of Session
	 * @return sessionStart Start time of Session
	 */
	public Calendar getSessionStart() {
		return sessionStart;
	}
	
	/**
	 * Gets the end time of Session
	 * @return sessionEnd End time of Session
	 */
	public Calendar getSessionEnd() {
		return sessionEnd;
	}
	
	// ------ mutator methods ------
	/**
	 * Sets the type of session
	 * @param sessionType Type of Session
	 */
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	
	/**
	 * Sets the location of session
	 * @param venue Location of session
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	/**
	 * Sets the start time of Session
	 * @param sessionStart Start time of Session
	 */
	public void setSessionStart(Calendar sessionStart) {
		this.sessionStart = sessionStart;
	}
	
	/**
	 * Sets the end time of Session
	 * @param sessionEnd End time of Session
	 */
	public void setSessionEnd(Calendar sessionEnd) {
		this.sessionEnd = sessionEnd;
	}
}
