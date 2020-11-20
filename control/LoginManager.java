package control;

import java.util.*;

import entities.Student;
import entities.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LoginManager{
	private User[] users;
	public LoginManager() {
		// get user array from file?
	}
	
	public Manager authenticate(String username, String password) {
		User currUser = null;
		for (User user: users) {
			if (user.getUsername().contentEquals(username)) { // find the correct user given the username
				currUser = user;
			}
		}
		
		if (currUser == null)
			return null; // if user not found, return null
		
		String encryptedPassword = currUser.getPassword();
		String salt = currUser.getSalt();
		String decryptedPassword = getHashedPassword(encryptedPassword,salt);
		if (password.equals(decryptedPassword)) {
			if (currUser.getAdminAccess() == true) {
				Manager newManager = new StaffManager(); // if admin, return staff manager
				return newManager;
			} else {
				if (LoginManager.checkAccessPeriod(currUser) == true) {
					Manager newManager = new StudentManager(); // else, return the student manager
					return newManager;
				}
			}
		} else {
			return null; // if password is incorrect, return false
		}
	}
	
	private static byte[] generateSalt(String password) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
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
	
	public void getFromFile() {
		// get users array from file
	}
	
	private static boolean checkAccessPeriod(Student stud) {
		// Check current time
		Calendar currentTime = Calendar.getInstance();

		// Check if accessed time is before access period
		if (currentTime.compareTo(stud.getAccessStart()) < 0) {
			System.out.println("You are not allowed to access yet! Access Period is" + stud.getAccessStart().getTime());
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
