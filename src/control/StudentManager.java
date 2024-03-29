/**
 * Manager for Student
 * @author Stanley Ho, Cheah Jing Feng
 * @version 1.0
 * @since 2020-11-20
 */

package control;
import java.util.ArrayList;
import entities.Course;
import entities.Database;
import entities.Index;
import entities.RegisteredCourse;
import entities.Student;
import java.util.Scanner;

public class StudentManager extends Manager {
	/**
	 * Stores which Student is using the Manager
	 */
	private Student user;
	
	/**
	 * Initializing the Student
	 * @param user Student
	 */
	public StudentManager(Student user) {
		super();
		this.user = user;
		// Database.initialise(); // To remove
	}
	
	/**
	 * Gets the Student object
	 * @return Student Object
	 */
	public Student getUser() {
		return user;
	}
	
	/**
	 * Adds a Course with specified Index to Student's list of Registered Courses
	 * @param courseCode Course Code
	 */
	@SuppressWarnings("resource")
	public void addCourse(String courseCode) {
		// Shows indexes in course and their vacancies
		boolean courseExists = checkVacancies(courseCode);
		// Exits if courseCode is not found
		if (!courseExists) {
			System.out.println("Returning back to main menu...");
			return;
		}
		
		Course course = Database.findCourse(courseCode);
		
		// Student inputs index
		boolean userinput = true;
		boolean onWaitList = false;
		Index index = null;
		Scanner sc = new Scanner(System.in);
		
		while(userinput) {
			int choice = -1;
			System.out.print("\nEnter an Index to enroll in: ");
			try {
				choice = sc.nextInt();
			}
			catch (Exception InputMismatchException) {  // Error handling
				System.out.println("Invalid Input!");
				sc.next();
				continue;
			}
			for (Index i : course.getIndexList()) {
				if (choice == i.getIndexNumber()) {
					index = i;
					
					// Check if index/course is already enrolled, exits if yes
					for (RegisteredCourse registered : user.getCourseList()) {
						if (course.getCourseCode().equals(registered.getCourse().getCourseCode())) {
							System.out.println("You have already enrolled in this course!");
							System.out.println("Returning back to main menu...");
							System.out.println("");
							return;
						}
					}
					
					// Check if there are any timetable clashes, exits if yes
					if (ScheduleManager.willClash(index, user.getCourseList()) == true) {
						System.out.println("Returning back to main menu...");
						System.out.println("");
						return;
					}
					
					// Check number of AUs of student, add course AU to student's AUs 
					int newAU = user.getNumAU() + course.getAU();
					if (newAU > 21) {
						System.out.println("Maximum AU exceeded! Course " + courseCode + " not registered.");
						System.out.println("Returning back to main menu...");
						System.out.println("");
						return;
					}
					
					// confirm whether want to add course
					PrintManager.printSessions(index);
					System.out.println("\nConfirm to add course? (Y/N)");
					String confirm;
					try {
						confirm = sc.next().toUpperCase();
					}
					catch (Exception InputMismatchException) {  // Error handling
						System.out.println("Invalid Input!");
						System.out.println("Returning back to main menu...");
						sc.next();
						return;
					}
					if (confirm.equals("N")) {
						System.out.println("Course " + courseCode + " not added.");
						System.out.println("Returning back to main menu...");
						return;
					}
						
					else if (!confirm.equals("Y")) {
						System.out.println("Please confirm by entering Y/N.");
						System.out.println("Course " + courseCode + " not added.");
						System.out.println("Returning back to main menu...");
						return;
					}
					
					// set new AU of student (even if need to be on waitlist)
					user.setNumAU(newAU);
					
					// Check if index has vacancies
					if (index.getVacancies() != 0 ) {
						// Decrease vacancy of index by 1
						index.setVacancies(index.getVacancies() - 1);
						userinput = false;
						break;
					}
					else {
						System.out.println("Index " + choice + " has 0 vacancy left.");
						System.out.println("You have been put on the waitlist.");
						System.out.println("");
						// Enqueue student in the wait list of the index
						index.addWaitList(user);
						onWaitList = true;
						userinput = false;
						break;
					}
				}
			}
			// If user input is not in the index list, ask for input again
			if (userinput) {
				System.out.println("Please enter a valid index!");
				System.out.println("");
			}
		}
			
		// Enroll student in the index
		ArrayList<RegisteredCourse> newCourseList = user.getCourseList();
		RegisteredCourse newCourse = new RegisteredCourse(onWaitList, course, index, user);
		newCourseList.add(newCourse);
		user.setCourseList(newCourseList);
		if (!onWaitList) {
			index.addStudentList(user);
			System.out.println("Index " + index.getIndexNumber() + " of Course Name " + course.getCourseName() + " (" +course.getCourseCode() + ")" + " successfully added!\n");
		}
		Database.updateCourseDB(course);
		// Update Student Database
		Database.updateStudentDB(user);
		PrintManager.printRegistered(user);
	}
	
