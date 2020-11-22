/**
 * User Interface for Login
 * @author Zachary Varella Lee Zheyu
 * @version 1.0
 * @since 2020-11-20
 */
package boundary;
import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

import control.*;
public class LoginUI implements UserUI{
	/**
	 * Display the login UI
	 */
	public void showUI() {
		Console console = System.console();
		boolean masking = true;
		if (console == null) {
			System.out.println("Using IDE - masking is disabled. Please launch from cmd to use password masking.");
			masking = false;
		}
		Scanner sc = new Scanner(System.in);
		LoginManager manager = new LoginManager();
		UserUI newUI;
		int choice = 1;
		String username;
		String password;
		do {
			System.out.println("-----------------------------------------");
			System.out.println("Welcome to the mySTARS Homepage!");
			System.out.println("-----------------------------------------");
			System.out.println("1. Login");
			System.out.println("2. Quit");
			System.out.println("-----------------------------------------");
			System.out.print("Enter your choice: ");
			try {
				choice = Integer.parseInt(sc.next());
				switch (choice) {
				case 1: 
					sc.nextLine(); // eat the newLine character
					System.out.print("Enter username: ");
					username = sc.nextLine();
					System.out.print("Enter password: ");
					if (masking) password = new String(console.readPassword());
					else password = sc.nextLine();
					boolean success = manager.authenticate(username, password);
					if (!success) {
						System.out.println("Incorrect username/password\n");
					}
					break;
				case 2: 
					System.out.println("Thank you for using mySTARS.");
					break;
				default:
					System.out.println("Please enter a number 1 to 2.\n");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid input.\n");
			}
		} while (choice != 2);
	}
}
