package control;

import java.util.ArrayList;
import java.util.Calendar;
import entities.Index;
import entities.RegisteredCourse;
import entities.Session;

public class ScheduleManager {
	// Compares whether the 2 session clashes
	private static boolean isOverlapping(Calendar sessionStart, Calendar sessionEnd, Calendar session2Start, Calendar session2End) {
	    return sessionStart.before(session2End) && session2Start.before(session2End);
	}
	
	// Check whether the 2 indexes clashes
	private static boolean indexClashes(Index index1, Index index2) {
		for (Session s : index1.getSessionList()) {
			for (Session t : index2.getSessionList()) {
				if (isOverlapping(s.getSessionStart(), s.getSessionEnd(), t.getSessionStart(), t.getSessionEnd()) == true)
					return true;
			}
		}
		return false;
	}
	
	// Check whether the new index clashes with enrolled indexes
	public static boolean willClash(Index newIndex, ArrayList<RegisteredCourse> regCourses) {
		for (RegisteredCourse c : regCourses) {
			if (indexClashes(newIndex, c.getIndex()) == true)
				return true;
		}
		return false;
	}
}
