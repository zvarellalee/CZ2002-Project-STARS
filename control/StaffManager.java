package control;

import java.util.*;
import database.Database;
import entities.Course;
import entities.Index;
import entities.RegisteredCourse;
import entities.Staff;
import entities.Student;

public class StaffManager {
	private Staff user;
	
	public StaffManager(Staff user) {
		this.user = user;
		// Database.initialise(); // To test out the UI
	}
	
	public void editStudentAccessPeriod(Calendar accessStart, Calendar accessEnd, Student student) {
		student.setAccessStart(accessStart);
		student.setAccessEnd(accessEnd);
	}
	
	public void updateCourseCode(Course course, String courseCode) {
		course.setCourseCode(courseCode);
	}
	
	public void updateCourseName(Course course, String courseName) {
		course.setCourseName(courseName);
	}
	
	public void updateCourseSchool(Course course, String courseSchool) {
		course.setSchool(courseSchool);
	}
	
	public void addStudent(Student student) { 
		
		for (Student s : Database.studentList) {
			if (s.getMatricNumber().equals(student.getMatricNumber())) {
				System.out.println("Student already exists");
				return;
			}
		}	
		
        	Database.studentList.add(student);
		printStudentList();
	}
	
	private  void printStudentList() { 
		System.out.println("Matriculation Number         Full Name");
		System.out.println("---------------------------------------------------");
		for (Student student : Database.studentList){
			System.out.print(student.getMatricNumber() + "\t");
			System.out.println(student.getFirstName() + " " + student.getLastName()); 
			// decide how much to display
		}
	}
	

	public void addCourse(String courseCode, String courseName, String school, ArrayList<Index> indexList ) {
		for (Course course : Database.courseList) {
			if (course.getCourseCode().equals(courseCode)) {
				System.out.println("Course "+ courseCode +" already exists");
				return;
			}
		}
		
		Course newCourse = new Course ( courseCode, courseName, school);
		ArrayList<Index> il 	= new ArrayList<Index>();
		newCourse.setIndexList(il);
		Database.courseList.add(newCourse); 
		printCourseList();
	}
	
	public void printCourseList() {
		System.out.println("Course Code : Course Name");
		System.out.println("---------------------------------------------------");
		for (Course course : Database.courseList){
			System.out.println(course.getCourseCode() + ":" + course.getCourseName());
			
		}
	}
	
	public void addStudentToIndex(String courseCode, int indexNumber) {
		Index index = findIndex( indexNumber, courseCode);
		Course course = findCourse(courseCode);
		int vacancy = index.getVacancies();
		ArrayList<Student> studentWaitlist = index.getWaitList();
		ArrayList<Student> studentlist = index.getStudentList();
		
		if (vacancy <= 0) {
			System.out.println("No vacancies");
			return;
		}
		
		for (Student s : studentWaitlist) {
			if(vacancy > 0 || studentWaitlist.isEmpty()) {
				ArrayList<RegisteredCourse> registeredCourseList = s.getCourseList();
				for ( RegisteredCourse rc : registeredCourseList) {
					if (rc.getIndex().equals(index)) {
						Database.studentList.add(s);
						rc.setOnWaitlist(false);
						studentWaitlist.remove(s);
						vacancy--;
					}
				}
						
		     }
		}
		
		index.setVacancies(vacancy);
			
	}
	
	
	public void printStudentList(String courseCode){
		Course course = findCourse(courseCode);
		
		System.out.println("Course Code: " + courseCode);
		System.out.println("Name\tGender\tNationality");
		
		ArrayList<Index> courseIndex = course.getIndexList();
		
		for (Index index : courseIndex) {
			for (Student student : courseIndex.getStudentList()) {
				System.out.print(student.getFirstName() + " " + student.getLastName() + ",\t"
								+ student.getGender() + ",\t" + student.getNationality());
			}
		}
	}	
	
	public void printStudentList(int indexNumber, String courseCode){
		System.out.println("Index Number: " + index);
		System.out.println("Name\tGender\tNationality");
		
		Index index = findIndex(indexNumber, courseCode);
		
		for (Student student : index.getStudentList()) {
			System.out.print(student.getFirstName() + " " + student.getLastName() + ",\t"
							+ student.getGender() + ",\t" + student.getNationality());
		}
	}
	
	private Course findCourse(String courseCode){
		for (Course c : Database.courseList) {
			if (c.getCourseCode().equals(courseCode)) {
				return c;
			}
		}
		return null;
	}
	
	private Index findIndex(int indexNumber, String courseCode){
		Course course = findCourse(courseCode);
		List<Index> indexList = course.getIndexList();
			
		for (Index i: indexList ) {
			if (i.getIndexNumber() == indexNumber) {
				return i;
			}
		}
		return null;
	}
}
