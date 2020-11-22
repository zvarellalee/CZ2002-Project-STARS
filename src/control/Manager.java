package control;

import entities.Course;
import entities.Index;

public class Manager {
	/**
	 * Finds the Course Object using the Course Code
	 * @param courseCode Course Code
	 * @return Course Object
	 */
	public Course findCourse(String courseCode) {
		for (Course c : FileManager.getCourseDB()) {
			if (c.getCourseCode().equals(courseCode)) {
				return c;
			}
		}
		System.out.println("\nInvalid Course Code! Please try again.");
		System.out.println("");
		return null;
	}
	
	/**
	 * Finds the Index Object using the Index Number
	 * @param Index Index Number
	 * @return Index Object
	 */
	public Index findIndex(int index) {
		// Finds the Index object from index number
		for (Course c : FileManager.getCourseDB()) {
			for (Index i : c.getIndexList())
				if (i.getIndexNumber() == index) {
					return i;
				}
		}
		System.out.println("\nInvalid Index! Please try again.");
		System.out.println("");
		return null;
	}	

}
