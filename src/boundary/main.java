package boundary;
import database.*;
import entities.*;

import java.util.ArrayList;
import java.util.List;

import control.*;

public class main {
	public static void main(String[] args) {
		//LoginUI login = new LoginUI();
		//login.displayLogin();
		
		// test serializing
		Database.initialise();
		List<User> list = null;
		List<Course> courseList = null;
		try	{
			FileManager.write("staff.dat", Database.staffList);
			FileManager.write("student.dat", Database.studentList);
			FileManager.write("course.dat", Database.courseList);
			
			// test read
			list = (ArrayList<User>)FileManager.read("student.dat");
			courseList = FileManager.read("course.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getFirstName());
			System.out.println(((Student)list.get(i)).getMatricNumber());
		}
		
		for (int i=0; i<courseList.size(); i++) {
			List<Index> indexList = courseList.get(i).getIndexList();
			for (int j =0; j<indexList.size(); j++) {
				System.out.println(indexList.get(j).getIndexNumber());
			}
			
		}
		
		
	}
}
