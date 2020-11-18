package boundary;

import java.util.Scanner;
import database.Database;
import entities.Course;
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
					break;
				case 2:
					// drop course 
					break;
				case 3:
					// check/print courses registered
					studentManager.printRegistered();
					break;
				case 4:
					// check vacancies 
					// initialise course does not exist
					boolean courseExists = false;
					// loop user to re-enter courseCode if course does not exist
					do {
						System.out.print("Enter Course Code: ");
						String courseCode = sc.next();
						courseExists = studentManager.checkVacancies(courseCode);
					} while(!courseExists);
					break;
				case 5:
					// change index number
					break;
				case 6:
					// swap index number
					break;
				case 7:
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
