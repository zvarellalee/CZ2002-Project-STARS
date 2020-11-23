/**
 * Represents a user in mySTARS with the 
 * relevant information stored in this class.
 * Each user can either be a staff or student user
 * where staff has administrative rights.
 * @author Cheah Jing Feng
 * @version 1.0
 * @since 2020-11-20
 */

package entities;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import control.LoginManager;

public class User implements Serializable{
	/** 
	 * Username that User uses to login into mySTARS
	 */
	private String username;
	/** 
	 * Password that User uses to login into mySTARS
	 */
	private String password;
	/** 
	 * Whether User has administrative rights 
	 */
	private boolean adminAccess;
	/** 
	 * User's First Name
	 */
	private String firstName;
	/** 
	 * User's Last Name
	 */
	private String lastName;
	/** 
	 * Hashing salt
	 */
	private String salt;
	/** 
	 * User's Email Addrss
	 */
	private String email;
	
	/**
	 * Initialising the User
	 * @param username Username used to login into mySTARS
	 * @param password Password used to login into mySTARS
	 * @param adminAccess Whether the User has administrative rights
	 * @param firstName First Name
	 * @param lastName Last Name
	 * @param email Email Address
	 */
	public User(String username, String password, boolean adminAccess, String firstName, String lastName, String email) {
		this.username = username;
		try {
			this.salt = LoginManager.generateSalt(password);
			this.password = LoginManager.getHashedPassword(password,this.salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.adminAccess = adminAccess;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	// ------ accessor methods ------
	/**
	 * Gets the Username of the User
	 * @return username Username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Gets the Password of the User
	 * @return password Password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets the first name of the User
	 * @return firstName First Name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Gets the last name of the User
	 * @return lastName Last Name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Gets the generated salt of the User
	 * @return salt Salt
	 */
	public String getSalt() {
		return salt;
	}
	
	/**
	 * Gets the email address of the User
	 * @return email Email Address
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Gets the boolean value of whether the User has administrative rights
	 * @return adminAccess
	 */
	public boolean getAdminAccess() {
		return adminAccess;
	}
	
	// ------ mutator methods ------
	/**
	 * Sets the administrative rights
	 * @param admin Administrative rights
	 */
	public void setAdminAccess(boolean admin) {
		adminAccess = admin;
	}
	
	/**
	 * Sets the Username
	 * @param username Username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Sets the Password
	 * @param password Password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Sets the Salt
	 * @param salt Salt
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	/**
	 * Sets the Email Address
	 * @param email Email Address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
