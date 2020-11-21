package entities;

import java.io.Serializable;

public class User implements Serializable{
	private String username;
	private String password;
	private boolean adminAccess;
	private String firstName;
	private String lastName;
	private String salt;
	private String email;
	
	public User(String username, String password, boolean adminAccess, String firstName, String lastName, String salt, String email) {
		this.username = username;
		this.password = password;
		this.adminAccess = adminAccess;
		this.firstName = firstName;
		this.lastName = lastName;
		this.salt = salt;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getSalt() {
		return salt;
	}
	public String getEmail() {
		return email;
	}
	public boolean getAdminAccess() {
		return adminAccess;
	}
	public void setAdminAccess(boolean admin) {
		adminAccess = admin;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
