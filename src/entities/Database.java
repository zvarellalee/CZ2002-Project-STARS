/**
 * Stores the hashmaps of objects retrieved from file
 * @version 1.0
 * @since 2020-11-20
 */
package entities;

import java.util.HashMap;

import control.FileManager;

public class Database {
	/**
	 * Store the data objects in HashMap
	 */
	private static HashMap<String,Course> courseMap;
	private static HashMap<String,Student> studentMap;
	private static HashMap<String,Staff> staffMap;
	/**
	 * Constructor method for database, initializes the data objects via FileManager read method
	 */
	public Database() {
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
	public static Course findCourse(String courseCode) {
		if (courseMap.containsKey(courseCode.toUpperCase())) 
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
	public static Index findIndex(int index) {
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
	
	// mutator methods
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
