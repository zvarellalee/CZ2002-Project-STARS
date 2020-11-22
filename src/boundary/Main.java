/**
 * Main method body to call the login UI
 * @version 1.0
 * @since 2020-11-20
 */
package boundary;
import database.*;
import entities.*;

import java.util.ArrayList;
import java.util.List;

import control.*;

public class Main {
	public static void main(String[] args) {
		Database.initialise();  // to remove
		UserUI login = new LoginUI();
		login.showUI();
		
		//UserUI staff = new StaffUI();
		//staff.showUI();
		
		// test serializing
		/**
		Database.initialise();
		List<User> list = null;
		List<Course> courseList = null;
		try	{
			FileManager.write("staff.dat", Database.staffList);
			FileManager.write("student.dat", Database.studentList);
			FileManager.write("course.dat", Database.courseList);
			
			// test read
			list = (ArrayList<User>)FileManager.read("staff.dat");
			courseList = FileManager.read("course.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getSalt());
			//System.out.println(((Student)list.get(i)).getMatricNumber());
		}
		
		for (int i=0; i<courseList.size(); i++) {
			List<Index> indexList = courseList.get(i).getIndexList();
			for (int j =0; j<indexList.size(); j++) {
				System.out.println(indexList.get(j).getIndexNumber());
			}
			
		}
		*/
		
		
		
	}
}
