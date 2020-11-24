/**
 * Parent Class of StaffManager and StudentManager
 * @version 1.0
 * @since 2020-11-20
 */
package control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import entities.*;

public class Manager {
	/**
	 * Store the data objects in HashMap
	 */
	private static Database db;
	/**
	 * Constructor method for manager, initializes the data objects via FileManager read method
	 */
	public Manager() {
		db = new Database();
	}
	
	/**
	 * Prints the List of Sessions in the Index
	 * @param index Object
	 */
	public void printSessions (Index index ){
		for (Session s: index.getSessionList()) {
			System.out.println("\nSession Type: " + s.getSessionType());
			System.out.println("Venue: " + s.getVenue());
			SimpleDateFormat sdf = new SimpleDateFormat("EEE");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
			System.out.println("Day: " + sdf.format(s.getSessionStart().getTime()));
			System.out.println("Start Time: " + sdf2.format(s.getSessionStart().getTime()));
			System.out.println("End Time: " + sdf2.format(s.getSessionEnd().getTime()));
		}
	}
	
	/**
	 * Check the number of vacancies for a particular course per index
	 * @param courseCode Course Code
	 * @return boolean courseExists
	 */
	public boolean checkVacancies(String courseCode) {
		// initialise course does not exist
		boolean courseExists = false;
		// find the course that student wants to check
		Course selectedCurrentCourse = Database.findCourse(courseCode);
		
		// if course code is found in database
		if (selectedCurrentCourse != null) {
			courseExists = true;
			// get all indexes from course
			ArrayList<Index> courseIndexes = selectedCurrentCourse.getIndexList();
			
			System.out.println("\nCourse Code: " + selectedCurrentCourse.getCourseCode());
			System.out.println("Course Name: " + selectedCurrentCourse.getCourseName());

			// print all the indexes and their respective vacancies and waitLists
			System.out.println("================================================================");
			System.out.println("Index\t\tVacancy\t\tWaitlist");
			System.out.println("================================================================");
			for (Index index: courseIndexes) {
				System.out.println(index.getIndexNumber() + "\t\t" + index.getVacancies() + "\t\t" + index.getWaitListSize());
			}
			System.out.println("");
		}
		return courseExists;
	}
	
	/**
	 * Checks whether the index exists
	 * @param index Index Number
	 * @return boolean
	 */
	public static boolean indexExists(int index) {
		for (Course c : Database.getCourseDB().values()) {
			for (Index i : c.getIndexList())
				if (i.getIndexNumber() == index) {
					return true;
				}
		}
		return false;
	}
	
	
	/**
	 * Checks whether the course exists
	 * @param course Course
	 * @return boolean
	 */
	public static boolean courseExists(String course) {
		if (Database.getCourseDB().containsKey(course))
				return true;
		else
			return false;
	}
}	
