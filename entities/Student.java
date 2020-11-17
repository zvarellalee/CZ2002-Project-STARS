package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Student extends User {
	private String matricNumber; 					// student's matric number, gender and nationality
	private String gender;
	private String nationality;
	private int numAU;						//remaining AU, update whenever student adds/drops a course
	private Calendar accessStart; 					// start and end of STARS access period
	private Calendar accessEnd;
	private ArrayList<RegisteredCourse> courseList; // list of courses Student is registered for
	
	// ------ constructor ------
	public Student(String username, String password, boolean adminAccess, String firstName, String lastName, String salt, String email,
					String matricNumber, String gender, String nationality, int numAU, Calendar accessStart, Calendar accessEnd) {
		super(username, password, adminAccess, firstName, lastName, salt, email);
		this.matricNumber = matricNumber;
		this.gender = gender;
		this.nationality = nationality;
		this.accessStart = accessStart;
		this.accessEnd = accessEnd;
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
	
	public int getNumAU() {
		return numAU;
	}
	
	public Calendar getAccessStart() {
		return accessStart;
	}
	
	public Calendar getAccessEnd() {
		return accessEnd;
	}
	
	// Not sure if we need this? Same for setCourseList()
	/*public ArrayList<RegisteredCourse> getCourseList() {
		return courseList;
	}*/
	
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
	
	public void setNumAU(int numAU) {
		this.numAU = numAU;
	}
	
	public void setAccessStart(Calendar accessStart) {
		this.accessStart = accessStart;
	}
	
	public void setAccessEnd(Calendar accessEnd) {
		this.accessEnd = accessEnd;
	}
	
	// Not sure if we need this? Same for getCourseList()
	/*public void setCourseList(ArrayList<RegisteredCourse> courseList) {
		this.courseList = courseList;
	}*/
	
	// To test, delete later
	/*public static void main(String[] args) {
		Student stud = new Student("test", "burger", "lol");
		Student stud2 = new Student("noob", "cow", "eat", 2020, 9, 10, 3, 5, 9, 13, 4, 30);
		Calendar cal = Calendar.getInstance();
		System.out.println(stud.getAccessStart().getTime());
		System.out.println(stud.getAccessEnd().getTime());
		System.out.println(stud2.getAccessStart().getTime());
		System.out.println(stud2.getAccessEnd().getTime());
		System.out.println(cal.getTime());
	}*/
}
