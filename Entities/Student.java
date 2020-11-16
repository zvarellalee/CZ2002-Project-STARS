package Entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Student extends User {
	private String matricNumber; 					// student's matric number, gender and nationality
	private String gender;
	private String nationality;	
	private Calendar accessStart; 					// start and end of STARS access period
	private Calendar accessEnd;
	private ArrayList<RegisteredCourse> courseList; // list of courses Student is registered for
	
	// ------ constructor ------
	public Student(String username, String password, boolean adminAccess, String firstName, String lastName, String salt, String email,
					String matricNumber, String gender, String nationality) {
		super(username, password, adminAccess, firstName, lastName, salt, email);
		this.matricNumber = matricNumber;
		this.gender = gender;
		this.nationality = nationality;
		/*Calendar cal = Calendar.getInstance();
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
		this.accessEnd = cal2;*/
		this.accessStart = accessStart;
		this.accessEnd = accessEnd;
		courseList = new ArrayList<RegisteredCourse>();
	}
	public Student(String username, String password, boolean adminAccess, String firstName, String lastName, String salt, String email,
					String matricNumber, String gender, String nationality, 
					int startYear, int startMth, int startDay, int startHour, int startMin,
					int endMth, int endDay, int endHour, int endMin) {
		super(username, password, adminAccess, firstName, lastName, salt, email);
		this.matricNumber = matricNumber;
		this.gender = gender;
		this.nationality = nationality;
		/*Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,startYear);
		cal.set(Calendar.MONTH,startMth);
		cal.set(Calendar.DAY_OF_MONTH,startDay);
		cal.set(Calendar.HOUR_OF_DAY,startHour);
		cal.set(Calendar.MINUTE,startMin);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(cal.getTime());
		cal.set(Calendar.MONTH,endMth);
		cal.set(Calendar.DAY_OF_MONTH,endDay);
		cal2.set(Calendar.HOUR_OF_DAY,endHour);
		cal2.set(Calendar.MINUTE,endMin);
		this.accessStart = cal;
		this.accessEnd = cal2;*/
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
	
	public void setAccessStart(Calendar accessStart) {
		this.accessStart = accessStart;
	}
	
	public void setAccessEnd(Calendar accessEnd) {
		this.accessEnd = accessEnd;
	}
	
	// Not sure if we need this? Same for setCourseList()
	/*public void setCourseList(ArrayList<RegisteredCourse> courseList) {
		this.courseList = courseList;
	}*/
	
	// To test, delete later
	/*public static void main(String[] args) {
		Student stud = new Student("gay", "burger", "lol");
		Student stud2 = new Student("noob", "cow", "eat", 2020, 9, 10, 3, 5, 9, 13, 4, 30);
		Calendar cal = Calendar.getInstance();
		System.out.println(stud.getAccessStart().getTime());
		System.out.println(stud.getAccessEnd().getTime());
		System.out.println(stud2.getAccessStart().getTime());
		System.out.println(stud2.getAccessEnd().getTime());
		System.out.println(cal.getTime());
	}*/
}
