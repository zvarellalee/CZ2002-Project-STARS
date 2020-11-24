package control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entities.*;

public class PrintManager {
	/**
	 * Prints out the List of Students with their Matriculation Number and Full Name
	 */
	public static void printStudentList() {
		System.out.println("\n================================================================");
		System.out.printf("%-25s%-25s\n", "Matriculation Number", "Full Name");
		System.out.println("================================================================");
		for (Student student : Database.getStudentDB().values()){
			System.out.printf("%-25s%-25s\n", student.getMatricNumber(), student.getFirstName() + " " + student.getLastName());		
		}
	}
	
	
	/**
	 * Prints the List of Students enrolled in the Course
	 * Displays the Name, Gender and Nationality of the Students
	 * @param courseCode Course Code
	 */
	public static void printStudentList(String courseCode) {
		Course course = Database.findCourse(courseCode);
		if (course == null)
			return;
	
		System.out.println("Course Code: " + courseCode);
		System.out.println("================================================================");
		System.out.printf("%-20s%-20s%-20s\n", "Name", "Gender", "Nationality");
		System.out.println("================================================================");
		
		
		ArrayList<Index> courseIndex = course.getIndexList();
		
		for (Index index : courseIndex) {
			for (Student student : index.getStudentList()) {
				System.out.printf("%-20s%-20s%-20s\n", student.getFirstName() + " " + student.getLastName(), student.getGender(), student.getNationality());
			}
		}
		System.out.println();
	}
	
	/**
	 * Prints the List of Students enrolled in the Index
	 * Displays the Name, Gender and Nationality of the Students
	 * @param indexNumber Index Number
	 */
	public static void printStudentList(int indexNumber) {
		Index index = Database.findIndex(indexNumber);
		if (index == null)
			return;
		System.out.println("Index Number: " + indexNumber);
		System.out.println("================================================================");
		System.out.printf("%-20s%-20s%-20s\n", "Name", "Gender", "Nationality");
		System.out.println("================================================================");
		
		for (Student student : index.getStudentList()) {
			System.out.printf("%-20s%-20s%-20s\n", student.getFirstName() + " " + student.getLastName(), student.getGender(), student.getNationality());
		}
		System.out.println();
	}
	
	/**
	 * Prints the List of Courses with Course Code and Name
	 */
	public static void printCourseList() {
		System.out.println("================================================================");
		System.out.println("Course Code\tCourse Name");
		System.out.println("================================================================");
		for (Course course : Database.getCourseDB().values()){
			System.out.println(course.getCourseCode() + "\t\t" + course.getCourseName());
		}
		System.out.println("");
	}
	
	/**
	 * Print the index list of a course
	 */
	public static void printIndexList(Course course) {
		System.out.println("Course Code: " + course.getCourseCode());
		System.out.println("Course Name: " + course.getCourseName());
		System.out.println("================================================================");
		System.out.println("Indexes");
		System.out.println("================================================================");
		for (Index i: course.getIndexList()) {
			System.out.println(i.getIndexNumber());
		}
	}
	
	/**
	 * Prints the List of Sessions in the Index
	 * @param index Object
	 */
	public static void printSessions (Index index ){
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
	 * Prints student's list of registered courses
	 * @param user User
	 */
	public static void printRegistered(Student user) {
		// print course code, course name, index of registered courses
		System.out.println("Courses Registered for " + user.getFirstName() + " " + user.getLastName() + " (" + user.getMatricNumber() + "):");
		System.out.println("==============================================================================================================================================");
		System.out.println("Course Code\tCourse Name\t\t\t\tIndex\tAU\tStatus\t\tSession Type\tDay\tTime\t\tVenue");
		System.out.println("==============================================================================================================================================");
		for(RegisteredCourse course: user.getCourseList()) {
			String status = course.getOnWaitlist() ?  "On Waitlist": "Registered";
			String line = course.getCourse().getCourseCode() + "\t\t" + String.format("%-36.36s", course.getCourse().getCourseName()) + "\t" + course.getIndex().getIndexNumber() + "\t" + course.getCourse().getAU() + "\t" + status + "\t";
			System.out.print(line);
			int whitespaceSize = line.length() -1;
			String whitespace = " ";
			whitespace = String.format("%1$" + whitespaceSize + "s", whitespace);
			SimpleDateFormat sdf = new SimpleDateFormat("EEE");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
			for (Session session: course.getIndex().getSessionList())
			{
				System.out.println(session.getSessionType() + "\t\t" + sdf.format(session.getSessionStart().getTime()) + "\t" + sdf2.format(session.getSessionStart().getTime()) + "-" + sdf2.format(session.getSessionEnd().getTime()) + "\t" + session.getVenue());
				System.out.print(whitespace + "\t\t\t\t");
			}
			System.out.println("");
		} 
		System.out.println("Total number of AUs registered (including on waitlist): " + user.getNumAU());
	}
}
