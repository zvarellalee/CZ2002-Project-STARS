package database;

import java.util.ArrayList;

import control.FileManager;
import entities.Course;
import entities.Index;
import entities.Staff;
import entities.Student;

public class Database {
	public static ArrayList<Student> studentList = new ArrayList<Student>();
	public static ArrayList<Staff> staffList = new ArrayList<Staff>();
	public static ArrayList<Course> courseList = new ArrayList<Course>();
	public static ArrayList<Index> indexList = new ArrayList<Index>();

	public static void initialise() {
		// Initialize students
		Student carl = new Student("carl", "123", false, "Carl", 
				"Butt", null, "test@lmao.com", "U123U", "Asexual", "Alien", 0);
		Student stacey = new Student("stacey", "321", false, "Stacey", 
				"Smiles", null, "test2@lmao.com", "U321U", "GenderFluid", "North Korean", 0);
		
		// Initialize staffs
		Staff lokey = new Staff ("dustbin", "jkl", true, "Loke", "Dustbin", null, "a@test.com", "S666S");
		Staff lala = new Staff ("lalala", "ghj", true, "Lala", "Lol", null, "b@test.com", "S888S");
		
		// Initialize courses
		Course oodp = new Course("2002", "Obviously Oolong Dying Programme", "Weed Science", 3);
		Course algo = new Course("2001", "Algorithms", "Computer Science", 3);
		
		// Initialize indexes
		Index a = new Index(1, 10);
		Index b = new Index(2, 10);
		Index c = new Index(3, 10);
		Index d = new Index(4, 10);
		Index e = new Index(5, 10);
		
		// Add Indexes for courses
		oodp.addIndex(a);
		oodp.addIndex(b);
		algo.addIndex(c);
		algo.addIndex(d);
		algo.addIndex(e);
		
		// Initialize studentList with test data
		studentList.add(carl);
		studentList.add(stacey);
		
		// Initialize staffList with test data
		staffList.add(lokey);
		staffList.add(lala);
		
		// Initialize courseList with test data
		courseList.add(oodp);
		courseList.add(algo);
		
		// Initialize indexList with test data
		//indexList.add(12345);
		//indexList.add(67890);
		
		// test cases used for case 5 and 6
		/*
		// carl registers oodp at index 1
		RegisteredCourse registered1 = new RegisteredCourse(false, oodp, a, carl);
		ArrayList<RegisteredCourse> reg = new ArrayList<RegisteredCourse>();
		reg.add(registered1);
		studentList.get(0).setCourseList(reg);
		
		// stacey registers algo at index 3
		RegisteredCourse registered2 = new RegisteredCourse(false, algo, c, stacey);
		ArrayList<RegisteredCourse> reg2 = new ArrayList<RegisteredCourse>();
		reg2.add(registered2);
		studentList.get(1).setCourseList(reg2);
		*/
		
		// Write to .dat files
		FileManager.setCourseDB(courseList);
		FileManager.setStudentDB(studentList);
		FileManager.setStaffDB(staffList);
		
		FileManager.write("course.dat", courseList);
		FileManager.write("student.dat", studentList);
		FileManager.write("staff.dat", staffList);
	}
	
}
