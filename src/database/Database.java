package database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import control.FileManager;
import entities.Course;
import entities.Index;
import entities.Staff;
import entities.Student;

public class Database {	
	private static HashMap<String,Course> courseMap = new HashMap<String,Course>();
	private static HashMap<String,Student> studentMap = new HashMap<String,Student>();
	private static HashMap<String,Staff> staffMap = new HashMap<String,Staff>();

	public static void initialise() {		
		// Initialize students
		Student carl = new Student("carl", "123", false, "Carl", 
				"Cauliflower", null, "test@lmao.com", "U123U", "Male", "Chinese", 0);
		Student stacey = new Student("stacey", "321", false, "Stacey", 
				"Smiles", null, "test2@lmao.com", "U321U", "Female", "Indian", 0);
		
		// Initialize access period
		Calendar accessStart = Calendar.getInstance();
		Calendar accessEnd = Calendar.getInstance();
		
		accessStart.set(Calendar.MONTH, 10);  // 0: Jan, 11: Dec
		accessStart.set(Calendar.DAY_OF_MONTH, 15);
		accessStart.set(Calendar.HOUR_OF_DAY, 9);
		accessStart.set(Calendar.MINUTE, 30);
		
		accessEnd.set(Calendar.MONTH, 11);
		accessEnd.set(Calendar.DAY_OF_MONTH, 25);
		accessEnd.set(Calendar.HOUR_OF_DAY, 16);
		accessEnd.set(Calendar.MINUTE, 30);
		
		carl.setAccessStart(accessStart);
		carl.setAccessEnd(accessEnd);
		stacey.setAccessStart(accessStart);
		stacey.setAccessEnd(accessEnd);
		
		// Initialize staffs
		Staff lokey = new Staff ("dustbin", "jkl", true, "Loke", "Dustbin", null, "a@test.com", "S666S");
		Staff lala = new Staff ("lalala", "ghj", true, "Lala", "Lol", null, "b@test.com", "S888S");
		
		// Initialize courses
		Course oodp = new Course("2002", "Obnoxious Oblong Drunk Police", "Weed Science", 3);
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
		studentMap.put(carl.getMatricNumber(),carl);
		studentMap.put(stacey.getMatricNumber(),stacey);
		
		// Initialize staffList with test data
		staffMap.put(lokey.getStaffID(),lokey);
		staffMap.put(lala.getStaffID(),lala);
		
		// Initialize courseList with test data
		courseMap.put(oodp.getCourseCode(),oodp);
		courseMap.put(algo.getCourseCode(),algo);
		
		// Write to .dat files
		FileManager.write("course.dat", courseMap);
		FileManager.write("student.dat", studentMap);
		FileManager.write("staff.dat", staffMap);
	}
	
}
