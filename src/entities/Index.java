/**
 * Represents an index in a course.
 * Each index is unique and contains a list of sessions.
 * An index can be registered by students and has a 
 * certain number of available slots (vacancies).
 * Once there are no vacancies in the index, students who 
 * register this index will be added to the wait list.
 * @author 
 * @version 1.0
 * @since 2020-11-20
 */

package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Index implements Serializable {
	/**
	 * Stores the Index Number of the Index
	 * Each Index is identified using the indexNumber
	 */
	private int indexNumber; // identifier of index (key value)
	/**
	 * Stores the number of slots available in the Index
	 */
	private int vacancies; // number of slots available 
	/**
	 * Stores an Array List of Sessions in the Index
	 */
	private ArrayList<Session> sessionList; // list of all sessions in the index
	/**
	 * Stores an Array List of Students enrolled in the Index
	 */
	private ArrayList<Student> studentList; // list of all students enrolled in the course
	/**
	 * Stores an Array List of Students on the wait list in the Index
	 */
	private ArrayList<Student> waitList; // list of all students on the wait list.
	
	// ------ constructor method ------
	/**
	 * Initializing the Index
	 * @param indexNumber Index Number 
	 * @param vacancies Number of Slots Available
	 */
	public Index(int indexNumber, int vacancies) {
		this.indexNumber = indexNumber;
		this.vacancies = vacancies;
		sessionList = new ArrayList<Session>();
		studentList = new ArrayList<Student>();
		waitList = new ArrayList<Student>();
	}
	
	// ------ accessor methods ------
	/**
	 * Gets the Index Number
	 * @return indexNumber Index Number
	 */
	public int getIndexNumber() {
		return indexNumber;
	}
	
	/**
	 * Gets the Number of Slots Available
	 * @return vacancies Number of Slots Available
	 */
	public int getVacancies() {
		return vacancies;
	}
	
	/**
	 * Gets the Array List of Sessions in the Index
	 * @return Array List of Sessions in the Index
	 */
	public ArrayList<Session> getSessionList() {
		return sessionList;
	}
	
	/**
	 * Gets the Array List of Students in the Index
	 * @return Array List of Students in the Index
	 */
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	/**
	 * Gets the Array List of Students in the wait list
	 * @return Array List of Students in the wait list
	 */
	public ArrayList<Student> getWaitList() {
		return waitList;
	}
	
	/**
	 * Gets the number of Students in the wait list
	 * @return number of Students in the wait list
	 */
	public int getWaitListSize() {
		return waitList.size();
	}
	
	// ------ mutator methods ------
	/**
	 * Sets the number of available slots in the index
	 * @param num Number of available slots
	 */
	public void setVacancies(int num) {
		vacancies = num;
	}
	
	/**
	 * Sets the array list of Students in the Index
	 * @param studentList Array List of Students
	 */
	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
	
	/**
	 * Adds a student to the Array List of Students enrolled in the Index
	 * @param Student Object
	 */
	public void addStudentList(Student student) { 
		studentList.add(student);
	}
	
	/**
	 * Removes a student from the Array List of Students enrolled in the Index
	 * @param Student Object
	 */
	public void removeStudentList(Student student) {
		if (studentList.contains(student))
			studentList.remove(student);

	}
	
	/**
	 * Sets the array list of Students on the wait list of the Index
	 * @param waitList Array List of Students on the wait list
	 */
	public void setWaitList(ArrayList<Student> waitList) {
		this.waitList = waitList;
	}
	
	/**
	 * Adds a student to the wait list 
	 * @param waitList Array List of Students on the wait list
	 */
	public void addWaitList(Student student) { 
		waitList.add(student);
	}
	
	/**
	 * Removes a student from the wait list 
	 * @param waitList Array List of Students on the wait list
	 */
	public void removeWaitList(Student student) {
		if (waitList.contains(student))
			waitList.remove(student);

	}
	
	/**
	 * Sets the array list of Sessions in the Index
	 * @param sessionList Array List of Sessions
	 */
	public void setSessionList(ArrayList<Session> sessionList) {
		this.sessionList = sessionList;
	}
	
	/**
	 * Adds a session to the sessionList
	 * @param sessionList Array List of Sessions in the Index
	 */
	public void addSessionList(Session session) { 
		sessionList.add(session);
	}
	
	/**
	 * Removes a session from the sessionList
	 * @param sessionList Array List of Sessions in the Index
	 */
	public void removeSessionList(Session session) {
		if (sessionList.contains(session))
			sessionList.remove(session);
	} 

}
