package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import edu.ncsu.csc216.pack_scheduler.io.StudentRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Maintains a directory of all students enrolled at NC State.
 * All students have a unique id.
 * @author Sarah Heckman
 */
public class StudentDirectory {
	
	/** List of students in the directory */
	private ArrayList<Student> studentDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Creates an empty student directory.
	 */
	public StudentDirectory() {
		newStudentDirectory();
	}
	
	/**
	 * Creates an empty student directory.  All students in the previous
	 * list are list unless saved by the user.
	 */
	public void newStudentDirectory() {
		studentDirectory = new ArrayList<Student>();
	}
	
	/**
	 * Constructs the student directory by reading in student information
	 * from the given file.  Throws an IllegalArgumentException if the 
	 * file cannot be found.
	 * @param fileName file containing list of students
	 */
	public void loadStudentsFromFile(String fileName) {
		try {
			studentDirectory = StudentRecordIO.readStudentRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a Student to the directory.  Returns true if the student is added and false if
	 * the student is unable to be added because their id matches another student's id.
	 * 
	 * This method also hashes the student's password for internal storage.
	 * 
	 * @param firstName student's first name
	 * @param lastName student's last name
	 * @param id student's id
	 * @param email student's email
	 * @param password student's password
	 * @param repeatPassword student's repeated password
	 * @param maxCredits student's max credits.
	 * @return true if added
	 */
	public boolean addStudent(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCredits) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || password.equals("") || repeatPassword.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());
			
			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		//If an IllegalArgumentException is throw, it's passed up from Student
		//to the GUI
		Student student = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		
		for (int i = 0; i < studentDirectory.size(); i++) {
			Student s = studentDirectory.get(i);
			if (s.getId().equals(student.getId())) {
				return false;
			}
		}
		return studentDirectory.add(student);
	}
	
	/**
	 * Removes the student with the given id from the list of students with the given id.
	 * Returns true if the student is removed and false if the student is not in the list.
	 * @param studentId student's id
	 * @return true if removed
	 */
	public boolean removeStudent(String studentId) {
		for (int i = 0; i < studentDirectory.size(); i++) {
			Student s = studentDirectory.get(i);
			if (s.getId().equals(studentId)) {
				studentDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns all students in the directory with a column for first name, last name, and id.
	 * @return String array containing students first name, last name, and id.
	 */
	public String[][] getStudentDirectory() {
		String [][] directory = new String[studentDirectory.size()][3];
		for (int i = 0; i < studentDirectory.size(); i++) {
			Student s = studentDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}
	
	/**
	 * Saves all students in the directory to a file.
	 * @param fileName name of file to save students to.
	 */
	public void saveStudentDirectory(String fileName) {
		try {
			StudentRecordIO.writeStudentRecords(fileName, studentDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

}
