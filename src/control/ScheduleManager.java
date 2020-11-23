package control;

import java.util.ArrayList;
import java.util.Calendar;

import entities.Course;
import entities.Index;
import entities.RegisteredCourse;
import entities.Session;

public class ScheduleManager {
	// Compares whether the 2 session clashes
	private static boolean isOverlapping(Calendar sessionStart, Calendar sessionEnd, Calendar session2Start, Calendar session2End) {
		return sessionStart.before(session2End) && session2Start.before(sessionEnd);
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
	
	private static boolean belongsToCourse(Index index, Course c) {
        for (Index i: c.getIndexList()) {
            if (index.getIndexNumber() == i.getIndexNumber())
                return true;
        }
        return false;
    }
	
	public static boolean willClash(Index newIndex, ArrayList<RegisteredCourse> regCourses) {
        for (RegisteredCourse c : regCourses) {
            if (indexClashes(newIndex, c.getIndex()) == true && !belongsToCourse(newIndex,c.getCourse())) {
                return true;
            }
        }
        return false;
    }
}
