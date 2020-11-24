/**
 * Manager for Staff
 * @version 1.0
 * @since 2020-11-19
 */

package control;

import java.util.*;

import entities.Course;
import entities.Index;
import entities.RegisteredCourse;
import entities.Session;
import entities.Staff;
import entities.Student;

public class StaffManager extends Manager {
	/**
	 * Stores which Staff is using the Manager
	 */
	private Staff user;
	
	/**
	 * Initializing the Staff
	 * @param user Staff
	 */
	public StaffManager(Staff user) {
		super();
		this.user = user;
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
		updateStudentDB(student);
	}	

	/**
	 * @param student Student
	 * @return Calendar Object with access start period
	 */
	public Calendar checkStudentAccessStart(Student student) {
		return student.getAccessStart();
	}
	
	/**
	 * @param student Student
	 * @return Calendar Object with access end period
	 */
	public Calendar checkStudentAccessEnd(Student student) {
		return student.getAccessEnd();
	}
	
	/**
	 * Updates the Course Code
	 * @param course Course
	 * @param courseCode Course Code
	 */
	public void updateCourseCode(Course course, String courseCode) {
		course.setCourseCode(courseCode);
		updateCourseDB(course);
	}
	
	/**
	 * Updates the Course Name
	 * @param course Course
	 * @param courseName Course Name
	 */
	public void updateCourseName(Course course, String courseName) {
		course.setCourseName(courseName);
		updateCourseDB(course);
	}
	
	/**
	 * Updates the Faculty of the Course
	 * @param course Course
	 * @param courseSchool Faculty
	 */
	public void updateCourseSchool(Course course, String courseSchool) {
		course.setSchool(courseSchool);
		updateCourseDB(course);
	}
	
	/**
	 * Adds Student
	 * @param student Student
	 */
	public void addStudent(Student student) {
		
		if (getStudentDB().containsKey(student.getMatricNumber())) {
			System.out.println("Student already exists");
			return;
		}
		
		updateStudentDB(student);
        
		printStudentList();
	}
	

	/**
	 * Adds Course
	 * @param courseCode Course Code
	 * @param courseName Course Name
	 * @param school Faculty
	 * @param AU Number of AUs
	 */
	public void addCourse(String courseCode, String courseName, String school, int AU) {
		if (getCourseDB().containsKey(courseCode)) {
			System.out.println("Course "+ courseCode +" already exists");
			return;
		}
		
		Course newCourse = new Course (courseCode, courseName, school, AU);
		ArrayList<Index> il = new ArrayList<Index>();
		newCourse.setIndexList(il);
		
        updateCourseDB(newCourse); 
		
		printCourseList();
	}
	
	/**
	 * Adds Student to the Index's ArrayList of Students and Waitlist
	 * @param indexNumber Index Number
	 * @param courseCode Course Code
	 */
	public void addStudentToIndex(int indexNumber, String courseCode) {
		Course c = findCourse(courseCode);
		Index index = findIndex(indexNumber);
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
												
						rc.setOnWaitlist(false);
						updateStudentDB(s);
						
						studentWaitlist.remove(s);
						index.setWaitList(studentWaitlist);
						
						studentlist.add(s);
						index.setStudentList(studentlist);
						
						NotifManager.sendEmail(s.getEmail(), s, courseCode, "Course " + courseCode + " has been successfully registered and you have been removed from the waitlist.");
												
						vacancy--;
					}
				}
						
		     }
		}
		
		index.setVacancies(vacancy);
		
		updateCourseDB(c);
	}
	
	
	/**
	 * Obtain Calendar Object by inputting time
	 * @param day Day
	 * @param hour Hour
	 * @param min Minute
	 * @param calendar Calendar
	 * @param sc Scanner
	 * @return Calendar Object using input day and time
	 */
	private static Calendar inputTime(int day, int hour, int min, Calendar calendar, Scanner sc) {
		try {			
			while (true) {
				// Input Hour
				System.out.print("Enter Hour (24H format): ");
				hour = sc.nextInt();
				if (hour < 0 || hour > 23) {
					System.out.println("Please input a valid time! Please try again.");
					continue;
				}
				// Input Minute
				System.out.print("Enter Minute: ");
				min = sc.nextInt();
				if (min < 0 || min > 59) {
					System.out.println("Please enter a valid time! Please try again.");
					continue;
				}
				
				calendar.set(Calendar.DAY_OF_WEEK, day + 1);
				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, min);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				return calendar;
			}
		}
		catch (Exception InputMismatchException) {
			System.out.println("Invalid input! Please try again.\n");
		}
		return calendar;
	}	
	
	/**
	 * Adds a Session
	 * @param sessionType Session Type (Tutorial/Lab)
	 * @param j Session number
	 * @param sc Scanner
	 * @return Session
	 */
	public static Session inputSession(String sessionType, int j, Scanner sc) {
		String venue;
		Calendar sessionStart = Calendar.getInstance(), sessionEnd = Calendar.getInstance();
		int sessionDay = -1, startHour = -1, startMin = -1, endHour = -1, endMin = -1;
		System.out.print("\nEnter Venue for " + sessionType + " Session " + j + ": ");
		sc.nextLine();
		venue = sc.nextLine();
		System.out.print("\nEnter Day of " + sessionType + " Session " + j + ": ");
		sessionDay = sc.nextInt();
		while (sessionDay < 1 || sessionDay > 7) {
			System.out.println("Please a valid day of the week! Please try again.");
			sessionDay = sc.nextInt();
		}
		System.out.println("Entering Session Start Time: ");
		sessionStart = inputTime(sessionDay, startHour, startMin, sessionStart, sc);
		System.out.println("Entering Session End Time: ");
		sessionEnd = inputTime(sessionDay, endHour, endMin, sessionEnd, sc);
		if (sessionEnd.compareTo(sessionStart) < 0) {
			System.out.println("Invalid Session period! Please try again.\n");
			sessionEnd = inputTime(sessionDay, endHour, endMin, sessionEnd, sc);
		}
		Session newSession = new Session(sessionType, venue, sessionStart, sessionEnd);
		return newSession;
	}
	
	
	/**
	 * Add Sessions for a new Index
	 * @param newIndex Index 
	 */
	public static void addIndex(Index newIndex) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Number of Lecture Sessions for Index " + newIndex.getIndexNumber() + ": ");
		int lectureCount = sc.nextInt();
		while (lectureCount < 1) {
			System.out.print("\nInvalid Entry. Lecture Session is Required. Please Enter Number of Lecture Sessions for Index " + newIndex.getIndexNumber() + ": ");
			lectureCount = sc.nextInt();											
		}
		System.out.print("\nEnter Number of Tutorial Sessions for Index " + newIndex.getIndexNumber() + ": ");
		int tutorialCount = sc.nextInt();
		System.out.print("\nEnter Number of Laboratory Sessions for Index " + newIndex.getIndexNumber() + ": ");
		int laboratoryCount = sc.nextInt();
		for (int j = 1; j <= lectureCount; j++) {
			Session newSession = StaffManager.inputSession("LEC", j, sc);
			newIndex.addSessionList(newSession);
		}
		for (int j = 1; j <= tutorialCount; j++) {
			Session newSession = StaffManager.inputSession("TUT", j, sc);
			newIndex.addSessionList(newSession);
		}
		for (int j = 1; j <= laboratoryCount; j++) {
			Session newSession = StaffManager.inputSession("LAB", j, sc);
			newIndex.addSessionList(newSession);
		}
	}
	
}
