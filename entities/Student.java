package entities;

import java.util.ArrayList;
import java.util.Calendar;

public class Student extends User {
	private String matricNumber; 					// student's matric number, gender and nationality
	private String gender;
	private String nationality;	
	private int numAU;								//remaining AU, update whenever student adds/drops a course
	private Calendar accessStart; 					// start and end of STARS access period
	private Calendar accessEnd;
	private ArrayList<RegisteredCourse> courseList; // list of courses Student is registered for
	
	// ------ constructor ------
	public Student(String username, String password, boolean adminAccess, String firstName, String lastName, 
					String salt, String email, String matricNumber, String gender, String nationality, int numAU) {
		super(username, password, adminAccess, firstName, lastName, salt, email);
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
	public String getMatricNumber() {
		return matricNumber;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public Calendar getAccessStart() {
		return accessStart;
	}
	
	public Calendar getAccessEnd() {
		return accessEnd;
	}
	
	public ArrayList<RegisteredCourse> getCourseList() {
		return courseList;
	}
	public int getNumAU() {
		return numAU;
	}
	
	// ------ mutator methods ------
	public void setMatricNumber(String matricNumber) {
		this.matricNumber = matricNumber;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public void setAccessStart(Calendar accessStart) {
		this.accessStart = accessStart;
	}
	
	public void setAccessEnd(Calendar accessEnd) {
		this.accessEnd = accessEnd;
	}
	
	public void setCourseList(ArrayList<RegisteredCourse> courseList) {
		this.courseList = courseList;
	}
	
	public void setNumAU(int au) {
		this.numAU = au;
	}
	
}
