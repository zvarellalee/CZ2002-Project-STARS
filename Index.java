package cz2002_Project;

import java.util.ArrayList;

public class Index {
	private int indexNumber; // identifier of index (key value)
	private int vacancies; // number of slots available 
	private ArrayList<Lesson> lessonList; // list of all lessons in the index
	private ArrayList<Student> studentList; // list of all students enrolled in the course
	private ArrayList<Student> waitList; // list of all students on the wait list.
	
	// ------ constructor method ------
	public Index(int indexNumber, int vacancies) {
		this.indexNumber = indexNumber;
		this.vacancies = vacancies;
		lessonList = new ArrayList<Lesson>();
		studentList = new ArrayList<Student>();
		waitList = new ArrayList<Student>();
	}
	
	// ------ accessor methods ------
	public int getIndexNumber() {
		return indexNumber;
	}
	
	public int getVacancies() {
		return vacancies;
	}
	
	public ArrayList<Lesson> getLessonList() {
		return lessonList;
	}
	
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	public ArrayList<Student> getWaitList() {
		return waitList;
	}
	
	// ------ mutator methods ------
	// change the number of vacancies
	public void setVacancies(int num) {
		vacancies = num;
	}
	
	// set the studentList to a predefined studentList
	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}
	
	// add a student to the studentList
	public void addStudentList(Student student) { 
		studentList.add(student);
	}
	
	// remove a student from the studentList
	public void removeStudentList(Student student) {
		if (studentList.contains(student))
			studentList.remove(student);

	}
	
	// set the waitList to a predefined waitList
	public void setWaitList(ArrayList<Student> waitList) {
		this.waitList = waitList;
	}
	
	// add a student to the waitList
	public void addWaitList(Student student) { 
		waitList.add(student);
	}
	
	// remove a student from the waitList
	public void removeWaitList(Student student) {
		if (waitList.contains(student))
			waitList.remove(student);

	}
	
	// set the lessonList to a predefined lessonList
	public void setLessonList(ArrayList<Lesson> lessonList) {
		this.lessonList = lessonList;
	}
	
	
	// add a lesson to the lessonList
	public void addLessonList(Lesson lesson) { 
		lessonList.add(lesson);
	}
	
	// remove a lesson from the lessonList
	public void removeLessonList(Lesson lesson) {
		if (lessonList.contains(lesson))
			lessonList.remove(lesson);
	} 
}
