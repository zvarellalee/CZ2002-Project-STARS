package control;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import entities.Student;
import java.util.ArrayList;

public class FileManager {
	private static List courses;
	private static List students;
	private static List staffs;
	
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
	
	@SuppressWarnings("rawtypes")
	public static List getCourseDB() {
		return courses;
	}
	
	@SuppressWarnings("rawtypes")
	public static List getStudentDB() {
		return students;
	}
	
	@SuppressWarnings("rawtypes")
	public static List getStaffDB() {
		return staffs;
	}
	
	public static int getStudentIndex(Student student) {
		for (int i = 0; i < FileManager.getStudentDB().size(); i++) {
			if (FileManager.getStudentDB().get(i).equals(student)) {
				return i;
			}
		}
		System.out.println("Student Not Found.");
		return -1;
	}
	
}
