package entities;

import java.util.ArrayList;

/* Each course refers to a particular module from a particular school.*/

public class Course {
	private String courseCode;
	private String courseName;
	private String school;
	private int AU;
	private ArrayList<Index> indexList;

	public Course(String courseCode, String courseName, String school) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.school = school;
		indexList = new ArrayList<Index>();
	}
	
	// ------ accessor methods ------
	public String getCourseCode() {
		return courseCode;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public String getSchool() {
		return school;
	}

	public int getAU() {
		return AU;
	}

	public ArrayList<Index> getIndexList() {
		return indexList;
	}


	// ------ mutator methods ------
	// set course code
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	// set course name
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	// set the school offering the course
	public void setSchool(String school) {
		this.school = school;
	}
	
	// set the number of AU for this course
	public void setAU(int AU) {
		this.AU = AU;
	}
	
	// set the indexList to a predefined indexList
	public void setIndexList(ArrayList<Index> indexList) {
		this.indexList = indexList;
	}
	
	// add an index to the indexList
	public void addIndex(Index index) {
		indexList.add(index);
	}
	
	// remove an index from the indexList
	public void removeIndex(Index index) {
		if (indexList.contains(index)) {
			indexList.remove(index);
		}
	}
}