	/**
	 * Drops a Course with specified Index from Student's list of Registered Courses
	 * @param courseCode Course Code
	 */
	@SuppressWarnings("resource")
	public void dropCourse(String courseCode) {	
		ArrayList<RegisteredCourse> courses = user.getCourseList();
		Course droppedCourse = Database.findCourse(courseCode);
		
		if (droppedCourse == null) return; // return if droppedCourse is not found
		
		for (RegisteredCourse course : courses) {
			if (!course.getOnWaitlist()) {
				if (course.getCourse().getCourseCode().equals(courseCode)) {
					Index droppedIndex = Database.findIndex(course.getIndex().getIndexNumber());
					// Remove Student from the studentList in Index
					droppedIndex.removeStudentList(user.getMatricNumber());
					// Update Student's number of AUs
					user.setNumAU(user.getNumAU() - droppedCourse.getAU());
					// Remove course from student's list of registered courses
					courses.remove(course); 
					
					// Update Course Database
					Database.updateCourseDB(droppedCourse);
					// Update Student Database
					Database.updateStudentDB(user);
					
					// confirm whether want to drop course
					PrintManager.printSessions(droppedIndex);
					System.out.println("\nConfirm to drop course? (Y/N)");
					Scanner sc = new Scanner(System.in);
					String confirm;
					try {
						confirm = sc.next().toUpperCase();
					}
					catch (Exception InputMismatchException) {  // Error handling
						System.out.println("Invalid Input!");
						System.out.println("Returning back to main menu...");
						sc.next();
						return;
					}
					if (confirm.equals("N")) {
						System.out.println("Course " + courseCode + " not dropped.");
						System.out.println("Returning back to main menu...");
						return;
					}
						
					else if (!confirm.equals("Y")) {
						System.out.println("Please confirm by entering Y/N.");
						System.out.println("Course " + courseCode + " not dropped.");
						System.out.println("Returning back to main menu...");
						return;
					}
					
					System.out.println("Course Code " + courseCode + " successfully dropped.\n");
					PrintManager.printRegistered(user);
				
					// Increase vacancy by 1 if wait list is empty
					if (droppedIndex.getWaitList().isEmpty()) {
						droppedIndex.setVacancies(droppedIndex.getVacancies() + 1);
					}
					else {
						// Add course for first student in the wait list
						Student waiting = droppedIndex.getWaitList().get(0);
						ArrayList<RegisteredCourse> newCourseList = waiting.getCourseList();
						RegisteredCourse newCourse = new RegisteredCourse(false, droppedCourse, droppedIndex, waiting);
						for (RegisteredCourse old : newCourseList) {
							if (old.getIndex().getIndexNumber() == droppedIndex.getIndexNumber()) {
								newCourseList.remove(old);
								newCourseList.add(newCourse);
								break;
							}
						}
						waiting.setCourseList(newCourseList);
						droppedIndex.addStudentList(waiting);
						droppedIndex.removeWaitList(waiting.getMatricNumber());
	
						// Update Student Database
						Database.updateStudentDB(waiting);
						
						// Notify enrolled student
						// change first argument for testing
						NotifManager.sendEmail(waiting.getEmail(), waiting, courseCode, "Course " + courseCode + " has been successfully registered and you have been removed from the waitlist.");
					}
					// Update Course Database
					Database.updateCourseDB(droppedCourse);
					return;
				}
			}
		}
		// Exits if course code is not registered
		System.out.println("Course " + courseCode + " not registered! Please try again.");
		System.out.println("Returning back to main menu...");
		return;
	}
	
