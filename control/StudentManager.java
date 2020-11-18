package control;

import java.util.ArrayList;
import database.Database;
import entities.Course;
import entities.Index;
import entities.RegisteredCourse;
import entities.Student;
import java.util.Scanner;

public class StudentManager {
	private Student user;
	
	public StudentManager(Student user) {
		this.user = user;
		// Database.initialise(); // To test out the UI
	}
	
	public static Index findIndex(int index) {
		// Finds the Index object from index number
		for (Course c : Database.courseList) {
			for (Index i : c.getIndexList())
				if (i.getIndexNumber() == index) {
					return i;
				}
		}
		System.out.println("Invalid index code! Please try again.");
		System.out.println("");
		return null;
	}
	
	public void addCourse(String courseCode) {
		Course course = null;
		
		// Shows indexes in course and their vacancies
		System.out.println("Index\tVacancies");
		for (Course c : Database.courseList) {
			if (c.getCourseCode() == courseCode) {
				course = c;
				for (Index index : course.getIndexList()) {
					System.out.print(index.getIndexNumber());
					System.out.println("\t" + index.getVacancies());
				}
				break;
			}
			// Exits if courseCode is not found
			System.out.println("No course found! Returning back to main menu...");
			return;
			
		}
		
		// Student inputs index
		boolean userinput = true;
		boolean onWaitList = false;
		Index index = null;
		
		while(userinput) {
			System.out.print("\nEnter an Index to enroll in: ");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			for (Index i : course.getIndexList()) {
				if (choice == i.getIndexNumber()) {
					index = i;
					
					// Check if index/course is already enrolled, exits if yes
					for (RegisteredCourse registered : user.getCourseList()) {
						if (course.getCourseCode() == registered.getCourse().getCourseCode()) {
							System.out.println("You have already enrolled in this course!");
							System.out.println("Returning back to main menu...");
							sc.close();
							return;
						}
					}
					
					// Check if index has vacancies
					if (index.getVacancies() != 0 ) {
						// Decrease vacancy of index by 1
						index.setVacancies(index.getVacancies() - 1);
						// Add course AUs
						user.setNumAU(user.getNumAU() + course.getAU());
						userinput = false;
						break;
					}
					else {
						System.out.println("Index " + choice + " has 0 vacancies left.");
						System.out.println("You have been put on the waitlist.");
						// Enqueue student in the wait list of the index
						index.addWaitList(user);
						
						onWaitList = true;
						userinput = false;
						break;
					}
				}
			}
			// If user input is not in the index list, ask for input again
			if (userinput)
				System.out.println("Please enter a valid index!");
		}
		
		// Check if there are any clashes
		//TODO
		
		// Enroll student in the index
		ArrayList<RegisteredCourse> newCourseList = user.getCourseList();
		RegisteredCourse newCourse = new RegisteredCourse(onWaitList, course, index, user);
		newCourseList.add(newCourse);
		user.setCourseList(newCourseList);

	}
	
	public void dropCourse(String courseCode) {	
		ArrayList<RegisteredCourse> courses = user.getCourseList();
		Course droppedCourse = getCourseFromCourseCode(courseCode);
		
		for (RegisteredCourse course : courses) {
			if (course.getCourse().getCourseCode() == courseCode) {
				// Remove Student from the studentList in Index
				ArrayList<Student> updated = course.getIndex().getStudentList();
				updated.remove(user);
				course.getIndex().setStudentList(updated);
				// Update Student's number of AUs
				user.setNumAU(user.getNumAU() - droppedCourse.getAU());
				// Remove course from student's list of registered courses
				courses.remove(course);
				System.out.println("Course Code" + courseCode + "successfully dropped.");
				
				// Increase vacancy by 1 if wait list is empty
				if (course.getIndex().getWaitList().isEmpty()) {
					course.getIndex().setVacancies(course.getIndex().getVacancies() + 1);
				}
				else {
					// Add course for first student in the wait list
					Student waiting = course.getIndex().getWaitList().get(0);
					StudentManager enrollUser = new StudentManager(waiting);
					enrollUser.addCourse(courseCode);
					
					// Notify enrolled student
					// TODO
				}
				
				return;
			}
		}
		// If course code is not found
		System.out.println("Invalid Course Code! Please try again.");
		return;
	}
	
	/********** Test Function ********************
	public static void main(String[] args) {
		Database.initialise();
		
		StudentManager student = new StudentManager(Database.studentList.get(0));
		student.addCourse("2002");
		
		System.out.println(student.getUser().getCourseList().get(0).getCourse().getCourseName());
		System.out.println(student.getUser().getCourseList().get(0).getCourse().getIndexList().get(1).getWaitList());
		System.out.println(Database.courseList.get(0).getIndexList().get(1).getVacancies());
		System.out.println(student.getUser().getNumAU());
	}
	*********************************************/
	
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
	
	public boolean checkVacancies(String courseCode) {
		// initialise course does not exist
		boolean courseExists = false;
		// find the course that student wants to check
		Course selectedCourse = getCourseFromCourseCode(courseCode);
		
		// if course code is found
		if (selectedCourse != null) {
			// get all indexes from course
			courseExists = true;
			ArrayList<Index> courseIndexes = getAllIndexesFromCourse(selectedCourse);
			
			System.out.println("\nCourse Code: " + selectedCourse.getCourseCode());
			System.out.println("Course Name: " + selectedCourse.getCourseName());

			// print all the indexes and their respective vacancies and waitLists
			System.out.println("================================================================");
			System.out.println("Index\t\tVacancy\t\tWaitlist");
			System.out.println("================================================================");
			for (Index index: courseIndexes) {
				System.out.println(index.getIndexNumber() + "\t\t" + index.getVacancies() + "\t\t" + index.getWaitListSize());
			}
			System.out.println("");
		}
		
		return courseExists;
	}
	
	// --- helper methods---
	public Course getCourseFromCourseCode(String courseCode) {
		for (Course course: Database.courseList) {
			if (course.getCourseCode().equals(courseCode)) {
				return course;
			}
		}
		System.out.println("\nInvalid Course Code! Please try again.");
		System.out.println("");
		return null;
	}
	
	
	private ArrayList<Index> getAllIndexesFromCourse(Course course) {
		ArrayList<Index> courseIndexes = new ArrayList<Index>();
		for (Index id: course.getIndexList()) {
			courseIndexes.add(id);
		}
		return courseIndexes;
	}
	
	
