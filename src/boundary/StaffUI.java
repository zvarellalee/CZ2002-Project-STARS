/**
 * User Interface for Staff
 * @author Cheah Jing Feng
 * @version 1.0
 * @since 2020-11-20
 */

package boundary;

import java.util.Calendar;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import database.Database;
import entities.Course;
import entities.Index;
import entities.Session;
import entities.Staff;
import entities.Student;
import control.Manager;
import control.StaffManager;

public class StaffUI implements UserUI {
	
	/**
	 * Stores which Staff is using the UI
	 */
	private static Staff user;
	
	/**
	 * Gets the staff object
	 * @return Staff object
	 */
	public Staff getStaff() {
		return StaffUI.user;
	}
	
	/**
	 * Sets the current user to input Staff
	 * @param user Staff
	 */
	public void setStaff(Staff user) {
		StaffUI.user = user;
	}

	/**
	 * Displays the Staff User Interface
	 */
	public void showUI() {
		
		StaffManager staffManager = new StaffManager(user);
		Scanner sc = new Scanner(System.in); 
		int choice;
		boolean run = true;
		
		do {
			try {
				System.out.println();
				System.out.println("-----------------------------------------");
				System.out.println("Welcome to STARS(Staff), " + user.getFirstName() + "!");
				System.out.println("-----------------------------------------");
				System.out.println("(1) Edit Student Access Period");
				System.out.println("(2) Check Student Access Period");
				System.out.println("(3) Add a Student");
				System.out.println("(4) Add/Update a Course");
				System.out.println("(5) Check Vacancies for Index");
				System.out.println("(6) Print Student List by Index");
				System.out.println("(7) Print Student List by Course");
				System.out.println("(8) Exit");
				System.out.println("-----------------------------------------");
				System.out.print("Enter your choice: ");
				
				choice = sc.nextInt();
				System.out.println("");
				switch(choice) {
					case 1:
						// Choose which student to edit
						String matric;
						Student student;
						boolean found = false;
						
						System.out.print("Enter Student's Matric Number to edit access period: ");
						matric = sc.next();
						// Find the student object using matriculation number
						if (Manager.getStudentDB().containsKey(matric)) {
							student = Manager.getStudentDB().get(matric);
							boolean invalid = true;
							// Ask staff for access and end date and time
							Calendar accessStart = Calendar.getInstance();
							accessStart.setLenient(false);
							Calendar accessEnd = Calendar.getInstance();
							accessEnd.setLenient(false);
							int startMth = -1, startDay = -1, startHour = -1, startMin = -1, endMth = -1, endDay = -1, endHour = -1, endMin = -1;
								
							while (invalid) {
								System.out.println("\n---Editing " + student.getFirstName() + " Access Start Period---");
								accessStart = StaffUI.inputDate(startMth, startDay, startHour, startMin, accessStart, sc);
								System.out.println("\n---Editing " + student.getFirstName() + " Access End Period---\n");
								accessEnd = StaffUI.inputDate(endMth, endDay, endHour, endMin, accessEnd, sc);
							
								// Edit student access period
								if (accessEnd.compareTo(accessStart) < 0) {
									System.out.println("Invalid access period! Please try again.\n");
								} else invalid = false;
							}

							staffManager.editStudentAccessPeriod(accessStart, accessEnd, student);
							System.out.println("Student's access period successfully changed!\n");
							found = true;
							break;
						}
						
						// If student not found
						if (found == false) {
							System.out.println("Matriculation Number does not exist!\n");
						}
						break;
					case 2:
						// Choose which student to check access period
						boolean found2 = false;
						Student student2 = null;
						
						System.out.print("Enter Student's Matric Number to check access period: ");
						String matric2 = sc.next();
						// Find the student object using matriculation number
						if (Manager.getStudentDB().containsKey(matric2)) {
							student2 = Manager.getStudentDB().get(matric2);
							found2 = true;
						}

						// If student not found
						if (found2 == false) {
							System.out.println("Matriculation Number does not exist!\n");
						} else {
							// Return access start and end date and time
							Calendar accessStart = staffManager.checkStudentAccessStart(student2);
							Calendar accessEnd = staffManager.checkStudentAccessEnd(student2);
							
							System.out.println("\n---Viewing " + student2.getFirstName() + "'s Access Start Period---");	
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY, h:mma");
							System.out.println("Access Start Time: " + sdf.format(accessStart.getTime()));
							System.out.println("Access End Time: " + sdf.format(accessEnd.getTime()));
						}
						break;						
					case 3:
						// Add a student
						System.out.print("Enter New Student's First Name: ");
						String firstName = sc.next();
						System.out.print("Enter New Student's Last Name: ");
						String lastName = sc.next();
						System.out.print("Enter New Student's Username: ");
						String username = sc.next();
						System.out.print("Enter New Student's Default Password: ");
						String password = sc.next();
						System.out.print("Enter New Student's Email: ");
						String email = sc.next();
						System.out.print("Enter New Student's Matriculation Number: ");
						String matricNumber = sc.next();
						System.out.print("Enter New Student's Gender: ");
						String gender = sc.next();
						System.out.print("Enter New Student's Nationality: ");
						String nationality = sc.next();
						
						Student newStudent = new Student(username, password, false, firstName, 
								lastName, email, matricNumber, gender, nationality, 0);
						staffManager.addStudent(newStudent);
						break;
					case 4:
						// Add/Update a course
						int selection;
						do {
							System.out.println("Do you want to Add or Update a Course?");
							System.out.println("(1) Add");
							System.out.println("(2) Update");
							System.out.println("(3) Back");
							selection = sc.nextInt();
							
							if (selection == 1) {
								System.out.print("\nEnter Course Code to add: ");
								String courseCode = sc.next();
								String clear = sc.nextLine();
								System.out.print("\nEnter Course Name to add: ");
								String courseName = sc.nextLine();
								System.out.print("\nEnter Faculty to add: ");
								String school = sc.nextLine();
								System.out.print("\nEnter Number of AUs for the course: ");
								int au = sc.nextInt();
								
								staffManager.addCourse(courseCode, courseName, school, au);
								Course newCourse = staffManager.findCourse(courseCode);
								System.out.print("\nEnter number of Indexes to insert into Course (" + courseName + ") : ");
								int numIndex = sc.nextInt();
								for (int i = 0; i < numIndex; i++) {
									System.out.println("Adding Index (" + (i+1) + "/" + numIndex + ")");
									System.out.print("\nEnter Index Number: ");
									int index = sc.nextInt();
									System.out.print("\nEnter Number of Vacancies for Index " + index + ": ");
									int vacancies = sc.nextInt();
									Index newIndex = new Index(index, vacancies);
									newCourse.addIndex(newIndex);
									boolean done = false;
									System.out.print("\nEnter Number of Lecture Sessions for Index " + index + ": ");
									int lectureCount = sc.nextInt();
									while (lectureCount < 1) {
										System.out.print("\nInvalid Entry. Lecture Session is Required. Please Enter Number of Lecture Sessions for Index " + index + ": ");
										lectureCount = sc.nextInt();											
									}
									System.out.print("\nEnter Number of Tutorial Sessions for Index " + index + ": ");
									int tutorialCount = sc.nextInt();
									System.out.print("\nEnter Number of Laboratory Sessions for Index " + index + ": ");
									int laboratoryCount = sc.nextInt();
									for (int j = 1; j <= lectureCount; j++) {
										Session newSession = StaffManager.inputSession("Lecture", j, sc);
										newIndex.addSessionList(newSession);
									}
									for (int j = 1; j <= tutorialCount; j++) {
										Session newSession = StaffManager.inputSession("Tutorial", j, sc);
										newIndex.addSessionList(newSession);
									}
									for (int j = 1; j <= laboratoryCount; j++) {
										Session newSession = StaffManager.inputSession("Laboratory", j, sc);
										newIndex.addSessionList(newSession);
									}
								}
								System.out.println("New Indexes Successfully Added!\n");
							}
							else if (selection == 2) {
								// Update Course Code
								System.out.print("Enter Course Code to update: ");
								String courseCode = sc.next();
								String clear = sc.nextLine();
								Course course = staffManager.findCourse(courseCode);
								// Error Handling
								if (course == null) {
									continue;
								}
								staffManager.updateCourseCode(course, courseCode);
								//Update Course Name
								System.out.print("Enter Course Name to update: ");
								String courseName = sc.nextLine();
								staffManager.updateCourseName(course, courseName);
								// Update Faculty
								System.out.print("Enter Faculty to update: ");
								String school = sc.nextLine();
								staffManager.updateCourseSchool(course, school);
								// Update Index Number
								System.out.print("Enter Index to update: ");
								int index = sc.nextInt();
								Index updatedIndex = staffManager.findIndex(index);
								// Error Handling
								if (updatedIndex == null) {
									continue;
								}
								// Update Vacancies
								System.out.print("Enter new Vacancy to Index " + index + ": ");
								int vacancy = sc.nextInt();
								updatedIndex.setVacancies(vacancy);
								
								System.out.println("Course " + course.getCourseCode() + " Successfully Updated!\n");
							}
						}
						while (selection != 3);
						break;
					case 5:
						// Check vacancies for index number
						System.out.print("Enter index to check vacancy: ");
						int index = sc.nextInt();
						Index checkIndex = staffManager.findIndex(index);
						// Error Handling
						if (checkIndex == null)
							continue;
						System.out.println("Vacancies for index " + checkIndex.getIndexNumber() + ": " + checkIndex.getVacancies());
						break;
					case 6:
						// Print student list by index number
						System.out.print("Enter index to display student list: ");
						index = sc.nextInt();
						staffManager.printStudentList(index);
						break;
					case 7:
						// Print student list by course code
						System.out.print("Enter Course Code: ");
						String courseCode = sc.next();
						staffManager.printStudentList(courseCode);
						break;
					case 8:
						// Exits
						run = false;
						break;
					// To check session list, can remove later
					case 9:
						System.out.print("Enter Index to Check Session List: ");
						Index index2 = staffManager.findIndex(sc.nextInt());
						for (Session s: index2.getSessionList()) {
							System.out.println("\nSession Type: " + s.getSessionType());
							System.out.println("Venue: " + s.getVenue());
							SimpleDateFormat sdf = new SimpleDateFormat("EEEE, h:mma");
							System.out.println("Start Time: " + sdf.format(s.getSessionStart().getTime()));
							System.out.println("End Time: " + sdf.format(s.getSessionEnd().getTime()));
						}
						break;
					default:
						System.out.println("Please choose an option from 1-8!");
						break;
				}
			}
			catch (Exception InputMismatchException) {
				System.out.println("Invalid input! Please try again.\n");
				sc.next();
			}
		} while (run);
	}
	
