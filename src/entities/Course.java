/**
 * Represents a course offered in the school.
 * A course can have many indexes and 
 * can be be registered by students.
 * @author Stanley Ho
 * @version 1.0
 * @since 2020-11-20
 */

package entities;

import java.io.Serializable;
import java.util.ArrayList;

/* Each course refers to a particular module from a particular school.*/


public class Course implements Serializable{
	/**
	 * Stores the Course Code of the Course 
	 * Each Course is identified using the courseCode
	 */
	private String courseCode;
	/**
	 * Stores Name of the Course
	 */
	private String courseName;
	/**
	 * Stores the Faculty that offers the Course
	 */
	private String school;
	/**
	 * Stores the Number of Academic Units for the Course
	 */
	private int AU;
	/**
	 * Stores an Array List of Indexes in the Course
	 */
	private ArrayList<Index> indexList;

	/**
	 * Initializing the Course
	 * @param courseCode Course Code
	 * @param courseName Course Name
	 * @param school Faculty
	 * @param AU Number of Acadamic Units
	 */
	public Course(String courseCode, String courseName, String school, int AU) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.school = school;
		this.AU = AU;
		indexList = new ArrayList<Index>();
	}
	
	// ------ accessor methods ------
	/**
	 * Gets the Course Code
	 * @return courseCode Course Code
	 */
	public String getCourseCode() {
		return courseCode;
	}
	
	/**
	 * Gets the Course Name
	 * @return courseName Course Name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Gets the Faculty
	 * @return school Faculty
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * Gets the Number of Academic Units
	 * @return AU Number of Academic Units
	 */
	public int getAU() {
		return AU;
	}

	/**
	 * Gets the array list of Indexes in the Course
	 * @return indexList Array List of Indexes
	 */
	public ArrayList<Index> getIndexList() {
		return indexList;
	}


	// ------ mutator methods ------
	/**
	 * Sets the Course Code
	 * @param courseCode Course Code
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	/**
	 * Sets the Course Name
	 * @param courseName Course Name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Sets the Faculty
	 * @param school Faculty
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	
	/**
	 * Sets the Number of Academic Units
	 * @param AU Number of Academic Units
	 */
	public void setAU(int AU) {
		this.AU = AU;
	}
	
	/**
	 * Sets the array list of Indexes in the Course
	 * @param indexList Array List of Indexes
	 */
	public void setIndexList(ArrayList<Index> indexList) {
		this.indexList = indexList;
	}
	
	/**
	 * Adds an Index to the array list of Indexes in the Course
	 * @param index Index
	 */
	public void addIndex(Index index) {
		indexList.add(index);
	}
	
	/**
	 * Removes an Index from the array list of Indexes in the Course
	 * @param index Index
	 */
	public void removeIndex(Index index) {
		if (indexList.contains(index)) {
			indexList.remove(index);
		}
	}
	
	/**
	 * Print the index list of a course
	 */
	public void printIndexList() {
		System.out.println(courseCode + " " + courseName + " Indexes:");
		System.out.println("-----------------------------------------");
		for (Index i: indexList) {
			System.out.println(i.getIndexNumber());
		}
	}
}
