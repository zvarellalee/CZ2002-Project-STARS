package control;

import java.util.*;

import boundary.*;
import entities.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LoginManager{
	private List<User> users;
	
	public LoginManager() {
		users = new ArrayList<User>();
		users.addAll(FileManager.read("student.dat"));
		users.addAll(FileManager.read("staff.dat"));
	}
	
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
	
	public boolean authenticate(String username, String password) {
		User currUser = null;
		for (User user: users) {
			if (user.getUsername().contentEquals(username)) { // find the correct user given the username
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
			}
		}
		
		String correctPassword = currUser.getPassword(); // password from user object
		String hashPassword = getHashedPassword(password,salt); // password to compare
		
		if (hashPassword.equals(correctPassword)) {
			login(currUser);
			return true;
		}
		return false; // if password is incorrect, return false
	}
	
	private static String generateSalt(String password) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}
	// generate the hashed password from the salt
	private static String getHashedPassword(String passwordToHash, String salt) {
		String password = null;
		try { 
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes());
			byte[] passwordBytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< passwordBytes.length ;i++)
	           {
	               sb.append(Integer.toString((passwordBytes[i] & 0xff) + 0x100, 16).substring(1));
	           }
	        password = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}

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