	/**
	 * Stores Calendar's Date from User Input
	 * @param mth Month
	 * @param day Day
	 * @param hour Hour
	 * @param min Minute
	 * @param calendar Calendar Object
	 * @param sc Scanner Object
	 * @return Calendar Object
	 */
	private static Calendar inputDate(int mth, int day, int hour, int min, Calendar calendar, Scanner sc) {
		try {			
			while (true) {
				// Input Month
				System.out.print("Enter Month (1-12): ");
				mth = (sc.nextInt()) - 1;
				if (mth < 0 || mth > 11) {
					System.out.println("Please enter an integer between 1 to 12! Please try again.");
					mth = -1;
					continue;
				}
				calendar.set(Calendar.MONTH, mth);
				
				// Input Day
				System.out.print("Enter Day: ");
				day = sc.nextInt();
				if (day < 1 || day > calendar.getActualMaximum(calendar.DAY_OF_MONTH)) {
					System.out.println("Please a valid date! Please try again.");
					continue;
				}
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
				
				calendar.set(Calendar.DAY_OF_MONTH, day);
				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, min);
				calendar.set(Calendar.SECOND, 0);
				return calendar;
			}
		}
		catch (Exception InputMismatchException) {
			System.out.println("Invalid input! Please try again.\n");
		}
		return calendar;
	}	
}