package boundary;

import java.util.Scanner;

import control.LoginManager;

public class LoginUI {
	public void displayLogin() {
		Scanner sc = new Scanner(System.in);
		LoginManager manager = new LoginManager();
		UserUI newUI;
		int choice = 1;
		String username;
		String password;
		
		manager.getFromFile(); // instantiate the users array in loginManager
		
		System.out.println("Welcome to mySTARS. Please select an option: ");
		while (choice < 2) {
			System.out.println("1. Login");
			System.out.println("2. Quit");
			if (sc.hasNextInt()) {
				choice = sc.nextInt();
			} else {
				sc.hasNext();
				System.out.println("Please enter a valid input");
			}
			
			switch (choice) {
				case 1: 
					System.out.println("Enter username: ");
					username = sc.nextLine();
					System.out.println("Enter password: ");
					password = sc.nextLine();

					newUI = manager.authenticate(username, password);
					if (newUI != null)
						choice = 2;
						manager.login(newUI);
					break;
				default: 
					System.out.println("Thank you for using mySTARS.");
					break;
			}
			
			
		}
		sc.close();
	}
}
