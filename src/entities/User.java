/**
 * Stores the shared User information for Staff and Student child classes
 * @version 1.0
 * @since 2020-11-20
 */
package entities;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import control.LoginManager;

public class User implements Serializable{
	/** Username of user */
	private String username;
	/** Hashed password of user */
	private String password;
	/** adminAccess reflects whether or not the user has admin priveleges */
	private boolean adminAccess;
	/** User first name */
	private String firstName;
	/** User last name */
	private String lastName;
	/** Hashing salt */
	private String salt;
	/** user email */
	private String email;
	
	/**
	 * User constructor for initialization
	 * @param String username
	 * @param String password
	 * @param boolean adminAccess
	 * @param String firstName
	 * @param String lastName
	 * @param String salt
	 * @param String email
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
	
	/**
	 * Accessor method for username
	 * @return String username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Accessor method for password
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Accessor method for first name
	 * @return String firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Accessor method for last name
	 * @return String last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Accessor method for salt
	 * @return String salt
	 */
	public String getSalt() {
		return salt;
	}
	
	/**
	 * accessor method for email
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Accessor method for admin access
	 * @return boolean adminAccess
	 */
	public boolean getAdminAccess() {
		return adminAccess;
	}
	
	/**
	 * Mutator method for admin access
	 * @param boolean admin
	 */
	public void setAdminAccess(boolean admin) {
		adminAccess = admin;
	}
	
	/**
	 * Mutator method for username
	 * @param String username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Mutator method for password
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Mutator method for salt
	 * @param String salt
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	/**
	 * Mutator method for email
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
