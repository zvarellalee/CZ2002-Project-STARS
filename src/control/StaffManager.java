/**
 * 
 * @author 
 * @version 1.0
 * @since
 */

package control;

import java.util.*;
import database.Database;
import entities.Course;
import entities.Index;
import entities.RegisteredCourse;
import entities.Staff;
import entities.Student;

public class StaffManager extends Manager{
	/**
	 * Stores which Staff is using the Manager
	 */
	private Staff user;
	
	/**
	 * Initializing the Staff
	 * @param user Staff
	 */
	public StaffManager(Staff user) {
		this.user = user;
		// Database.initialise(); // To test out the UI
	}
	
	/**
	 * Edits the Access Period of the Input Student
	 * @param accessStart Calendar Access Start Period
	 * @param accessEnd Calendar Access End Period
	 * @param student Student
	 */
	public void editStudentAccessPeriod(Calendar accessStart, Calendar accessEnd, Student student) {
		student.setAccessStart(accessStart);
		student.setAccessEnd(accessEnd);
	}
	
	/**
	 * Updates the Course Code
	 * @param course Course
	 * @param courseCode Course Code
	 */
	public void updateCourseCode(Course course, String courseCode) {
		course.setCourseCode(courseCode);
	}
	
	/**
	 * Updates the Course Name
	 * @param course Course
	 * @param courseName Course Name
	 */
	public void updateCourseName(Course course, String courseName) {
		course.setCourseName(courseName);
	}
	
	/**
	 * Updates the Faculty of the Course
	 * @param course Course
	 * @param courseSchool Faculty
	 */
	public void updateCourseSchool(Course course, String courseSchool) {
		course.setSchool(courseSchool);
	}
	
	/**
	 * Adds Student
	 * @param student Student
	 */
	public void addStudent(Student student) {
		
		for (Student s : Database.studentList) {
			if (s.getMatricNumber().equals(student.getMatricNumber())) {
				System.out.println("Student already exists");
				return;
			}
		}	
		
        	Database.studentList.add(student);
		printStudentList();
	}
	
	/**
	 * Prints out the List of Students with their Matriculation Number and Full Name
	 */
	private void printStudentList() {
		System.out.println("Matriculation Number         Full Name");
		System.out.println("---------------------------------------------------");
		for (Student student : Database.studentList){
			System.out.print(student.getMatricNumber() + "\t");
			System.out.println(student.getFirstName() + " " + student.getLastName()); 
			// decide how much to display
		}
	}
	

	/**
	 * Adds Course
	 * @param courseCode Course Code
	 * @param courseName Course Name
	 * @param school Faculty
	 * @param AU Number of AUs
	 */
	public void addCourse(String courseCode, String courseName, String school, int AU) {
		for (Course course : Database.courseList) {
			if (course.getCourseCode().equals(courseCode)) {
				System.out.println("Course "+ courseCode +" already exists");
				return;
			}
		}
		
		Course newCourse = new Course (courseCode, courseName, school, AU);
		ArrayList<Index> il = new ArrayList<Index>();
		newCourse.setIndexList(il);
		Database.courseList.add(newCourse); 
		printCourseList();
	}
	
	/**
	 * Prints the List of Courses with Course Code and Name
	 */
	public void printCourseList() {
		System.out.println("Course Code : Course Name");
		System.out.println("---------------------------------------------------");
		for (Course course : Database.courseList){
			System.out.println(course.getCourseCode() + ":" + course.getCourseName());
			
		}
	}
	
	/**
	 * Adds Student to the Index's ArrayList of Students and Waitlist
	 * @param indexNumber Index Number
	 */
	public void addStudentToIndex(int indexNumber) {
		Index index = findIndex( indexNumber);
		int vacancy = index.getVacancies();
		ArrayList<Student> studentWaitlist = index.getWaitList();
		ArrayList<Student> studentlist = index.getStudentList();
		
		if (vacancy <= 0) {
			System.out.println("No vacancies");
			return;
		}
		
		for (Student s : studentWaitlist) {
			if(vacancy > 0 || !studentWaitlist.isEmpty()) {
				ArrayList<RegisteredCourse> registeredCourseList = s.getCourseList();
				for ( RegisteredCourse rc : registeredCourseList) {
					if (rc.getIndex().equals(index)) {
						Database.studentList.add(s);
						rc.setOnWaitlist(false);
						studentWaitlist.remove(s);
						vacancy--;
					}
				}
						
		     }
		}
		
		index.setVacancies(vacancy);
			
	}
	
	
	/**
	 * Prints the List of Students enrolled in the Course
	 * Displays the Name, Gender and Nationality of the Students
	 * @param courseCode Course Code
	 */
	public void printStudentList(String courseCode) {
		Course course = findCourse(courseCode);
		if (course == null)
			return;
	
		System.out.println("Course Code: " + courseCode);
		System.out.println("Name\tGender\tNationality");
		
		ArrayList<Index> courseIndex = course.getIndexList();
		
		for (Index index : courseIndex) {
			for (Student student : index.getStudentList()) {
				System.out.print(student.getFirstName() + " " + student.getLastName() + ",\t"
								+ student.getGender() + ",\t" + student.getNationality());
			}
		}
		System.out.println();
	}	
	
	/**
	 * Prints the List of Students enrolled in the Index
	 * Displays the Name, Gender and Nationality of the Students
	 * @param indexNumber Index Number
	 */
	public void printStudentList(int indexNumber) {
		Index index = findIndex(indexNumber);
		if (index == null)
			return;
		
		System.out.println("Index Number: " + indexNumber);
		System.out.println("Name\tGender\tNationality");
		
		for (Student student : index.getStudentList()) {
			System.out.print(student.getFirstName() + " " + student.getLastName() + ",\t"
							+ student.getGender() + ",\t" + student.getNationality());
		}
		System.out.println();
	}
	
	/**
	/**
	 * Finds the Course Object using the Course Code
	 * @param courseCode Course Code
	 * @return Course Object
	 
	public Course findCourse(String courseCode) {
		for (Course c : Database.courseList) {
			if (c.getCourseCode().equals(courseCode)) {
				return c;
			}
		}
		System.out.println("\nInvalid Course Code! Please try again.");
		System.out.println("");
		return null;
	}
	
	/**
	 * Finds the Index Object using the Index Number
	 * @param Index Index Number
	 * @return Index Object
	 
	public Index findIndex(int index) {
		// Finds the Index object from index number
		for (Course c : Database.courseList) {
			for (Index i : c.getIndexList())
				if (i.getIndexNumber() == index) {
					return i;
				}
		}
		System.out.println("\nInvalid Index! Please try again.");
		System.out.println("");
		return null;
	}
	*/
}
