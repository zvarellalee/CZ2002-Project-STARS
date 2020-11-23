/**
 * Represents a course registered by a student.
 * Each registered course may be on the wait list of an index.
 * @author Stanley Ho
 * @version 1.0
 * @since 2020-11-20
 */
package entities;

import java.io.Serializable;

public class RegisteredCourse implements Serializable{
	/**
	 * Stores the boolean value of whether
	 * this course is currently on the wait list.
	 */
	private boolean onWaitlist;
	/**
	 * Stores the Course Object registered.
	 */
	private Course course;
	/**
	 * Stores the Index Object registered.
	 */
	private Index index; 
	/**
	 * Stores the Student who registered this course.
	 */
	private Student student;
	
	/**
	 * Initializing the Registered Course
	 * @param onWaitlist Whether the registered course is on wait list
	 * @param course Course
	 * @param index Index
	 * @param student Student
	 */
	//constructor method
	public RegisteredCourse(boolean onWaitlist, Course course, Index index, Student student) {
		this.onWaitlist = onWaitlist;
		this.course = course;
		this.index = index;
		this.student = student;
	}
	
	
	//get methods
	/**
	 * Gets the boolean value of whether the registered index is on the wait list
	 * @return onWaitList 
	 */
	public boolean getOnWaitlist() {
		return onWaitlist;
	}

	/**
	 * Gets the Course registered by the student
	 * @return Course Object
	 */
	public Course getCourse() {
		return course;
	}
	
	/** 
	 * Gets the Index registered by the student
	 * @return Index Object
	 */
	public Index getIndex() {
		return index;
	}
	
	/**
	 * Gets the Student who registered the Course 
	 * @return Student Object
	 */
	public Student getStudent() {
		return student;
	}
	
	
	//set methods
	/**
	 * Sets the boolean value of whether the registered index is on the wait list
	 * @param onWaitlist Whether the registered index is on the wait list
	 */
	public void setOnWaitlist(boolean onWaitlist) {
		this.onWaitlist = onWaitlist;
	}
	
	/**
	 * Sets the Course registered by the student
	 * @param course Course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
	
	/** 
	 * Sets the Index registered by the student
	 * @param index Index
	 */
	public void setIndex(Index index) {
		this.index = index;
	}
	
	/**
	 * Sets the Student who registered the Course
	 * @param student Student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
}
