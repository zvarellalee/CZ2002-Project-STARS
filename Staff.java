public class Staff extends User{
	
	private String name;
	private String staffID;
	
	public Staff (String username, String password, boolean adminAccess, String firstName, String lastName, String salt, String staffID) {
		super(username, password, adminAccess, firstName, lastName, salt);
		this.staffID = staffID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getStaffID() {
		return staffID;
	}
	
	public void setStaffID(String staffID) {
		this.staffID = staffID;
		
	}
}
