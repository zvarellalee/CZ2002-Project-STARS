package control;

import boundary.*;
import entities.*;
import java.util.List;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class LoginManager extends Manager{
	private List<User> users;
	public LoginManager() {
		users = (ArrayList)read("userList.dat");
	}
	
	public void login(UserUI UI) {
		UI.showUI();
	}
	
	public UserUI authenticate(String username, String password) {
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
				UserUI staffUI = new StaffUI(); // if admin, return staff manager
				return staffUI;
			} else {
				UserUI studentUI = new StudentUI(); // else, return the student manager
				return studentUI;
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
		if (salt == null) { // if no salt is given
			return passwordToHash;
		}
		return password;
	}
	
	
	public void getFromFile() {
		// get users array from file
	}
}
