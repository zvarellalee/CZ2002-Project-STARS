/**
 * Manager for Staff
 * @version 1.0
 * @since 2020-11-19
 */

package control;

import java.util.*;

import entities.*;

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
		Database.updateStudentDB(student);
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
	 * Update Course
	 * @param courseCode Course Code
	 * @param courseName Course Name
	 * @param school Faculty
	 */
	public void updateCourse(String courseCode, String courseName, String school) {
		Course course = Database.findCourse(courseCode);
		course.setCourseName(courseName);
		course.setSchool(school);
		Database.updateCourseDB(course);
	}
	
	/**
	 * Adds Student
	 * @param student Student
	 */
	public void addStudent(String username, String password, String firstName,
			String lastName, String email, String matricNumber, String gender, String nationality) {
		
		Student newStudent = new Student(username, password, false, firstName, 
				lastName, email, matricNumber, gender, nationality, 0);
		
		if (Database.getStudentDB().containsKey(newStudent.getMatricNumber())) {
			System.out.println("Student already exists");
			return;
		}
		
		Database.updateStudentDB(newStudent);
        
		PrintManager.printStudentList();
	}
	

	/**
	 * Adds Course
	 * @param courseCode Course Code
	 * @param courseName Course Name
	 * @param school Faculty
	 * @param AU Number of AUs
	 */
	public void addCourse(String courseCode, String courseName, String school, int AU) {
		if (Database.getCourseDB().containsKey(courseCode)) {
			System.out.println("Course "+ courseCode +" already exists");
			return;
		}
		
		Course newCourse = new Course (courseCode, courseName, school, AU);
		ArrayList<Index> il = new ArrayList<Index>();
		newCourse.setIndexList(il);
		
        	Database.updateCourseDB(newCourse); 
		
		PrintManager.printCourseList();
	}
	
	/**
	 * Adds the Index to the specific Course
	 * @param courseCode Course Code
	 * @param index Index ID
	 * @param vacancies Number of vacancies 
	 */
	public void addIndex(String courseCode, int index, int vacancies) {
		Course newCourse = Database.findCourse(courseCode);
		// Adding of Index
		Index newIndex = new Index(index, vacancies);
		newCourse.addIndex(newIndex);
		StaffManager.addSession(newIndex);
		Database.updateCourseDB(newCourse);
	}
	
	/**
	 * Updates the index parameters for a given Index
	 * @param courseCode Course Code
	 */
	public void updateIndex(int indexNumber, int vacancies) {
		Index index = Database.findIndex(indexNumber);
		// Adding of Index
		index.setVacancies(vacancies);
		Course course = getCourseFromIndex(indexNumber);
		for (Index i: course.getIndexList()) {
			if (i.getIndexNumber() == indexNumber)
				i = index;
				break;
		}
		Database.updateCourseDB(course);
	}
	
	/**
	 * Add Sessions for a new Index
	 * @param newIndex Index 
	 */
	public static void addSession(Index newIndex) {
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
	
	/**
	 * Adds students on the waitlist to any vacancies in the index
	 * @param indexNumber Index Number
	 * @parm courseCode Course Code
	 */
	public void addStudentsToIndex(int indexNumber, String courseCode) {
		Course c = Database.findCourse(courseCode);
		Index index = Database.findIndex(indexNumber);
		int vacancies = index.getVacancies();
		ArrayList<Student> studentWaitlist = index.getWaitList();
		ArrayList<Student> studentlist = index.getStudentList();
		
		
		if (vacancies <= 0) { // if there are no vacancies
			return;
		}
		
		if (studentWaitlist.size() == 0) { // if there are no students on the waitlist
			return;
		}
		for (int i=0; i<Math.min(vacancies,studentWaitlist.size());i++) {
			Student waiting = index.getWaitList().get(0);
			ArrayList<RegisteredCourse> newCourseList = waiting.getCourseList();
			RegisteredCourse newCourse = new RegisteredCourse(false, c, index, waiting);
			for (RegisteredCourse old : newCourseList) {
				if (old.getIndex().getIndexNumber() == indexNumber) {
					newCourseList.remove(old);
					newCourseList.add(newCourse);
					break;
				}
			}
			waiting.setCourseList(newCourseList);
			index.addStudentList(waiting);
			index.removeWaitList(waiting.getMatricNumber());
			vacancies--;
			index.setVacancies(vacancies);

			// Update Student Database
			Database.updateStudentDB(waiting);
			
			// Notify enrolled student
			System.out.println("Student " + waiting.getMatricNumber() + " has filled the new vacancy. Notifying student...");
			NotifManager.sendEmail(waiting.getEmail(), waiting, courseCode, "Course " + courseCode + " has been successfully registered and you have been removed from the waitlist.");
		}	
		Database.updateCourseDB(c);
	}

	/**
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
		sessionDay = sc.next().charAt(0);
		while (!('0'>=sessionDay ||sessionDay<='7')) {
			System.out.println("Please enter a valid day of the week! Please try again.");
			sessionDay = sc.next().charAt(0);
		}
		int sessionDayInt = Integer.parseInt(String.valueOf(sessionDay));
		System.out.println("Entering Session Start Time: ");
		sessionStart = inputTime(sessionDayInt, startHour, startMin, sessionStart, sc);
		System.out.println("Entering Session End Time: ");
		sessionEnd = inputTime(sessionDayInt, endHour, endMin, sessionEnd, sc);
		if (sessionEnd.compareTo(sessionStart) < 0) {
			System.out.println("Invalid Session period! Please try again.\n");
			sessionEnd = inputTime(sessionDayInt, endHour, endMin, sessionEnd, sc);
		}
		Session newSession = new Session(sessionType, venue, sessionStart, sessionEnd);
		return newSession;
	}
	
	
	
}
