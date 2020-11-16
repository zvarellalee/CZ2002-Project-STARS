package Entities;

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
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getAU() {
		return AU;
	}

	public void setAU(int AU) {
		this.AU = AU;
	}

	public ArrayList<Index> getIndexList() {
		return indexList;
	}

	public void setIndexList(ArrayList<Index> indexList) {
		this.indexList = indexList;
	}
	
	public void addIndex(Index index) {
		indexList.add(index);
	}
	
	public void removeIndex(Index index) {
		if (indexList.contains(index)) {
			indexList.remove(index);
		}
	}
	
}
