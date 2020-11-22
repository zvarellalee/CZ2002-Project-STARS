/**
 * Represents a student user that does not have 
 * administrative rights.
 * Each student can be enrolled into a 
 * specific number of courses with specified index. 
 * @version 1.0
 * @since 2020-11-20
 */
package entities;

import java.util.ArrayList;
import java.util.Calendar;

public class Student extends User {
	/** 
	 * This is the student's matriculation number 
	 * which identifies the Student 
	 */
	private String matricNumber; 					// student's matric number, gender and nationality
	/** 
	 * Gender of student 
	 */
	private String gender;
	/** 
	 * Nationality of student 
	 */
	private String nationality;	
	/** 
	 * Stores the total Number of Academic Units 
	 * registered by a Student including courses
	 * on wait list
	 */
	private int numAU;	
	/** 
	 * Start of STARS access period 
	 */
	private Calendar accessStart; 		
	/** 
	 * End of STARS access period 
	 */
	private Calendar accessEnd;
	/*
	 * List of registered courses that Student has 
	 * enrolled in or is on wait list for 
	 */
	private ArrayList<RegisteredCourse> courseList; 
	
	/**
	 * Initialising the Student
	 * @param username Username used to login into mySTARS
	 * @param password Password used to login into mySTARS
	 * @param adminAccess False as Student does not have administrative rights
	 * @param firstName First Name
	 * @param lastName Last Name
	 * @param email Email Address
	 * @param matricNumber Matriculation Number that identifies the Student
	 * @param gender Gender
	 * @param nationality Nationality
	 * @param numAU Total Number of Academic Units registered by the Student 
	 */
	public Student(String username, String password, boolean adminAccess, String firstName, String lastName, 
					 String email, String matricNumber, String gender, String nationality, int numAU) {
		super(username, password, adminAccess, firstName, lastName, email);
		this.matricNumber = matricNumber;
		this.gender = gender;
		this.nationality = nationality;
		this.numAU = numAU;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,2020);
		cal.set(Calendar.MONTH,8);
		cal.set(Calendar.DAY_OF_MONTH,11);
		cal.set(Calendar.HOUR_OF_DAY,14);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(cal.getTime());
		cal2.set(Calendar.HOUR_OF_DAY,22);
		cal.set(Calendar.DAY_OF_MONTH,14);
		this.accessStart = cal;
		this.accessEnd = cal2;
		courseList = new ArrayList<RegisteredCourse>();
	}
	
	// ------ accessor methods ------
	/**
	 * Gets the student's matriculation number
	 * @return matricNumber
	 */
	public String getMatricNumber() {
		return matricNumber;
	}
	
	/**
	 * Gets the student's gender
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Gets for student's nationality
	 * @return nationality
	 */
	public String getNationality() {
		return nationality;
	}
	
	/** 
	 *Gets the student's access start date
	 * @return accessStart
	 */
	public Calendar getAccessStart() {
		return accessStart;
	}
	
	/**
	 * Gets the student's access end date
	 * @return accessEnd
	 */
	public Calendar getAccessEnd() {
		return accessEnd;
	}
	
	/** 
	 * Gets the student's list of registered courses that the 
	 * student has enrolled in or is on wait list for
	 * @return Array List of Registered Courses
	 */
	public ArrayList<RegisteredCourse> getCourseList() {
		return courseList;
	}
	
	/**
	 * Get the number of Academic Units the student has
	 * registered for including courses on wait list
	 * @return numAU
	 */
	public int getNumAU() {
		return numAU;
	}
	
	
	// ------ mutator methods ------
	/**
	 * Sets the student's matriculation number
	 * @param matricNumber Student's matriculation number
	 */
	public void setMatricNumber(String matricNumber) {
		this.matricNumber = matricNumber;
	}
	
	/**
	 * Sets the student's gender
	 * @param gender Student's gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * Sets the student's nationality
	 * @param nationality Student's nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/**
	 * Sets the student's access start date
	 * @param accessStart Student's access start date
	 */
	public void setAccessStart(Calendar accessStart) {
		this.accessStart = accessStart;
	}
	
	/**
	 * Sets the student's access end date
	 * @param accessEnd Student's access end date
	 */
	public void setAccessEnd(Calendar accessEnd) {
		this.accessEnd = accessEnd;
	}
	
	/**
	 * Sets the list of registered courses that the student
	 * has enrolled in or is on waitlist for
	 * @param courseList Array List of Student's registered courses
	 */
	public void setCourseList(ArrayList<RegisteredCourse> courseList) {
		this.courseList = courseList;
	}
	
	/**
	 * Sets the number of Academic Units the student
	 * has registered for including courses on the 
	 * wait list
	 * @param au Number of Academic Units
	 */
	public void setNumAU(int au) {
		this.numAU = au;
	}
	
}
