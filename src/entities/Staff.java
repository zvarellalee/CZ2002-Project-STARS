/**
 * Represents a staff user that has administrative rights 
 * in mySTARS, with the relevant information stored 
 * in this class.
 * @author 
 * @version 1.0
 * @since 2020-11-20
 */

package entities;

public class Staff extends User {
	/** 
	 * Name of Staff
	 */
	private String name;
	/** 
	 * Staff Identifier
	 */
	private String staffID;
	
	
	/**
	 * Initialising the Staff
	 * @param username Username used to login to mySTARS
	 * @param password Password used to login to mySTARS
	 * @param adminAccess True as Staff has administrative rights
	 * @param firstName First Name
	 * @param lastName Last Name
	 * @param email Email Address
	 * @param staffID Staff Identifier
	 */
	public Staff (String username, String password, boolean adminAccess, String firstName, String lastName, String email, String staffID) {
		super(username, password, adminAccess, firstName, lastName, email);
		this.staffID = staffID;
	}
	
	/**
	 * Gets the Name of Staff
	 * @return Name of Staff
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the Staff Identifier
	 * @return staffID
	 */
	public String getStaffID() {
		return staffID;
	}
	
	/**
	 * Sets the Staff Identifier
	 * @param staffID
	 */
	public void setStaffID(String staffID) {
		this.staffID = staffID;
		
	}
}
