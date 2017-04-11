package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Provides methods to read and write faculty directory files.
 * 
 * @author Steven Mayo
 */
public class FacultyRecordIO {

	/**
	 * Reads in and returns a valid list of Faculty records from the passed in filename.
	 * @param fileName String representing the name of the file containing a list of Faculty records.
	 * @throws FileNotFoundException if the file cannot be read.
	 * @return String array containing Faculty objects.
	 */

	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyDirectory = new LinkedList<Faculty>();	

		while(fileReader.hasNextLine()) {
			try{
				Faculty faculty = processFaculty(fileReader.nextLine());
				boolean isValid = true;
				for(int i = 0; i < facultyDirectory.size(); i++){
					User s = facultyDirectory.get(i);
					if(s == null) isValid = false;
				}
				if(isValid) facultyDirectory.add(faculty);
			} catch(IllegalArgumentException e){
				//skip the line
			}
		}
		fileReader.close();
		return facultyDirectory;
	}

	/**
	 * Takes input from the readFacultyRecords method and uses the Faculty class to generate a
	 * new Faculty object. Also includes validation checks to ensure the Faculty record was processed correctly.
	 * If an invalid record is found, a null value is passed into the Faculty constructor, which is then not
	 * added into the directory.
	 * @param record A string representing data passed from a Scanner object. 
	 * @return new Faculty object.
	 */
	private static Faculty processFaculty(String record) throws IllegalArgumentException{
		Faculty processedFaculty;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(record).useDelimiter(",");
		String fname = scanner.next();
		String lname = scanner.next();
		String id = scanner.next();
		if(id.contains("@")) id = null;
		String email = scanner.next();
		if(!email.contains("@")) email = null;
		String pw = scanner.next();				
		if(!scanner.hasNext()) {
			//Must have all parameters when reading in Faculty records.
			scanner.close();
			throw new IllegalArgumentException();
		}
		int courses = Integer.parseInt(scanner.next());
		processedFaculty = new Faculty(fname, lname, id, email, pw, courses);
		scanner.close();
		return processedFaculty;
	}


	/**
	 * Static method used to write valid Faculty records to a file.
	 * @param fileName is a String representing the output filename.
	 * @param facultyDirectory is an SortedList of Faculty objects that is passed in 
	 * to be written as output. 
	 * @throws FileNotFoundException if the file cannot be located or written to.
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws FileNotFoundException {
		PrintStream fileWriter = new PrintStream(new FileOutputStream(new File(fileName)));
		for(int i = 0; i < facultyDirectory.size(); i++){
			fileWriter.println(facultyDirectory.get(i).toString());
		}
		fileWriter.close();
	}

}