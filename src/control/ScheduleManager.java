/**
 * Manager for checking clashes between different sessions in indexes.
 * @author Cheah Jing Feng, Zachary Varella Lee Zheyu, Stanley Ho
 * @version 1.0
 * @since 2020-11-20
 */
package control;

import java.util.ArrayList;
import java.util.Calendar;
import entities.Course;
import entities.Index;
import entities.RegisteredCourse;
import entities.Session;

public class ScheduleManager {
	/** 
	 * Compares whether the 2 selected sessions clashes
	 * @param sessionStart Start of 1st session
	 * @param sessionEnd End of 1st session
	 * @param session2Start Start of 2nd session
	 * @param session2End End of 2nd session
	 * @return true if clashes, false if no clash
	 */
	private static boolean isOverlapping(Calendar sessionStart, Calendar sessionEnd, Calendar session2Start, Calendar session2End) {
		return sessionStart.before(session2End) && session2Start.before(sessionEnd);
	}
	
	/**
	 * Check whether the 2 selected indexes clashes
	 * @param index1 1st Index
	 * @param index2 2nd Index
	 * @return true if clashes, false if no clash
	 */
	private static boolean indexClashes(Index index1, Index index2) {
		for (Session s : index1.getSessionList()) {
			for (Session t : index2.getSessionList()) {
				if (isOverlapping(s.getSessionStart(), s.getSessionEnd(), t.getSessionStart(), t.getSessionEnd()) == true)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks whether an Index belongs to a Course
	 * @param index Index
	 * @param c Course
	 * @return true if exists in the Course, false if does not exist
	 */
	private static boolean belongsToCourse(Index index, Course c) {
        for (Index i: c.getIndexList()) {
            if (index.getIndexNumber() == i.getIndexNumber())
                return true;
        }
        return false;
    }
	
	/**
	 * Checks if selected Index clash with current list of Registered Courses
	 * @param newIndex New Index
	 * @param regCourses Array List of Registered Courses
	 * @return true if selected Index clash with current registered courses, false if no clash
	 */
	public static boolean willClash(Index newIndex, ArrayList<RegisteredCourse> regCourses) {
        for (RegisteredCourse c : regCourses) {
            if (indexClashes(newIndex, c.getIndex()) == true && !belongsToCourse(newIndex,c.getCourse())) {
            	System.out.println("New Index clashes with " + c.getStudent().getMatricNumber() + "'s Registered Course " + c.getCourse().getCourseCode() + " of Index " + c.getIndex().getIndexNumber()+ ".");
                return true;
            }
        }
        return false;
    }
}
