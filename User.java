public class User {
	private String name;
	private String username;
	private String password;
	private boolean adminAccess;
	private String firstName;
	private String lastName;
	private String salt;
	
	public User(String username, String password, boolean adminAccess, String firstName, String lastName, String salt) {
		this.username = username;
		this.password = password;
	}
	public String getName() {
		return name;
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
	public void setAdminAccess(boolean admin) {
		adminAccess = admin;
	}
	public boolean getAdminAccess() {
		return adminAccess;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
