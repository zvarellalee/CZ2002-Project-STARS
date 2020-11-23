/**
 * Manager for Login Input
 * @version 1.0
 * @since 2020-11-20
 */
package control;

import java.util.*;

import boundary.*;
import entities.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LoginManager{
	/**
	 * Stores the list of users to cross-check the email
	 */
	private List<User> users;
	
	/**
	 * Initialize the Login Manager with users from student and staff files
	 */
	public LoginManager() {
		users = new ArrayList<User>();
		users.addAll((Collection<User>)FileManager.read("student.dat").values());
		users.addAll((Collection<User>) FileManager.read("staff.dat").values());
	}
	
	/**
	 * Refresh the user array if new users are added in the same application session
	 */
	private void refresh() {
		users.clear();
		users.addAll((Collection<User>)FileManager.read("student.dat").values());
		users.addAll((Collection<User>) FileManager.read("staff.dat").values());
	}
	
	/**
	 * Method to start the respective staff or student UI for a particular user
	 * @param User user
	 */
	private void login(User user) {
		if (user.getAdminAccess() == true) {
			StaffUI newUI = new StaffUI(); // if admin, return staff UI
			newUI.setStaff((Staff)user);
			newUI.showUI();
		} else {
			if (LoginManager.checkAccessPeriod((Student)user) == true) {
				StudentUI newUI = new StudentUI(); // else, return the student UI
				newUI.setStudent((Student)user);
				newUI.showUI();
			}
		}
	}
	
	/**
	 * Check if the user has entered the correct username/password
	 * @param username Username of user
	 * @param password Password of user
	 * @return boolean
	 */
	public boolean authenticate(String username, String password) {
		refresh();
		User currUser = null;
		for (User user: users) {
			if (user.getUsername().toLowerCase().contentEquals(username.toLowerCase())) { // find the correct user given the username
				currUser = user;
			}
		}
		
		if (currUser == null) {
			return false; // if user not found, return null
		}

		String salt = currUser.getSalt();
		
		if (salt == null) { // if the given password has no salt, create a new salt and hash the password
			try {
				salt = generateSalt(currUser.getPassword()); // get a new salt for the user
				currUser.setSalt(salt);
				String newPassword = getHashedPassword(currUser.getPassword(),salt);
				currUser.setPassword(newPassword);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		String correctPassword = currUser.getPassword(); // password from user object
		String hashPassword = null;
		try {
			hashPassword = getHashedPassword(password,salt); // password to compare
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (hashPassword.equals(correctPassword)) {
			login(currUser);
			return true;
		}
		return false; // if password is incorrect, return false
	}
	
	/**
	 * Generate a new salt for a given password
	 ****************************************************************************************
	 *    Title: Java SHA-512 Hash With Salt Example
	 *    Author: Ramesh Fadatare
	 *    Availability: https://www.javaguides.net/2020/02/java-sha-512-hash-with-salt-example.html
	 *
	 ***************************************************************************************
	 * @param password User's unhashed password
	 * @return salt Generated salt
	 * @throws NoSuchAlgorithmException Specified Hashing Algorithm not found
	 * @throws UnsupportedEncodingException Byte encoding not supported
	 */
	public static String generateSalt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		String encoded = new String(salt,"UTF-8");
		return encoded;
	}
	
	/**
	 * generate the hashed password from a given salt
	 * ***************************************************************************************
	 *    Title: Java SHA-512 Hash With Salt Example
	 *    Author: Ramesh Fadatare
	 *    Availability: https://www.javaguides.net/2020/02/java-sha-512-hash-with-salt-example.html
	 *
	 ***************************************************************************************
	 * @param passwordToHash Password to be hashed
	 * @param salt Specified user's salt
	 * @return password Hashed Password
	 * @throws NoSuchAlgorithmException Specified Hashing Algorithm not found
	 * @throws UnsupportedEncodingException Byte encoding not supported
	*/
	public static String getHashedPassword(String passwordToHash, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String password = null; 
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
		byte[] decodedPassword = passwordToHash.getBytes("UTF-8");
			
		messageDigest.update(salt.getBytes());
		byte[] passwordBytes = messageDigest.digest(decodedPassword);
		StringBuilder builder = new StringBuilder();
		for(int i=0; i < passwordBytes.length ;i++) {
			builder.append(Integer.toString((passwordBytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    password = builder.toString();

		return password;
	}
	
	/**
	 * Check if the student access period contains the current date
	 * @param stud Student
	 * @return boolean 
	 */
	private static boolean checkAccessPeriod(Student stud) {
		// Check current time
		Calendar currentTime = Calendar.getInstance();

		// Check if accessed time is before access period
		if (currentTime.compareTo(stud.getAccessStart()) < 0) {
			System.out.println("You are not allowed to access yet! Access Period is " + stud.getAccessStart().getTime());
			return false;
		// Check if accessed time is after access period
		} else if (currentTime.compareTo(stud.getAccessEnd()) > 0) {
			System.out.println("Your access period is over! Please contact the system administrator\n");
			return false;
		}
		// Returns true when student logins during access period
		else
			return true;
	}
}
