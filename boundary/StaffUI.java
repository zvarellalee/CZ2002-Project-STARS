package boundary;

import java.util.Scanner;
import database.Database;
import entities.Course;
import entities.Staff;
import entities.Student;
import control.StaffManager;

public class StaffUI implements UserUI {
	
	private static Staff user;
	
	public Staff getStudent() {
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
		
		StaffManager staffManager = new StaffManager(); // to change!
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
					// Edit student access period
					break;
				case 2:
					// Add a student
					break;
				case 3:
					// Add/Update a course
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
