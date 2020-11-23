/**
 * Parent Class of StaffManager and StudentManager
 * @version 1.0
 * @since 2020-11-20
 */
package control;

import java.util.ArrayList;
import java.util.HashMap;
import entities.Course;
import entities.Index;
import entities.Staff;
import entities.Student;

public class Manager {
	/**
	 * Store the data objects in HashMap
	 */
	private static HashMap<String,Course> courseMap;
	private static HashMap<String,Student> studentMap;
	private static HashMap<String,Staff> staffMap;
	/**
	 * Constructor method for manager, initializes the data objects via FileManager read method
	 */
	public Manager() {
		courseMap = (HashMap<String, Course>) FileManager.read("course.dat");
		studentMap = (HashMap<String, Student>) FileManager.read("student.dat");
		staffMap = (HashMap<String, Staff>) FileManager.read("staff.dat");
	}
	// accessor methods
	/**
	 * Returns the course database
	 * @return courseMap HashMap
	 */
	public static HashMap<String,Course> getCourseDB() {
		return courseMap;
	}
	
	/**
	 * Returns the student database
	 * @return studentMap HashMap
	 */
	public static HashMap<String,Student> getStudentDB() {
		return studentMap;
	}
	
	/**
	 * Returns the staff database
	 * @return staffMap HashMap
	 */
	public static HashMap<String,Staff> getStaffDB() {
		return staffMap;
	}
	
	/**
	 * Finds the Course Object using the Course Code
	 * @param courseCode Course Code
	 * @return Course Object
	 */
	public Course findCourse(String courseCode) {
		if (courseExists(courseCode)) 
			return courseMap.get(courseCode);
		System.out.println("\nInvalid Course Code! Please try again.");
		System.out.println("");
		return null;
	}
	
	/**
	 * Finds the Index Object using the Index Number
	 * @param index Index Number
	 * @return Index Object
	 */
	public Index findIndex(int index) {
		// Finds the Index object from index number
		for (Course c : courseMap.values()) {
			for (Index i : c.getIndexList())
				if (i.getIndexNumber() == index) {
					return i;
				}
		}
		System.out.println("\nInvalid Index! Please try again.");
		System.out.println("");
		return null;
	}
	
	/**
	 * Obtain an array list containing all indexes from a particular course
	 * @param course Object
	 * @return courseIndexes ArrayList<Index> 
	 */
	private ArrayList<Index> getAllIndexesFromCourse(Course course) {
		ArrayList<Index> courseIndexes = new ArrayList<Index>();
		for (Index id: course.getIndexList()) {
			courseIndexes.add(id);
		}
		return courseIndexes;
	}
	
	/**
	 * Prints the List of Courses with Course Code and Name
	 */
	public void printCourseList() {
		System.out.println("\nCourse Code : Course Name");
		System.out.println("---------------------------------------------------");
		for (Course course : getCourseDB().values()){
			System.out.println(course.getCourseCode() + ":" + course.getCourseName());
			
		}
	}
	
	/**
	 * Check the number of vacancies for a particular course
	 * @param courseCode Course Code
	 * @return boolean courseExists
	 */
	public boolean checkVacancies(String courseCode) {
		// initialise course does not exist
		boolean courseExists = false;
		// find the course that student wants to check
		Course selectedCurrentCourse = findCourse(courseCode.toUpperCase());
		
		// if course code is found in database
		if (selectedCurrentCourse != null) {
			courseExists = true;
			// get all indexes from course
			ArrayList<Index> courseIndexes = getAllIndexesFromCourse(selectedCurrentCourse);
			
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
		for (Course c : courseMap.values()) {
			for (Index i : c.getIndexList())
				if (i.getIndexNumber() == index) {
					return true;
				}
		}
		return false;
	}
	
	
	public static boolean courseExists(String course) {
		if (courseMap.containsKey(course))
				return true;
		else
			return false;
	}
	
	/**
	 * Updates a student in studentMap and writes to file
	 * @param student Student to be added/overwritten
	 */
	public static void updateStudentDB(Student student) {
		studentMap.put(student.getMatricNumber(), student);
		FileManager.write("student.dat", studentMap);
	}
	
	/**
	 * Updates a course in courseMap and writes to file
	 * @param course Course to be added/overwritten
	 */
	public static void updateCourseDB(Course course) {
		courseMap.put(course.getCourseCode(), course);
		FileManager.write("course.dat", courseMap);
	}
	
	/**
	 * Updates a staff in staffMap and writes to file
	 * @param staff Staff to be added/overwritten
	 */
	public static void updateStaffDB(Staff staff) {
		staffMap.put(staff.getStaffID(), staff);
		FileManager.write("staff.dat", staffMap);
	}
}
