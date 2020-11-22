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
		FileManager.updateStudentDB(student);
	}
	
	/**
	 * Updates the Course Code
	 * @param course Course
	 * @param courseCode Course Code
	 */
	public void updateCourseCode(Course course, String courseCode) {
		course.setCourseCode(courseCode);
		FileManager.updateCourseDB(course);
	}
	
	/**
	 * Updates the Course Name
	 * @param course Course
	 * @param courseName Course Name
	 */
	public void updateCourseName(Course course, String courseName) {
		course.setCourseName(courseName);
		FileManager.updateCourseDB(course);
	}
	
	/**
	 * Updates the Faculty of the Course
	 * @param course Course
	 * @param courseSchool Faculty
	 */
	public void updateCourseSchool(Course course, String courseSchool) {
		course.setSchool(courseSchool);
		FileManager.updateCourseDB(course);
	}
	
	/**
	 * Adds Student
	 * @param student Student
	 */
	public void addStudent(Student student) {
		
		for (Student s : FileManager.getStudentDB()) {
			if (s.getMatricNumber().equals(student.getMatricNumber())) {
				System.out.println("Student already exists");
				return;
			}
		}	
		
		List studentList;
		studentList = FileManager.getStudentDB();
		studentList.add(student);
       	        FileManager.write("student.dat" , studentList);
        
		printStudentList();
	}
	
	/**
	 * Prints out the List of Students with their Matriculation Number and Full Name
	 */
	private void printStudentList() {
		System.out.println("Matriculation Number         Full Name");
		System.out.println("---------------------------------------------------");
		for (Student student : FileManager.getStudentDB()){
			System.out.print(student.getMatricNumber() + "\t");
			System.out.println(student.getFirstName() + " " + student.getLastName()); 		
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
		for (Course course : FileManager.getCourseDB()) {
			if (course.getCourseCode().equals(courseCode)) {
				System.out.println("Course "+ courseCode +" already exists");
				return;
			}
		}
		
		Course newCourse = new Course (courseCode, courseName, school, AU);
		ArrayList<Index> il = new ArrayList<Index>();
		newCourse.setIndexList(il);
		
        	List courseList;
		courseList = FileManager.getCourseDB();
		courseList.add(newCourse);
        	FileManager.write("course.dat" , courseList);     
		
		printCourseList();
	}
	
	/**
	 * Prints the List of Courses with Course Code and Name
	 */
	public void printCourseList() {
		System.out.println("Course Code : Course Name");
		System.out.println("---------------------------------------------------");
		for (Course course : FileManager.getCourseDB()){
			System.out.println(course.getCourseCode() + ":" + course.getCourseName());
			
		}
	}
	
	/**
	 * Adds Student to the Index's ArrayList of Students and Waitlist
	 * @param indexNumber Index Number
	 */
	public void addStudentToIndex(int indexNumber) {
		Course c = null;
		Index index = findIndex(indexNumber);// might change
		int vacancy = index.getVacancies();
		ArrayList<Student> studentWaitlist = index.getWaitList();
		ArrayList<Student> studentlist = index.getStudentList();
		
		
		if (vacancy <= 0) {
			System.out.println("No vacancies");
			return;
		}
		
		for (Student s : studentWaitlist) {
			if(vacancy > 0 && !studentWaitlist.isEmpty()) {
				ArrayList<RegisteredCourse> registeredCourseList = s.getCourseList();
				for ( RegisteredCourse rc : registeredCourseList) {
					if (rc.getIndex().equals(index)) {
						
						c = rc.getCourse(); // might change
						
						rc.setOnWaitlist(false);
						FileManager.updateStudentDB(s);
						
						studentWaitlist.remove(s);
						index.setWaitList(studentWaitlist);
						
						studentlist.add(s);
						index.setStudentList(studentlist);
						
						vacancy--;
					}
				}
						
		     }
		}
		
		index.setVacancies(vacancy);
		
		FileManager.updateCourseDB(c);
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
	
}
