package control;

import java.util.ArrayList;
import database.Database;
import entities.Course;
import entities.Index;
import entities.RegisteredCourse;
import entities.Student;
import java.util.Scanner;

public class StudentManager extends Manager{
	private Student user;
	
	public StudentManager(Student user) {
		this.user = user;
		// Database.initialise(); // To test out the UI
	}
	
	public void addCourse(String courseCode) {
		Course course = null;
		
		// Shows indexes in course and their vacancies
		System.out.println("Index\tVacancies");
		for (Course c : Database.courseList) {
			if (c.getCourseCode().equals(courseCode)) {
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
			int choice = -1;
			System.out.print("\nEnter an Index to enroll in: ");
			Scanner sc = new Scanner(System.in);
			try {
				choice = sc.nextInt();
			}
			catch (Exception InputMismatchException) {  // Error handling
				System.out.println("Invalid Input!");
				sc.next();
				continue;
			}
			sc.close();
			for (Index i : course.getIndexList()) {
				if (choice == i.getIndexNumber()) {
					index = i;
					
					// Check if index/course is already enrolled, exits if yes
					for (RegisteredCourse registered : user.getCourseList()) {
						if (course.getCourseCode().equals(registered.getCourse().getCourseCode())) {
							System.out.println("You have already enrolled in this course!");
							System.out.println("Returning back to main menu...");
							return;
						}
					}
					
					// Check if there are any timetable clashes, exits if yes
					if (ScheduleManager.willClash(index, user.getCourseList()) == true) {
						System.out.println("New Index Clashes with your current indexes!");
						System.out.println("Returning back to main menu...");
						return;
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
						System.out.println("Index " + choice + " has 0 vacancy left.");
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
		
		// Enroll student in the index
		ArrayList<RegisteredCourse> newCourseList = user.getCourseList();
		RegisteredCourse newCourse = new RegisteredCourse(onWaitList, course, index, user);
		newCourseList.add(newCourse);
		user.setCourseList(newCourseList);
		

	}
	
	public void dropCourse(String courseCode) {	
		ArrayList<RegisteredCourse> courses = user.getCourseList();
		Course droppedCourse = findCourse(courseCode);
		
		for (RegisteredCourse course : courses) {
			if (course.getCourse().getCourseCode().equals(courseCode)) {
				// Remove Student from the studentList in Index
				ArrayList<Student> updated = course.getIndex().getStudentList();
				updated.remove(user);
				course.getIndex().setStudentList(updated);
				// Update Student's number of AUs
				user.setNumAU(user.getNumAU() - droppedCourse.getAU());
				// Remove course from student's list of registered courses
				courses.remove(course); 
				System.out.println("Course Code " + courseCode + " successfully dropped.");
				
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
	
	public void printRegistered(Student user) {
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
		Course selectedCurrentCourse = findCourse(courseCode);
		
		// if course code is found in database
		if (selectedCurrentCourse != null) {
			courseExists = true;
			// get all indexes from course
			ArrayList<Index> courseIndexes = getAllIndexesFromCourse(selectedCurrentCourse);
			
			System.out.println("\nCourse Code: " + selectedCurrentCourse.getCourseCode());
			System.out.println("Course Name: " + selectedCurrentCourse.getCourseName());

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
	
	public boolean changeIndex(int currentIndex) {
		// TODO: check if fail test cases 
		// initialise index number does not exist
		boolean indexExists = false;
		// find the course which student wants to change currentIndex
		Index selectedCurrentIndex = findIndex(currentIndex);
		Course selectedCurrentCourse = getCourseFromIndex(selectedCurrentIndex);
		
		// if current index does not exist in database
		if (selectedCurrentIndex == null) {
			return indexExists;
		}
		
		// check if current index exists in student's list of registered courses
		ArrayList<RegisteredCourse> courses = user.getCourseList();
		for (RegisteredCourse registered : courses) {
			if (currentIndex == registered.getIndex().getIndexNumber()) {
				break;
			}
			else {
				selectedCurrentIndex = null;
				selectedCurrentCourse = null;
				System.out.println("\nIndex not registered! Please try again.");
				System.out.println("");
			}
		}
		
		// if index already registered by student
		if (selectedCurrentCourse != null) {
			int newIndex = 0;
			Index selectedNewIndex = null;
			Course selectedNewCourse = null;
			do {
				// display all indexes for the course
				checkVacancies(selectedCurrentCourse.getCourseCode());
			
				// let student input new index to change to
				System.out.print("Enter the new Index you want to change to (Enter 0 to go back): ");
				Scanner sc = new Scanner(System.in);
				try {
				newIndex = sc.nextInt();
				}
				catch (Exception InputMismatchException) {  // Error handling
					System.out.println("Invalid Input!");
					sc.next();
					continue;
				}
				// cannot sc.close() here if not UI will have error
				if (newIndex == 0) {
					System.out.println("\nGoing back...");
					System.out.println("");
					return indexExists;
				}
				
				// get new index and course based on student's input
				selectedNewIndex = findIndex(newIndex);
				selectedNewCourse = getCourseFromIndex(selectedNewIndex);
				
				// if new index does not exist in database
				if (selectedNewIndex == null) {
					continue;
				}
				
				//check if new index is the same as current index
				if (newIndex== selectedCurrentIndex.getIndexNumber()) {
					// continue if user tries to change to same index
					System.out.println("\nYou are already enrolled in this Index!");
					System.out.println("Please select a new Index.");
					System.out.println("");
					continue;
				}
				
				// check if new index is in the same course
				if (selectedNewCourse != selectedCurrentCourse) {
					System.out.println("\nNew Index does not exist in the Course.");
					System.out.println("Please select a new Index.");
					continue;
				}
				
				// check if new index has vacancy 
				if (selectedNewIndex.getVacancies() == 0) {
					// continue if 0 vacancy
					System.out.println("\nIndex " + newIndex + " has 0 vacancy left.");
					System.out.println("Please select a new Index.");
					System.out.println("");
					continue;
				}
				// if new index has vacancy
				// check if new index clash with any other registered indexes, continue if yes
				else if (ScheduleManager.willClash(selectedNewIndex, user.getCourseList()) == true) {
					System.out.println("\nNew Index Clashes with your current indexes!");
					System.out.println("Please select a new Index.");
					System.out.println("");
					continue;
				}
				indexExists = true;
			} while (!indexExists);
			
			
			if (indexExists) {
				// enroll student to newIndex
				// decrease vacancy of newIndex by 1
				selectedNewIndex.setVacancies(selectedNewIndex.getVacancies()-1);
				// add student to studentList in newIndex
				ArrayList<Student> newIndexStudentList = selectedNewIndex.getStudentList();
				newIndexStudentList.add(user);
				selectedNewIndex.setStudentList(newIndexStudentList);
				// add index to  student's list of registered index
				RegisteredCourse newRegisteredIndex = new RegisteredCourse(false, selectedNewCourse, selectedNewIndex, user);
				courses.add(newRegisteredIndex);
				
				// drop student from currentIndex
				// remove student from studentList in currentIndex
				ArrayList<Student> currentIndexStudentList = selectedCurrentIndex.getStudentList();
				currentIndexStudentList.remove(user);
				selectedCurrentIndex.setStudentList(currentIndexStudentList);
				// remove index from student's list of registered index
				for (RegisteredCourse registered : courses) {
					if (registered.getIndex() == selectedCurrentIndex) {
						courses.remove(registered);
					}
				}
				// if currentIndex waitlist is empty 
				if (selectedCurrentIndex.getWaitList().isEmpty()) {
					// increase vacancy of currentIndex by 1
					selectedCurrentIndex.setVacancies(selectedCurrentIndex.getVacancies() + 1);
				}
				else {
					// add course for first student in waitlist
					Student waiting = selectedCurrentIndex.getWaitList().get(0);
					StudentManager enrollUser = new StudentManager(waiting);
					enrollUser.addCourse(selectedCurrentCourse.getCourseCode());
					// notify enrolled student 
					// TODO
				}
				System.out.println("\nIndex " + currentIndex + " from Course Code " + selectedCurrentCourse.getCourseCode() + " has been successfully changed to new Index " + newIndex + ".");
				System.out.println("");
				printRegistered(user);
			}
			
		}
		return indexExists;
	}
	
	public boolean swapIndex(int oldIndex, String matricNumber) {
		// TODO: check if fail test cases 
		// initialise swap fail
		boolean canSwap = false;
		// find the peer to change student's currentIndex to peer's newIndex     
		Student peer = getStudentFromMatricNumber(matricNumber);
		// find the course user wants to change currentIndex
		Index userIndex = findIndex(oldIndex);
		Course userCourse = getCourseFromIndex(userIndex);
		
		// if currentIndex does not exist or peer does not exist in database
		if (userIndex == null) {
			System.out.println("Index does not exist in database! Please try again.");
			System.out.println("");
			return canSwap;
		}
		if (peer == null) {
			System.out.println("Peer does not exist in database! Please try again.");
			System.out.println("");
			return canSwap;
		}
		
		// initialise user's and peer's registered courses 
		ArrayList<RegisteredCourse> userRegCourses = user.getCourseList();
		ArrayList<RegisteredCourse> peerRegCourses = peer.getCourseList();
		
		// check if currentIndex exists in user's list of registered courses
		for (RegisteredCourse registered : userRegCourses) {
			if (oldIndex == registered.getIndex().getIndexNumber()) {
				break;
			}
			else {
				userIndex = null;
				userCourse = null;
				System.out.println("\nIndex not registered! Please try again.");
				System.out.println("");
			}
		}
		
		// if index already registered by user
		if (userCourse != null) {
			int newIndex = 0;
			Index peerIndex = null;
			Course peerCourse = null;
			do {
				// let user input new index to change to
				System.out.print("Enter the new Index you want to change to (Enter 0 to go back): ");
				Scanner sc = new Scanner(System.in);
				newIndex = sc.nextInt();
				// cannot sc.close() here if not UI will have error
				if (newIndex == 0) {
					System.out.println("\nGoing back...");
					System.out.println("");
					return canSwap;
				}
				
				// check if peerIndex is in peer's list of registered courses
				
				boolean peerCourseExists = true;
				for (RegisteredCourse registered : peerRegCourses) {
					// selected index exists in peer's registered courses
					if (newIndex == registered.getIndex().getIndexNumber()) {
						break;
					}
					else {
						// selected peerIndex does not exist in peer's registered courses
						System.out.println("\nIndex not registered by peer! Please try again.");
						System.out.println("");
						peerCourseExists = false;
					}
				}
				// if peer does not have selected index
				if (!peerCourseExists) {
					continue;
				}
				
				// get peer's index and course based on user's input
				peerIndex = findIndex(newIndex);
				peerCourse = getCourseFromIndex(peerIndex);
				
				//check if input index is the same as current index
				if (newIndex == userIndex.getIndexNumber()) {
					// continue if user tries to change to same index
					System.out.println("\nYou are already enrolled in this Index!");
					System.out.println("Please select a new Index.");
					System.out.println("");
					continue;
				}
				
				// check if peer index is in the same course
				if (peerCourse != userCourse) {
					System.out.println("\nNew Index does not exist in the Course.");
					System.out.println("Please select a new Index.");
					continue;
				}
				
				// check if peer's index clash with any other user's registered indexes, continue if yes
				if (ScheduleManager.willClash(peerIndex, user.getCourseList()) == true) {
					System.out.println("\nNew Index Clashes with your current indexes!");
					System.out.println("Please select a new Index.");
					System.out.println("");
					continue;
				}
				
				// check if user's index clash with any other peer's registered indexes, continue if yes
				if (ScheduleManager.willClash(userIndex, peer.getCourseList()) == true) {
					System.out.println("\nCurrent Index Clashes with your peers' indexes!");
					System.out.println("Please select a new Index.");
					System.out.println("");
					continue;
				}
					
				canSwap = true;
			} while (!canSwap);
			
			if (canSwap) {
				// enroll user to peerIndex
				// add user to studentList in peerIndex
				ArrayList<Student> peerIndexStudentList = peerIndex.getStudentList();
				peerIndexStudentList.add(user);
				peerIndex.setStudentList(peerIndexStudentList);
				// add peer index to student's list of registered index
				RegisteredCourse peerRegisteredIndex = new RegisteredCourse(false, peerCourse, peerIndex, user);
				userRegCourses.add(peerRegisteredIndex);
				
				// drop user from currentIndex
				// remove user from studentList in currentIndex
				ArrayList<Student> currentIndexStudentList = userIndex.getStudentList();
				currentIndexStudentList.remove(user);
				userIndex.setStudentList(currentIndexStudentList);
				// remove index from student's list of registered index
				for (RegisteredCourse registered : userRegCourses) {
					if (registered.getIndex() == userIndex) {
						userRegCourses.remove(registered);
					}
				}
				
				// enroll peer to currentIndex
				// add peer to studentList in currentIndex
				currentIndexStudentList.add(peer);
				// add user index to peer's list of registered index
				RegisteredCourse userRegisteredIndex = new RegisteredCourse(false, userCourse, userIndex, peer);
				peerRegCourses.add(userRegisteredIndex);
				// drop peer from peerIndex
				// remove peerIndex from peer's list of registered index
				for (RegisteredCourse registered : peerRegCourses) {
					if (registered.getIndex() == userIndex) {
						userRegCourses.remove(registered);
					}
				}
				
				System.out.println("\nIndex " + oldIndex + " from Course Code " + userCourse.getCourseCode() + " has been successfully swapped with " + peer.getMatricNumber() + "'s Index " + newIndex + ".");
				System.out.println("");
				printRegistered(user);
			}
		}
		return canSwap;
	}
	
	
	
	// --- helper methods---
	private static Course findCourse(String courseCode) {
		// Finds the Course object from courseCode
		for (Course course: Database.courseList) {
			if (course.getCourseCode().equals(courseCode)) {
				return course;
			}
		}
		System.out.println("\nInvalid Course Code! Please try again.");
		System.out.println("");
		return null;
	}
	
	private static Index findIndex(int index) {
		// Finds the Index object from index number
		for (Course c : Database.courseList) {
			for (Index i : c.getIndexList())
				if (i.getIndexNumber() == index) {
					return i;
				}
		}
		System.out.println("\nInvalid Index! Please try again.");
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

	
	private static Course getCourseFromIndex(Index index) {
		Course course = null;
		for (Course c: Database.courseList) {
			if (c.getIndexList().contains(index)) {
				course = c;
			}
		}
		return course;
	}
	
	private static Student getStudentFromMatricNumber(String matricNumber) {
		Student student = null;
		for (Student s: Database.studentList) {
			if (s.getMatricNumber().equals(matricNumber)) {
				student = s;
			}
		}
		return student;
	}
}
