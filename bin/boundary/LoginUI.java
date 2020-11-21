package boundary;
import java.util.Scanner;

import control.*;

public class LoginUI {
	public void displayLogin() {
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
			if (sc.hasNextInt()) {
				choice = sc.nextInt();
			} else {
				sc.hasNext();
				System.out.println("Please enter a valid input");
			}
			
			switch (choice) {
				case 1: 
					sc.nextLine(); // eat the newLine character
					System.out.print("Enter username: ");
					username = sc.nextLine();
					System.out.print("Enter password: ");
					password = sc.nextLine();
					boolean success = manager.authenticate(username, password);
					if (!success) {
						System.out.println("Incorrect username/password\n");
					}
					break;
				default: 
					System.out.println("Thank you for using mySTARS.");
					break;
			}
			
			
		} while (choice < 2);
	}
}
