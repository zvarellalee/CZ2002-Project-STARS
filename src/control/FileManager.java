package control;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import entities.Course;
import entities.Staff;
import entities.Student;
import java.util.ArrayList;

public class FileManager {
	private static List<Course> courses;
	private static List<Student> students;
	private static List<Staff> staffs;
	
//	// Read in the .dat files upon Instantiation
//	public FileManager() {
//		courses = FileManager.read("course.dat");
//		students = FileManager.read("student.dat");
//		staffs = FileManager.read("staff.dat");
//	}
	
	public static void write(String filename, List list) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("data/"+filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List read(String filename) {
		List details = null;
		FileInputStream fis;
		ObjectInputStream in;
		
		try {
			fis = new FileInputStream("data/"+filename);
			in = new ObjectInputStream(fis);
			details = (ArrayList) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return details;
	}
	
	public static int getStudentIndex(Student student) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).equals(student)) {
				return i;
			}
		}
		System.out.println("Student Not Found.");
		System.out.println(-1);
		return -1;
	}
	
	public static void setStudentDB(List<Student> updatedStudents) {
		students = updatedStudents;
	}

	public static void setCourseDB(List<Course> updatedCourses) {
		courses = updatedCourses;
	}
	
	public static void setStaffDB(List<Staff> updatedStaffs) {
		staffs = updatedStaffs;
	}
	
	public static void updateStudentDB(Student student) {
		int i = FileManager.getStudentIndex(student);
		List<Student> updatedStudents = students;
		updatedStudents.set(i, student);
		setStudentDB(updatedStudents);
		write("student.dat", updatedStudents);
	}
}
