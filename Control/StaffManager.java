package Control;

import java.util.*;
import Entities.Course;
import Entities.Index;
import Entities.Student;
import Entities.User;

public class StaffManager {
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
		course.setCourseName(courseName);
	}
	
	public void updateCourseSchool(Course course, String courseSchool){
		course.setSchool(courseSchool);
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
		System.out.println("Index Number: " + indexNumber);
		System.out.println("Name\tGender\tNationality");
		
		/*ArrayList<Index> courseIndex = course.getIndexList();
		
		for (Index index : courseIndex) {
			for (Student student : courseIndex.getStudentList()) {
				System.out.print(student.getFirstName() + " " + student.getLastName() + ",\t"
								+ student.getGender() + ",\t" + student.getNationality());
			}
		}*/
	}
	
	public void printStudentList(String courseCode){
		System.out.println("Course Code: " + courseCode);
		System.out.println("Name\tGender\tNationality");
		
		/*ArrayList<Index> courseIndex = course.getIndexList();
		
		for (Index index : courseIndex) {
			for (Student student : courseIndex.getStudentList()) {
				System.out.print(student.getFirstName() + " " + student.getLastName() + ",\t"
								+ student.getGender() + ",\t" + student.getNationality());
			}
		}*/
	}
	
	private Course findCourse(String courseCode){
		return null; //TODO
	}
	
	private Index findIndex(int index){
		return null; //TODO
	}
}
