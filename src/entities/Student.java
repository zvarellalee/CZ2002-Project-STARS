/**
 * Student entity class for storing relevant student information
 * @version 1.0
 * @since 2020-11-20
 */
package entities;

import java.util.ArrayList;
import java.util.Calendar;

public class Student extends User {
	/** This is the student's matriculation number identifier */
	private String matricNumber; 					// student's matric number, gender and nationality
	/** Gender of student */
	private String gender;
	/** Nationality of student */
	private String nationality;	
	/** Store the remaining AUs of the student to update when the student adds/drops a course */
	private int numAU;	
	/** Start of STARS access period */
	private Calendar accessStart; 		
	/** End of STARS access period */
	private Calendar accessEnd;
	/** list of courses Student is enrolled in or on waiting list for */
	private ArrayList<RegisteredCourse> courseList; 
	
	/**
	 * Constructor method for Student
	 * @param String username
	 * @param String password
	 * @param boolean adminAccess
	 * @param String firstName
	 * @param String lastName
	 * @param String email
	 * @param String matricNumber
	 * @param String gender
	 * @param String nationality
	 * @param numAU
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
	
	/**
	 * Accessor method for matriculation number
	 * @return String matricNumber
	 */
	public String getMatricNumber() {
		return matricNumber;
	}
	
	/**
	 * Accessor method for gender
	 * @return String gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Accessor method for nationality
	 * @return String nationality
	 */
	public String getNationality() {
		return nationality;
	}
	
	/** 
	 * Accessor method for access start date
	 * @return Calendar accessStart
	 */
	public Calendar getAccessStart() {
		return accessStart;
	}
	
	/**
	 * Accessor method for access end date
	 * @return Calendar accessEnd
	 */
	public Calendar getAccessEnd() {
		return accessEnd;
	}
	
	/** 
	 * Accessor method for course list
	 * @return ArrayList courseList
	 */
	public ArrayList<RegisteredCourse> getCourseList() {
		return courseList;
	}
	
	/**
	 * Accessor method for number of AUs
	 * @return int numAU
	 */
	public int getNumAU() {
		return numAU;
	}
	
	/**
	 * Mutator method for matriculation number
	 * @param String matricNumber
	 */
	public void setMatricNumber(String matricNumber) {
		this.matricNumber = matricNumber;
	}
	
	/**
	 * Mutator method for gender
	 * @param String gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * Mutator method for nationality
	 * @param String nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/**
	 * Mutator method for access start date
	 * @param Calendar accessStart
	 */
	public void setAccessStart(Calendar accessStart) {
		this.accessStart = accessStart;
	}
	
	/**
	 * Mutator method for access end date
	 * @param Calendar accessEnd
	 */
	public void setAccessEnd(Calendar accessEnd) {
		this.accessEnd = accessEnd;
	}
	
	/**
	 * Mutator method for setting course list
	 * @param ArrayList courseList
	 */
	public void setCourseList(ArrayList<RegisteredCourse> courseList) {
		this.courseList = courseList;
	}
	/**
	 * Mutator method for setting number of AUs
	 * @param int au
	 */
	public void setNumAU(int au) {
		this.numAU = au;
	}
	
}
