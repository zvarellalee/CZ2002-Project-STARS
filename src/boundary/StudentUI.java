package boundary;

import java.util.ArrayList;
import java.util.Scanner;
import database.Database;
import entities.Course;
import entities.Index;
import entities.Student;
import control.StudentManager;

public class StudentUI implements UserUI {
	
	private static Student user;
	
	public Student getStudent() {
		return StudentUI.user;
	}
	
	public void setStudent(Student user) {
		StudentUI.user = user;
	}
	
	// to test
	public static void main(String[] args) {
		Database.initialise();
		user = Database.studentList.get(0);
		StudentUI studentUI = new StudentUI();
		studentUI.setStudent(user);
		studentUI.showUI();
	}
	
	public void showUI() {

		StudentManager studentManager = new StudentManager(user);
		Scanner sc = new Scanner(System.in); 
		int choice;
		String courseCode;
		boolean courseExists;
		boolean indexExists;
		
		do { 
			System.out.println("-----------------------------------------");
			System.out.println("Welcome to STARS(Student), " + user.getFirstName() + "!");
			System.out.println("-----------------------------------------");
			System.out.println("(1) Add Course");
			System.out.println("(2) Drop Course");
			System.out.println("(3) Check/Print Courses Registered ");
			System.out.println("(4) Check Vacancies Available");
			System.out.println("(5) Change Index Number of Course");
			System.out.println("(6) Swap Index Number with Another Student");
			System.out.println("(7) Check Maximum AUs");
			System.out.println("(8) Exit");
			System.out.println("-----------------------------------------");
			System.out.print("Enter your choice: ");
			
			choice = sc.nextInt();
			System.out.println("");
			switch(choice) {
				case 1:
					// add course 
					System.out.println("Please enter the Course Code that you wish to add: ");
					courseCode = sc.next();
					studentManager.addCourse(courseCode);
					break;
				case 2:
					// drop course 
					System.out.println("Please enter the Course Code that you wish to drop: ");
					courseCode = sc.next();
					studentManager.dropCourse(courseCode);
					break;
				case 3:
					// check/print courses registered
					studentManager.printRegistered();
					break;
				case 4:
					// check vacancies 
					// initialise course does not exist
					courseExists = false;
					// loop user to re-enter courseCode if course does not exist
					do {
						System.out.print("Enter Course Code (Enter Q to exit): ");
						courseCode = sc.next();
						if (courseCode.toUpperCase().equals("Q")) {
							System.out.println("\nReturning back to main menu...");
							System.out.println("");
							break;
						}
						courseExists = studentManager.checkVacancies(courseCode);
					} while(!courseExists);
					break;
				case 5:
					// change index number
					// initialise index number does not exist
					indexExists = false;
					// let student input current index to change index
					int currentIndex = 0;
					do {
						// print courses registered
						studentManager.printRegistered();
						System.out.print("Enter the current Index you want to change (Enter 0 to exit): ");
						currentIndex = sc.nextInt();
						if (currentIndex == 0) {
							System.out.println("\nReturning back to main menu...");
							System.out.println("");
							break;
						}
						indexExists = studentManager.changeIndex(currentIndex);
					} while (!indexExists);
					break;
				case 6:
					// TODO
					// swap index number
					break;
				case 7:
					// TODO: what is this method for???
					// check max AU
					break;
				case 8:
					// exit
					break;
				default:
					System.out.println("Please choose an option from 1-8!");
					break;
			}
		} while (choice != 8);
	}
		
}
