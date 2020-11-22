/**
 * Parent Class of StaffManager and StudentManager
 * @version 1.0
 * @since 2020-11-20
 */
package control;

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
	 * @return HashMap courseMap
	 */
	public static HashMap<String,Course> getCourseDB() {
		return courseMap;
	}
	
	/**
	 * Returns the student database
	 * @return HashMap studentMap
	 */
	public static HashMap<String,Student> getStudentDB() {
		return studentMap;
	}
	
	/**
	 * Returns the staff database
	 * @return HashMap staffMap
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
		if (courseMap.containsKey(courseCode)) return courseMap.get(courseCode);
		System.out.println("\nInvalid Course Code! Please try again.");
		System.out.println("");
		return null;
	}
	
	/**
	 * Finds the Index Object using the Index Number
	 * @param Index Index Number
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
	 * Updates a student in studentMap and writes to file
	 * @param Student student
	 */
	public static void updateStudentDB(Student student) {
		studentMap.put(student.getMatricNumber(), student);
		FileManager.write("student.dat", studentMap);
	}
	
	/**
	 * Updates a course in courseMap and writes to file
	 * @param Course course
	 */
	public static void updateCourseDB(Course course) {
		courseMap.put(course.getCourseCode(), course);
		FileManager.write("course.dat", courseMap);
	}
	
	/**
	 * Updates a staff in staffMap and writes to file
	 * @param Staff staff
	 */
	public static void updateStaffDB(Staff staff) {
		staffMap.put(staff.getStaffID(), staff);
		FileManager.write("staff.dat", staffMap);
	}
}
