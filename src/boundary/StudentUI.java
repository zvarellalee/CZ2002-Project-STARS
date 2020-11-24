/**
 * User Interface for Student
 * @author Stanley Ho
 * @version 1.0
 * @since 2020-11-20
 */

package boundary;
import java.util.Scanner;
import entities.Student;
import control.PrintManager;
import control.StudentManager;

public class StudentUI implements UserUI {
	
	/**
	 * Stores which Student is using the UI
	 */
	private static Student user;
	
	/**
	 * Gets the student object
	 * @return Student Object
	 */
	public Student getStudent() {
		return StudentUI.user;
	}
	
	/**
	 * Sets the current user to input Student
	 * @param user Student
	 */
	public void setStudent(Student user) {
		StudentUI.user = user;
	}
	
	/**
	 * Displays the Student User Interface
	 */
	public void showUI() {

		StudentManager studentManager = new StudentManager(user);
		Scanner sc = new Scanner(System.in); 
		int choice;
		boolean run = true;
		String courseCode;
		
		do {
			try {
				System.out.println();
				System.out.println("-----------------------------------------");
				System.out.println("Welcome to STARS(Student), " + user.getFirstName() + "!");
				System.out.println("-----------------------------------------");
				System.out.println("(1) Add Course");
				System.out.println("(2) Drop Course");
				System.out.println("(3) Check/Print Courses Registered ");
				System.out.println("(4) Check Vacancies Available");		
				System.out.println("(5) Change Index Number of Course");
				System.out.println("(6) Swap Index Number with Another Student");
				System.out.println("(7) Exit");
				System.out.println("-----------------------------------------");
				System.out.print("Enter your choice: ");
				
				choice = sc.nextInt();
				System.out.println("");
				switch(choice) {
					case 1:
						// add course 
						PrintManager.printCourseList();
						System.out.println("Enter the Course Code that you wish to add  (Enter Q to exit): ");
						courseCode = sc.next().toUpperCase();
						if (!courseCode.toUpperCase().equals("Q"))
							studentManager.addCourse(courseCode);
						break;
					case 2:
						// drop course 
						PrintManager.printRegistered(user);
						System.out.println("Enter the Course Code that you wish to drop (Enter Q to exit): ");
						courseCode = sc.next().toUpperCase();
						if (!courseCode.toUpperCase().equals("Q"))
							studentManager.dropCourse(courseCode);
						break;
					case 3:
						// check/print courses registered
						PrintManager.printRegistered(user);
						break;
					case 4:
						// check vacancies 
						// initialise course does not exist
						boolean courseExists = false;
						// loop user to re-enter courseCode if course does not exist
						do {
							PrintManager.printCourseList();
							System.out.print("Enter the Course Code that you wish to check (Enter Q to exit): ");
							courseCode = sc.next().toUpperCase();
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
						boolean indexExists = false;
						// let student input current index to change index
						int currentIndex = 0;
						do {
							// print courses registered
							PrintManager.printRegistered(user);
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
						boolean canSwap = false;
						// let student input old index to change index
						int oldIndex = 0;
						do {
							// print courses registered
							PrintManager.printRegistered(user);
							System.out.print("Enter the current Index you want to swap (Enter 0 to exit): ");
							oldIndex = sc.nextInt();
							if (oldIndex == 0) {
								System.out.println("\nReturning back to main menu...");
								System.out.println("");
								break;	
							}
							System.out.print("Enter your peer's matric number whose Index you want to swap with (Enter Q to exit): ");
							String matricNumber = sc.next().toUpperCase();
							if (matricNumber == "Q") {
								System.out.println("\nReturning back to main menu...");
								System.out.println("");
								break;
							}
							canSwap = studentManager.swapIndex(oldIndex, matricNumber);
						} while (!canSwap);
						break;
					case 7:
						// exit
						run = false;
						break;
					default:
						System.out.println("Please choose an option from 1-7!");
						break;
				}
			}
			catch (Exception InputMismatchException) {
				sc.next();
				System.out.println("Invalid input! Returning back to main menu...\n");
			}
		} while (run);
	}
}
