package planner.manager;

import java.util.*;

public class StaffManager implements Manager{
	private User Staff;
	private Calendar accessStart;
	private Calendar accessEnd;
	
	public void editStudentAccessPeriod(Calendar accessStart, Calendar accessEnd){
		this.accessStart = accessStart;
		this.accessEnd = accessEnd;
	}
	
	public void updateCourseCode(Course course, String courseCode){
		course.setCourseCode(courseCode);
	}
	
	public void updateCourseName(Course course, String courseName){
		course.setCourseName(courseCode);
	}
	
	public void updateCourseSchool(Course course, String courseSchool){
		course.setSchool(courseCode);
	}
	
	public void addStudent(Student student){
        	//do nothing
	}
	
	public void addCourse(String courseCode){
        	//do nothing
	}
	
	public void addStudentToIndex(Index index, Student student){
		//do nothing
	}
	
	public void printStudentList(int indexNumber){
		//do nothing
	}
	
	public void printStudentList(String courseCode){
		//do nothing
	}
	
	private Course findCourse(String courseCode){
		//do nothing
	}
	
	private Index findIndex(int index){
		//do nothing
	}
}
