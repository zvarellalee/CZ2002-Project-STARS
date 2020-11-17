package control;

import java.util.*;
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
		
		if (currUser == null) return null; // if user not found, return null
		
		String encryptedPassword = currUser.getPassword();
		String salt = currUser.getSalt();
		String decryptedPassword = getHashedPassword(encryptedPassword,salt);
		if (password.equals(decryptedPassword)) {
			if (currUser.getAdminAccess() == true) {
				Manager newManager = new StaffManager(); // if admin, return staff manager
				return newManager;
			} else {
				Manager newManager = new StudentManager(); // else, return the student manager
				return newManager;
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
	
	public Boolean checkAccess(User user) {
		// redundant?
		return true;
	}
	
	public void getFromFile() {
		// get users array from file
	}
}
