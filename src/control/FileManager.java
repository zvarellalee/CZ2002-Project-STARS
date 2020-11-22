/**
 * Manager for File Handling
 * @version 1.0
 * @since 2020-11-20
 */
package control;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.HashMap;

public class FileManager {
	/**
	 * Write Map object to a specified file
	 * @param String filename
	 * @param Map map
	 */
	public static void write(String filename, Map<?,?> map) {
		FileOutputStream fos;
		ObjectOutputStream out;
		try {
			fos = new FileOutputStream("data/"+filename);
			out = new ObjectOutputStream(fos);
			
			out.writeObject(map);
			out.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read the Map object from a specified file
	 * @param String filename
	 * @return Map details
	 */
	public static Map read(String filename) {
		Map details = null;
		FileInputStream fis;
		ObjectInputStream in;
		
		try {
			fis = new FileInputStream("data/"+filename);
			in = new ObjectInputStream(fis);
			details = (HashMap) in.readObject();
			in.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return details;
	}
}
