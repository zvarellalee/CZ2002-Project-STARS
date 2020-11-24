/**
 * Parent Class of StaffManager and StudentManager
 * @version 1.0
 * @since 2020-11-20
 */
package control;

import java.util.ArrayList;
import entities.*;

public class Manager {
	/**
	 * Constructor method for manager, initializes the data objects via FileManager read method
	 */
	public Manager() {
		new Database();
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
	
	/**
	 * Obtain the Course object from the entered Index 
	 * @param index Object
	 * @return Course Object
	 */
	public static Course getCourseFromIndex(Index index) {
		Course course = null;
		for (Course c : Database.getCourseDB().values()) {
			if (c.getIndexList().contains(index)) {
				course = c;
			}
		}
		return course;
	}
	
	/**
	 * Overloaded method - utilises the index number
	 * @param indexNumber Index Number
	 * @return Course Object
	 */
	public static Course getCourseFromIndex(int indexNumber) {
		Course course = null;
		Index index = Database.findIndex(indexNumber);
		for (Course c : Database.getCourseDB().values()) {
			if (c.getIndexList().contains(index)) {
				course = c;
			}
		}
		return course;
	}
	
	/**
	 * Obtain the Student object from the entered matriculation number
	 * @param matricNumber Matriculation Number
	 * @return Student Object
	 */
	public static Student getStudentFromMatricNumber(String matricNumber) {
		Student student = null;
		if (Database.getStudentDB().containsKey(matricNumber)) {
			student = Database.getStudentDB().get(matricNumber);
		}
		return student;
	}
}	
