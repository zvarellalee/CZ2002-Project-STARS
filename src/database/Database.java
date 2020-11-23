package database;

import java.util.Calendar;
import java.util.HashMap;
import control.FileManager;
import entities.Course;
import entities.Index;
import entities.Session;
import entities.Staff;
import entities.Student;

public class Database {	
	private static HashMap<String,Course> courseMap = new HashMap<String,Course>();
	private static HashMap<String,Student> studentMap = new HashMap<String,Student>();
	private static HashMap<String,Staff> staffMap = new HashMap<String,Staff>();

	public static void initialise() {		
		
		Staff staff1 = new Staff("R010", "123zxc", true, "Rick", "Grimes", "R010@e.ntu.edu.sg", "232A");
		Staff staff2 = new Staff("DDIXON2", "zxc123", true, "Daryl", "Dixon", "DDIXON2@e.ntu.edu.sg", "212B");
		
		staffMap.put(staff1.getStaffID(), staff1);
		staffMap.put(staff2.getStaffID(), staff2);
		
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
			
		Calendar beforeStart = Calendar.getInstance();
		Calendar beforeEnd = Calendar.getInstance();
		
		beforeStart.set(Calendar.MONTH, 8);  // 0: Jan, 11: Dec
		beforeStart.set(Calendar.DAY_OF_MONTH, 15);
		beforeStart.set(Calendar.HOUR_OF_DAY, 9);
		beforeStart.set(Calendar.MINUTE, 30);
		
		beforeEnd.set(Calendar.MONTH, 8);
		beforeEnd.set(Calendar.DAY_OF_MONTH, 25);
		beforeEnd.set(Calendar.HOUR_OF_DAY, 16);
		beforeEnd.set(Calendar.MINUTE, 30);
		
		Calendar afterStart = Calendar.getInstance();
		Calendar afterEnd = Calendar.getInstance();
		
		afterStart.set(Calendar.MONTH, 11);  // 0: Jan, 11: Dec
		afterStart.set(Calendar.DAY_OF_MONTH, 15);
		afterStart.set(Calendar.HOUR_OF_DAY, 9);
		afterStart.set(Calendar.MINUTE, 30);
		
		afterEnd.set(Calendar.MONTH, 11);
		afterEnd.set(Calendar.DAY_OF_MONTH, 25);
		afterEnd.set(Calendar.HOUR_OF_DAY, 16);
		afterEnd.set(Calendar.MINUTE, 30);
		
		// Student
		Student s0 = new Student("LIL001", "a123", false, "Lily", "Chen", "LIL001@e.ntu.edu.sg", "U123U", "Female", "Singaporean", 0);
		Student s1 = new Student("TOMH002", "a321", false, "Tom", "Hardy", "TOMH002@e.ntu.edu.sg", "U666H", "Male", "American", 0);
		Student s2 = new Student("JSMITH005", "john2", false, "John", "Smith", "JSMITH005@e.ntu.edu.sg", "U432J", "Male", "Canadian", 0);
		Student s3 = new Student("MLIM037", "mlmm3", false, "Mar", "Lim", "MLIM037@e.ntu.edu.sg", "U858M", "Male", "Singaporean", 0);
		Student s4 = new Student("C1527", "curl", false, "Carl", "Gooey", "C1527@e.ntu.edu.sg", "U876C", "Male", "British", 0);
		Student s5 = new Student("AME003", "amelia", false, "Amelia", "Ho", "AME003@e.ntu.edu.sg", "U111C", "Female", "Singaporean", 0);
		Student s6 = new Student("S2005", "st200", false, "Stacey", "Adams", "S2005@e.ntu.edu.sg", "U192J", "Female", "American", 0);
		Student s7 = new Student("JYO555", "jas3", false, "Jasmine", "Young", "JYO555@e.ntu.edu.sg", "U376J", "Female", "Singaporean", 0);
		Student s8 = new Student("SWO333", "sh3w", false, "Sheryl", "Wong", "SWO333@e.ntu.edu.sg", "U246I", "Female", "Singaporean", 0);
		Student s9 = new Student("SM667", "sakura3", false, "Sakura", "Myoui", "SM667@e.ntu.sg", "U676S", "Female", "Japanese", 0);
		Student s10 = new Student("KBS1", "kiboolol", false, "Ki Boo", "Song", "KBS1@e.ntu.edu.sg", "U112L", "Male", "Korean", 0);
		Student s11 = new Student("J789", "jacq", false, "Jacques", "Bernard", "J789@e.ntu.edu.sg", "U329D", "Male", "French", 0);
		Student s12 = new Student("SDHU888", "b12", false, "Sri", "Dhula", "SDHU888@e.ntu.edu.sg", "U454J", "Male", "Pakistani", 0);
		Student s13 = new Student("JHO963", "jayh0", false, "Jason", "Ho", "JHO963@e.ntu.edu.sg", "U223K", "Male", "Singaporean", 0);
		Student s14 = new Student("JTIAN008", "w432", false, "Jian Li", "Tian", "JTIAN008@e.ntu.edu.sg", "U542L", "Male", "Chinese", 0);
		Student s15 = new Student("SHO023", "s023", false, "Stanley", "Ho", "SHO023@e.ntu.edu.sg", "U998A", "Male", "Singaporean", 0);
		
		// special cases for access
		s0.setAccessStart(beforeStart);
		s0.setAccessEnd(beforeEnd);
		s1.setAccessStart(afterStart);
		s1.setAccessEnd(afterEnd);
		
		s2.setAccessStart(accessStart);
		s2.setAccessEnd(accessEnd);
		s3.setAccessStart(accessStart);
		s3.setAccessEnd(accessEnd);
		s4.setAccessStart(accessStart);
		s4.setAccessEnd(accessEnd);
		s5.setAccessStart(accessStart);
		s5.setAccessEnd(accessEnd);
		s6.setAccessStart(accessStart);
		s6.setAccessEnd(accessEnd);
		s7.setAccessStart(accessStart);
		s7.setAccessEnd(accessEnd);
		s8.setAccessStart(accessStart);
		s8.setAccessEnd(accessEnd);
		s9.setAccessStart(accessStart);
		s9.setAccessEnd(accessEnd);
		s10.setAccessStart(accessStart);
		s10.setAccessEnd(accessEnd);
		s11.setAccessStart(accessStart);
		s11.setAccessEnd(accessEnd);
		s12.setAccessStart(accessStart);
		s12.setAccessEnd(accessEnd);
		s13.setAccessStart(accessStart);
		s13.setAccessEnd(accessEnd);
		s14.setAccessStart(accessStart);
		s14.setAccessEnd(accessEnd);
		s15.setAccessStart(accessStart);
		s15.setAccessEnd(accessEnd);
		
		studentMap.put(s0.getMatricNumber(), s0);
		studentMap.put(s1.getMatricNumber(), s1);
		studentMap.put(s2.getMatricNumber(), s2);
		studentMap.put(s3.getMatricNumber(), s3);
		studentMap.put(s4.getMatricNumber(), s4);
		studentMap.put(s5.getMatricNumber(), s5);
		studentMap.put(s6.getMatricNumber(), s6);
		studentMap.put(s7.getMatricNumber(), s7);
		studentMap.put(s8.getMatricNumber(), s8);
		studentMap.put(s9.getMatricNumber(), s9);
		studentMap.put(s10.getMatricNumber(), s10);
		studentMap.put(s11.getMatricNumber(), s11);
		studentMap.put(s12.getMatricNumber(), s12);
		studentMap.put(s13.getMatricNumber(), s13);
		studentMap.put(s14.getMatricNumber(), s14);
		studentMap.put(s15.getMatricNumber(), s15);
		
		// Courses
		Course c0 = new Course("MA2007", "Thermodynamics", "MAE", 4);
		Course c1 = new Course("CZ2001", "Algorithms", "SCSE", 3);
		Course c2 = new Course("EG0001", "Engineers and Society", "SCSE", 3);
		Course c3 = new Course("AAA18D", "Life Drawing", "NIE", 3);
		Course c4 = new Course("CZ3004", "Multidisplinary Project", "SCSE", 4);
		Course c5 = new Course("CZ2002", "Object Oriented Design & Programming", "SCSE", 3);
		Course c6 = new Course("HW0188", "Engineering Communication I", "SOH", 3);
		Course c7 = new Course("CZ3002", "Advanced Software Engineering", "SCSE", 3);
		Course c8 = new Course("CZ3003", "Software System and Alaysis", "SCSE", 3);
		
		// Indexes
		Index i1 = new Index(1001, 2);
		Index i2 = new Index(1002, 1);
		Index i3 = new Index(1003, 1);
		Index i4 = new Index(1004, 3);
		Index i5 = new Index(1005, 3);
		Index i6 = new Index(1006, 1);
		Index i7 = new Index(1007, 1);
		Index i8 = new Index(1008, 3);
		Index i9 = new Index(1009, 3);
		Index i10 = new Index(1010, 2);
		Index i11 = new Index(1011, 2);
		
		Session se1 = new Session("LEC","Home",inputTime(2,13,0),inputTime(2,15,0));
		Session se1a = new Session("TUT", "LHN-TR17", inputTime(1,12,0),inputTime(1,13,0));
		Session se2 = new Session("LEC", "LT1A", inputTime(3,13,0),inputTime(3,14,0));
		Session se2a = new Session("LAB", "HWLAB1", inputTime(1,13,30), inputTime(1,15,30));
		Session se3 = new Session("LEC","LT1A",inputTime(3,13,0),inputTime(3,14,0));
		Session se3a = new Session("LAB", "HWLAB2", inputTime(4,14,30),inputTime(4,16,30));
		Session se4 = new Session("LEC", "LT19",inputTime(5,8,0),inputTime(5,10,0));
		Session se5 = new Session("TUT", "NIE2-01-TR201", inputTime(6,17,0), inputTime(6,19,0));
		Session se6 = new Session("LAB","SPL",inputTime(4,10,0),inputTime(4,13,0));
		Session se7 = new Session("LEC","LT1A",inputTime(5,11,0),inputTime(5,12,0));
		Session se8 = new Session("LEC","LHN-TR15",inputTime(1,14,0),inputTime(1,15,0));
		Session se9 = new Session("LEC","LHS-TR7",inputTime(2,8,0),inputTime(2,10,0));
		Session se9a = new Session("LAB","SWLAB3",inputTime(4,13,30),inputTime(4,15,30));
		Session se10 = new Session("LEC","LT2A",inputTime(2,8,0),inputTime(2,10,0));
		Session se10a = new Session("LAB","HWLAB1",inputTime(4,9,0),inputTime(4,10,0));
		Session se11 = new Session("LEC","LT19",inputTime(3,11,0),inputTime(3,13,0));
		
		// add sessions to index
		i1.addSessionList(se1);
		i1.addSessionList(se1a);
		i2.addSessionList(se2);
		i2.addSessionList(se2a);
		i3.addSessionList(se3);
		i3.addSessionList(se3a);
		i4.addSessionList(se4);
		i5.addSessionList(se5);
		i6.addSessionList(se6);
		i7.addSessionList(se7);
		i8.addSessionList(se8);
		i9.addSessionList(se9);
		i9.addSessionList(se9a);
		i10.addSessionList(se10);
		i10.addSessionList(se10a);
		i11.addSessionList(se11);
		
		// add indexes to course
		c0.addIndex(i1);
		c1.addIndex(i2);
		c1.addIndex(i3);
		c2.addIndex(i4);
		c2.addIndex(i11);
		c3.addIndex(i5);
		c4.addIndex(i6);
		c5.addIndex(i7);
		c6.addIndex(i8);
		c7.addIndex(i9);
		c8.addIndex(i10);
		
		courseMap.put(c0.getCourseCode(),c0);
		courseMap.put(c1.getCourseCode(),c1);
		courseMap.put(c2.getCourseCode(),c2);
		courseMap.put(c3.getCourseCode(),c3);
		courseMap.put(c4.getCourseCode(),c4);
		courseMap.put(c5.getCourseCode(),c5);
		courseMap.put(c6.getCourseCode(),c6);
		courseMap.put(c7.getCourseCode(),c7);
		courseMap.put(c8.getCourseCode(),c8);
		
		// Write to .dat files
		FileManager.write("course.dat", courseMap);
		FileManager.write("student.dat", studentMap);
		FileManager.write("staff.dat", staffMap);
		
	}
	
	private static Calendar inputTime(int day, int hour, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, day + 1);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, 0);
		return calendar;
		
	}	
}