	/**
	 * Change index from a user's registered course to another index
	 * @param currentIndex Current Index
	 * @return boolean indexExists
	 */
	@SuppressWarnings("resource")
	public boolean changeIndex(int currentIndex) {
		// initialise index number does not exist
		boolean indexExists = false;
		// find the course which student wants to change currentIndex
		Index selectedCurrentIndex = Database.findIndex(currentIndex);
		Course selectedCurrentCourse = getCourseFromIndex(selectedCurrentIndex);
		
		// if current index does not exist in database
		if (selectedCurrentIndex == null) {
			System.out.println("Selected Index does not exist. Please try again!");
			return indexExists;
		}
		
		ArrayList<RegisteredCourse> courses = user.getCourseList();
		// if student has no registered courses
		if (courses.isEmpty()) {
			System.out.println("\nNo Courses Registered! Please register a course first.");
			System.out.println("");
			return indexExists;
		}
		
		// check if current index exists in student's list of registered courses
		boolean courseRegistered = false;
		for (RegisteredCourse registered : courses) {
			// check if current index is on the wait list
			if (!registered.getOnWaitlist()) {
				if (currentIndex == registered.getIndex().getIndexNumber()) {
					courseRegistered = true;
					break;
				}
			}
		}
		if (!courseRegistered) {
			selectedCurrentIndex = null;
			selectedCurrentCourse = null;
			System.out.println("\nIndex not registered! Please try again.");
			System.out.println("");
		}
		
		Scanner sc = new Scanner(System.in);
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
				try {
					newIndex = sc.nextInt();
				}
				catch (Exception InputMismatchException) {  // Error handling
					System.out.println("Invalid Input! Please try again.");
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
				selectedNewIndex = Database.findIndex(newIndex);
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
					continue;
				}
				// if new index has vacancy
				// check if new index clash with any other registered indexes, continue if yes
				else if (ScheduleManager.willClash(selectedNewIndex, user.getCourseList()) == true) {
					System.out.println("Please select a new Index.");
					continue;
				}
				indexExists = true;
			} while (!indexExists);
			
