package boundary;

import java.util.Calendar;
import java.util.Scanner;
import database.Database;
import entities.Course;
import entities.Staff;
import entities.Student;
import control.StaffManager;

public class StaffUI implements UserUI {
	
	private static Staff user;
	
	public Staff getStaff() {
		return StaffUI.user;
	}
	
	public void setStaff(Staff user) {
		StaffUI.user = user;
	}
	
	// to test
	public static void main(String[] args) {
		Database.initialise();
		user = Database.staffList.get(0);
		StaffUI staffUI = new StaffUI();
		staffUI.setStaff(user);
		staffUI.showUI();
	}

	public void showUI() {
		
		StaffManager staffManager = new StaffManager(user);
		Scanner sc = new Scanner(System.in); 
		int choice;
		
		do { 
			System.out.println("-----------------------------------------");
			System.out.println("Welcome to STARS(Staff), " + user.getFirstName() + "!");
			System.out.println("-----------------------------------------");
			System.out.println("(1) Edit Student Access Period");
			System.out.println("(2) Add a Student");
			System.out.println("(3) Add/Update a Course");
			System.out.println("(4) Check Vacancies for Index");
			System.out.println("(5) Print Student List by Index");
			System.out.println("(6) Print Student List by Course");
			System.out.println("(7) Exit");
			System.out.println("-----------------------------------------");
			System.out.print("Enter your choice: ");
			
			choice = sc.nextInt();
			System.out.println("");
			switch(choice) {
				case 1:
					// Choose which student to edit
					String matric;
					Student student;
					
					System.out.print("Enter Student's Matric Number to edit access period: ");
					matric = sc.next();
					// Find the student object using matriculation number
					for (Student s : Database.studentList) {
						if (s.getMatricNumber() == matric) {
							student = s;
							
							// Ask staff for access and end date and time
							Calendar accessStart = Calendar.getInstance();
							Calendar accessEnd = Calendar.getInstance();
							int startMth, startDay, startHour, startMin, endMth, endDay, endHour, endMin;

							System.out.print("Enter Start Month (1-12): ");
							startMth = sc.nextInt();
							System.out.print("Enter Day: ");
							startDay = sc.nextInt();
							System.out.print("Enter Hour: ");
							startHour = sc.nextInt();
							System.out.print("Enter Minute: ");
							startMin = sc.nextInt();
							
							accessStart.set(Calendar.MONTH, startMth);
							accessStart.set(Calendar.DAY_OF_MONTH, startDay);
							accessStart.set(Calendar.HOUR_OF_DAY, startHour);
							accessStart.set(Calendar.MINUTE, startMin);
							
							System.out.print("Enter End Month (1-12): ");
							endMth = sc.nextInt();
							System.out.print("Enter Day: ");
							endDay = sc.nextInt();
							System.out.print("Enter Hour: ");
							endHour = sc.nextInt();
							System.out.print("Enter Minute: ");
							endMin = sc.nextInt();
							
							accessEnd.set(Calendar.MONTH, endMth);
							accessEnd.set(Calendar.DAY_OF_MONTH, endDay);
							accessEnd.set(Calendar.HOUR_OF_DAY, endHour);
							accessEnd.set(Calendar.MINUTE, endMin);
							
							// Edit student access period
							staffManager.editStudentAccessPeriod(accessStart, accessEnd, student);
							System.out.println("Student's access period successfully changed!");
							break;
						}
					}	
					break;
				case 2:
					// Add a student
					// TODO: OMG HALP SO MANY DETAILS TO ADD?
					break;
				case 3:
					// Add/Update a course
					int selection;
					do {
						System.out.println("Do you want to Add or Update a Course?");
						System.out.println("(1) Add");
						System.out.println("(2) Update");
						System.out.println("(3) Back");
						selection = sc.nextInt();
						
						if (selection == 1) {
							
						}
						else if (selection == 2) {
							
						}
					}
					while (selection != 3);
					break;
				case 4:
					// Check vacancies for index number
					break;
				case 5:
					// Print student list by index number
					break;
				case 6:
					// Print student list by course code
					break;
				case 7:
					// exit
					break;
				default:
					System.out.println("Please choose an option from 1-7!");
					break;
			}
		} while (choice != 7);
	}
	
}
