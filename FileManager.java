package control;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import entities.Course;
import entities.Staff;
import entities.Student;
import java.util.HashMap;

public class FileManager {
	public static void write(String filename, Map<?,?> map) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("data/"+filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(map);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Map read(String filename) {
		Map details = null;
		FileInputStream fis;
		ObjectInputStream in;
		
		try {
			fis = new FileInputStream("data/"+filename);
			in = new ObjectInputStream(fis);
			details = (HashMap) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return details;
	}
}