			if (indexExists) {
				// confirm whether want to change index
				System.out.println("\nCurrent Index: ");
				PrintManager.printSessions(selectedCurrentIndex);
				System.out.println("\nNew Index: ");
				PrintManager.printSessions(selectedNewIndex);
				System.out.println("\nConfirm to change Index? (Y/N)");
				String confirm;
				try {
					confirm = sc.next().toUpperCase();
				}
				catch (Exception InputMismatchException) {  // Error handling
					System.out.println("Invalid Input! Pleaes try again.");
					sc.next();
					return false;
				}
				if (confirm.equals("N")) {
					System.out.println("Index " + currentIndex + " not swapped with peer's new Index "+ newIndex);
					System.out.println("Please try again.");
					return false;
				}
					
				else if (!confirm.equals("Y")) {
					System.out.println("Please confirm by entering Y/N.");
					System.out.println("Index " + currentIndex + " not swapped with peer's new Index "+ newIndex);
					System.out.println("Please try again.");
					return false;
				}
				// enroll student to newIndex
				// decrease vacancy of newIndex by 1
				selectedNewIndex.setVacancies(selectedNewIndex.getVacancies()-1);
				// add student to studentList in newIndex
				selectedNewIndex.addStudentList(user);
				// add index to  student's list of registered index
				RegisteredCourse newRegisteredIndex = new RegisteredCourse(false, selectedNewCourse, selectedNewIndex, user);
				courses.add(newRegisteredIndex);
				
				// drop student from currentIndex
				// remove student from studentList in currentIndex
				selectedCurrentIndex.removeStudentList(user.getMatricNumber());
				// remove index from student's list of registered index
				for (RegisteredCourse registered : courses) {
					if (registered.getIndex() == selectedCurrentIndex) {
						courses.remove(registered);
						break;
					}
				}
				// if currentIndex waitlist is empty 
				if (selectedCurrentIndex.getWaitList().isEmpty()) {
					// increase vacancy of currentIndex by 1
					selectedCurrentIndex.setVacancies(selectedCurrentIndex.getVacancies() + 1);
				}
				else {
					
					// Add course for first student in the wait list
					Student waiting = selectedCurrentIndex.getWaitList().get(0);
					ArrayList<RegisteredCourse> newCourseList = waiting.getCourseList();
					RegisteredCourse newCourse = new RegisteredCourse(false, selectedCurrentCourse, selectedCurrentIndex, waiting);
					for (RegisteredCourse old : newCourseList) {
						if (old.getIndex().getIndexNumber() == selectedCurrentIndex.getIndexNumber()) {
							newCourseList.remove(old);
							newCourseList.add(newCourse);
							break;
						}
					}
					waiting.setCourseList(newCourseList);
					selectedCurrentIndex.addStudentList(waiting);
					selectedCurrentIndex.removeWaitList(waiting.getMatricNumber());
	
					// Update Course Database
					Database.updateCourseDB(selectedCurrentCourse);
					// Update Student Database
					Database.updateStudentDB(waiting);
					
					// Notify enrolled student
					NotifManager.sendEmail(waiting.getEmail(), waiting, selectedCurrentCourse.getCourseCode(), "Course " + selectedCurrentCourse.getCourseCode() + " has been successfully registered and you have been removed from the waitlist.");
				}
				// Update Course Database
				Database.updateCourseDB(selectedCurrentCourse);
				Database.updateCourseDB(selectedNewCourse);
				// Update Student Database
				Database.updateStudentDB(user);
				System.out.println("\nIndex " + currentIndex + " from Course Code " + selectedCurrentCourse.getCourseCode() + " has been successfully changed to new Index " + newIndex + ".");
				System.out.println("");
				PrintManager.printRegistered(user);
			}
		}
		return indexExists;
	}
	
	/**
	 * Swap index from a user's registered course with another peer's registered index
	 * @param oldIndex Old Index
	 * @param matricNumber Matriculation Number
	 * @return boolean canSwap
	 */
	@SuppressWarnings("resource")
	public boolean swapIndex(int oldIndex, String matricNumber) {
		// initialise swap fail
		boolean canSwap = false;
		// find the peer to change student's currentIndex to peer's newIndex     
		Student peer = getStudentFromMatricNumber(matricNumber);
		// find the course user wants to change currentIndex
		Index userIndex = Database.findIndex(oldIndex);
		Course userCourse = getCourseFromIndex(userIndex);
		
		// if currentIndex and peer do not exist in database
		if (userIndex == null && peer == null) {
			System.out.println("\nBoth Peer and Index do not exist in database! Please try again.");
			System.out.println("");
			return canSwap;
		}
		// if currentIndex or peer does not exist in database
		if (userIndex == null) {
			System.out.println("\nIndex does not exist in database! Please try again.");
			System.out.println("");
			return canSwap;
		}
		if (peer == null) {
			System.out.println("\nPeer does not exist in database! Please try again.");
			System.out.println("");
			return canSwap;
		}
		
		// initialise user's and peer's registered courses 
		ArrayList<RegisteredCourse> userRegCourses = user.getCourseList();
		ArrayList<RegisteredCourse> peerRegCourses = peer.getCourseList();
		
		// if student has no registered courses
		if (userRegCourses.isEmpty()) {
			System.out.println("\nNo Courses Registered! Please register a course first.");
			System.out.println("");
			return canSwap;
		}
		
		// check if current index exists in student's list of registered courses
		boolean courseRegistered = false;
		for (RegisteredCourse registered : userRegCourses) {
			// check if current index is on the wait list
			if (!registered.getOnWaitlist()) {
				if (oldIndex == registered.getIndex().getIndexNumber()) {
					courseRegistered = true;
					break;
				}
			}
		}
		if (!courseRegistered) {
			userIndex = null;
			userCourse = null;
			System.out.println("\nIndex not registered! Please try again.");
			System.out.println("");
		}
		
		Scanner sc = new Scanner(System.in);
		// if index already registered by user
		if (userCourse != null) {
			int newIndex = 0;
			Index peerIndex = null;
			Course peerCourse = null;
			do {
				// let user input new index to change to
				System.out.print("Enter the new Index you want to change to (Enter 0 to go back): ");
				
				try {
					newIndex = sc.nextInt();
				} 
				catch (Exception InputMismatchException) {  // Error handling
					System.out.println("Invalid Input! Please try again.");
					sc.next();
					continue;
				}
				if (newIndex == 0) {
					System.out.println("\nGoing back...");
					System.out.println("");
					return canSwap;
				}
				
				// check if peerIndex is in peer's list of registered courses
				boolean peerCourseExists = false;
				for (RegisteredCourse registered : peerRegCourses) {
					// check if selected index is on peer's waitlist
					if (!registered.getOnWaitlist()) {
						if (newIndex == registered.getIndex().getIndexNumber()) {
							peerCourseExists = true;
							break;
						}
					}
				}
				
				// get peer's index and course based on user's input
				peerIndex = Database.findIndex(newIndex);
				peerCourse = getCourseFromIndex(peerIndex);
				
				// if new index does not exist in database
				if (peerIndex == null) {
					continue;
				}
				
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
					System.out.println("");
					continue;
				}
				
				// if peer does not have selected index
				if (!peerCourseExists) {
					// selected peerIndex does not exist in peer's registered courses
					System.out.println("\nIndex not registered by peer!");
					System.out.println("Please select a new Index.");
					System.out.println("");
					continue;
				}
				
				// check if peer's index clash with any other user's registered indexes, continue if yes
				if (ScheduleManager.willClash(peerIndex, user.getCourseList()) == true) {
					System.out.println("Please select a new Index.");
					System.out.println("");
					continue;
				}
				
				// check if user's index clash with any other peer's registered indexes, continue if yes
				if (ScheduleManager.willClash(userIndex, peer.getCourseList()) == true) {
					System.out.println("Please select a new Index.");
					System.out.println("");
					continue;
				}
				canSwap = true;
			} while (!canSwap);
			
			if (canSwap) {
				// confirm whether want to swap
				System.out.println("\nCurrent Index: ");
				PrintManager.printSessions(userIndex);
				System.out.println("\nNew Index: ");
				PrintManager.printSessions(peerIndex);
				System.out.println("\nConfirm to swap Index with peer? (Y/N)");
				String confirm;
				try {
					confirm = sc.next().toUpperCase();
				}
				catch (Exception InputMismatchException) {  // Error handling
					System.out.println("Invalid Input! Pleaes try again.");
					sc.next();
					return false;
				}
				if (confirm.equals("N")) {
					System.out.println("Index " + oldIndex + " not swapped with peer's new Index "+ newIndex);
					System.out.println("Please try again.");
					return false;
				}
					
				else if (!confirm.equals("Y")) {
					System.out.println("Please confirm by entering Y/N.");
					System.out.println("Index " + oldIndex + " not swapped with peer's new Index "+ newIndex);
					System.out.println("Please try again.");
					return false;
				}
				
				// enroll user to peerIndex
				// add user to studentList in peerIndex
				peerIndex.addStudentList(user);
				// add peer index to student's list of registered index
				RegisteredCourse peerRegisteredIndex = new RegisteredCourse(false, peerCourse, peerIndex, user);
				userRegCourses.add(peerRegisteredIndex);
				
				// drop user from currentIndex
				// remove user from studentList in currentIndex
				userIndex.removeStudentList(user.getMatricNumber());
				// remove index from student's list of registered index
				for (RegisteredCourse registered : userRegCourses) {
					if (registered.getIndex().getIndexNumber() == oldIndex) {
						userRegCourses.remove(registered);
						break;
					}
				}
				
				// enroll peer to currentIndex
				// add peer to studentList in currentIndex
				userIndex.addStudentList(peer);
				// add user index to peer's list of registered index
				RegisteredCourse userRegisteredIndex = new RegisteredCourse(false, userCourse, userIndex, peer);
				peerRegCourses.add(userRegisteredIndex);
				// drop peer from peerIndex
				peerIndex.removeStudentList(peer.getMatricNumber());
				// remove peerIndex from peer's list of registered index
				for (RegisteredCourse registered : peerRegCourses) {
					if (registered.getIndex().getIndexNumber() == newIndex) {
						peerRegCourses.remove(registered);
						break;
					}
				}
				
				// Update Course Database
				Database.updateCourseDB(userCourse);
				Database.updateCourseDB(peerCourse);
				// Update Student Database
				Database.updateStudentDB(user);
				Database.updateStudentDB(peer);
				System.out.println("\nIndex " + oldIndex + " from Course Code " + userCourse.getCourseCode() + " has been successfully swapped with " + peer.getMatricNumber() + "'s Index " + newIndex + ".");
				System.out.println("");
				PrintManager.printRegistered(user);
			}
		}
		return canSwap;
	}
}
