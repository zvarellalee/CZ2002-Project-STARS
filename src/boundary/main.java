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
		try	{
			Manager.write("staff.dat", Database.staffList);
			Manager.write("student.dat", Database.studentList);
			Manager.write("course.dat", Database.courseList);
			
			// test read
			list = (ArrayList<User>)Manager.read("staff.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i).getFirstName());
		}
		
		
		
	}
}
