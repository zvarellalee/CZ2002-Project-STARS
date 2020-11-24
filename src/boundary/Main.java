/**
 * Main method body to call the login UI
 * @version 1.0
 * @since 2020-11-20
 */
package boundary;

import database.Database;

public class Main {
	public static void main(String[] args) {
		//Database.initialise();
		UserUI login = new LoginUI();
		login.showUI();
	}
}
