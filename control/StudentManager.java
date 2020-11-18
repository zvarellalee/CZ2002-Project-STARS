package control;

import entities.Student;
import entities.Course;

public class StudentManager {
	private Student user;
	
	public StudentManager(Student user) {
		this.user = user;
		Database.initialise();
	}
	
	public void findIndex(int index) {
		// Finds the course of the index
		
	}
	public void addCourse(String courseCode) {
		// Shows index in course
		
		// Student adds index
		
		// Appends to ArrayList
	}
	public void dropCourse(String courseCode) {
		// Remove from ArrayList
	}
	public Student getUser() {
		return user;
	}
	
	public void printRegistered() {
		// print course code, course name, index of registered courses
		System.out.println("Courses Registered for " + user.getFirstName() + " " + user.getLastName() + " (" + user.getMatricNumber() + "):");
		System.out.println("================================================================");
		System.out.println("Course Code\tCourse Name \t\t\t\tIndex");
		System.out.println("================================================================");
		for(RegisteredCourse course: user.getCourseList()) {
			System.out.println(course.getCourse().getCourseCode() + "\t\t" + course.getCourse().getCourseName() + "\t" + course.getIndex().getIndexNumber());
		}
		System.out.println("");
	}
	
	public boolean checkVacancies(Course course, int index) {
		for (Index id: course.getIndexList()) {
			if (id.getIndexNumber() == index) {
				System.out.println("================================================================");
				System.out.println("Course Code\t\t\tIndex\tVacancy");
				System.out.println("================================================================");
				System.out.println(course.getCourseCode() + "\t\t" + index + "\t\t" + id.getVacancies());
				System.out.println("");
				return true;
			}
		}
		System.out.println("Invalid index number! Please try again.");
		System.out.println("");
		return false;
	}
	
	public Course getCourseFromCourseCode(String courseCode) {
		for (Course course: Database.courseList) {
			if (course.getCourseCode().equals(courseCode)) {
				return course;
			}
		}
		System.out.println("Invalid course code! Please try again.");
		System.out.println("");
		return null;
	}
	
	public void printAllIndexesFromCourse(Course course) {
		System.out.println("\nCourse Code: "+ course.getCourseCode());
		System.out.println("Course Name: "+ course.getCourseName());
		System.out.println("================================================================");
		System.out.println("Indexes Available");
		System.out.println("================================================================");
		for (Index id: course.getIndexList()) {
			System.out.println(id.getIndexNumber());
		}
		System.out.println("");
	}
}
