package edu.ncsu.csc216.pack_scheduler.io;


import java.util.*;
import java.io.*;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Provides methods to read and write student directory files.
 * All students have a unique id.
 * @author Steven Mayo with skeleton code from Sarah Heckman
 */
public class StudentRecordIO {
	
	/**
	 * Reads in and returns a valid list of student records from the passed in filename.
	 * @param fileName String representing the name of the file containing a list of student records.
	 * @throws FileNotFoundException if the file cannot be read.
	 * @return String array containing Student objects.
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
			Scanner fileReader = new Scanner(new FileInputStream(fileName));
			SortedList<Student> students = new SortedList<Student>();	
			
			while(fileReader.hasNextLine()) {
				try{
					Student student = processStudent(fileReader.nextLine());
					boolean isValid = true;
					for(int i = 0; i < students.size(); i++){
						Student s = students.get(i);
						if(s == null) isValid = false;
					}
					if(isValid) students.add(student);
				} catch(IllegalArgumentException e){
					//skip the line
				}
			}
			fileReader.close();
			return students;
	}
		
	/**
	 * Takes input from the readStudentRecords method and uses the Student class to generate a
	 * new Student object. Also includes validation checks to ensure the student record was processed correctly.
	 * If an invalid record is found, a null value is passed into the Student constructor, which is then not
	 * added into the directory.
	 * @param record A string representing data passed from a Scanner object. 
	 * @return new Student object.
	 */
	private static Student processStudent(String record) throws IllegalArgumentException{
		Student processedStudent;
		Scanner scanner = new Scanner(record).useDelimiter(",");
		String fname = scanner.next();
		String lname = scanner.next();
		String id = scanner.next();
		if(id.contains("@")) id = null;
		String email = scanner.next();
		if(!email.contains("@")) email = null;
		String pw = scanner.next();				
		if(!scanner.hasNext()) {
			//Must have all parameters when reading in student records.
			scanner.close();
			throw new IllegalArgumentException();
		}
		int credits = Integer.parseInt(scanner.next());
		processedStudent = new Student(fname, lname, id, email, pw, credits);
		scanner.close();
		return processedStudent;
	}
	
	/**
	 * Static method used to write valid student records to a file.
	 * @param fileName is a String representing the output filename.
	 * @param studentDirectory is an SortedList of Student objects that is passed in 
	 * to be written as output. 
	 * @throws FileNotFoundException if the file cannot be located or written to.
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws FileNotFoundException {
		PrintStream fileWriter = new PrintStream(new FileOutputStream(new File(fileName)));
		for(int i = 0; i < studentDirectory.size(); i++){
			fileWriter.println(studentDirectory.get(i).toString());
		}
		fileWriter.close();
	}

}
