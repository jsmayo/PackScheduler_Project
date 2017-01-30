package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;


/**
 * Tests StudentRecordIO.
 * 
 * @author Steven Mayo
 */
public class StudentRecordIOTest {

	private String hashPW;
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Valid student records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid student records */
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	
	/** Expected results for valid students */
	private final String validStudent1 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	private final String validStudent2 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	private final String validStudent3 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	private final String validStudent4 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	private final String validStudent5 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	private final String validStudent6 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	private final String validStudent7 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	private final String validStudent8 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	private final String validStudent9 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	private final String validStudent10 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	
	/** Array to hold expected results */
	private final String[] validStudents = {validStudent1, validStudent2, validStudent3, validStudent4, validStudent5, 
			validStudent6, validStudent7, validStudent8, validStudent9, validStudent10};
	
	
	/**
	 * Resets student_records.txt for use in other tests and sets the password has key.
	 * @throws Exception if the file cannot be reached.
	 */
	@Before
	public void setUp() throws Exception {
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}

		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = new String(digest.digest());

			for (int i = 0; i < validStudents.length; i++) {
				validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Tests readValidStudentRecords().
	 */
	@Test
	public void testReadValidStudentRecords() {
		try {
			ArrayList<Student> students = StudentRecordIO.readStudentRecords(validTestFile);
			assertEquals(10, students.size());
			
			//check order.
			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], students.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}
	
	
	/**
	 * Tests readInvalidStudentRecords().
	 */
	@Test
	public void testReadInvalidStudentRecords() {
		ArrayList<Student> students;
		try {
			students = StudentRecordIO.readStudentRecords(invalidTestFile);
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected file contents");
		}
	}

	/**
	 * Tests readInvalidFileLocation().
	 */
	@Test
	public void testReadInvalidFileLocation() {
		ArrayList<Student> students = null;
		try {
			students = StudentRecordIO.readStudentRecords("nosuchfile.txt");
			fail("fileName should throw an exception!");
		} catch (FileNotFoundException e) {
			assertEquals(null, students);
		}
	}
		
	/**
	 * Tests readWriteStudentRecords().
	 */
	@Test
	public void testWriteStudentRecords() {
		try {
			ArrayList<Student> students = StudentRecordIO.readStudentRecords(validTestFile);
			StudentRecordIO.writeStudentRecords("test-files/student_records.txt", students);
			}
		 catch (IOException e) {
			fail("Cannot write to student records file");
		}
		
		checkFiles("test-files/expected_full_student_records.txt", "test-files/student_records.txt");
	}
	
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}

}