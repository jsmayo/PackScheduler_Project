package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class FacultyDirectoryTest {
	/** Valid faculty records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Faculty";
	/** Test last name */
	private static final String LAST_NAME = "Person";
	/** Test id */
	private static final String ID = "faculty";
	/** Test email */
	private static final String EMAIL = "faculty@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_COURSES = 3;
	
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("sesmith5"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		//Test that if there are faculty in the directory, they 
		//are removed after calling newFacultyDirectory().
		FacultyDirectory fd = new FacultyDirectory();
		
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		
		fd.newFacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultyFromFile().
	 */
	@Test
	public void testLoadFacultyFromFile() {
		FacultyDirectory fd = new FacultyDirectory();
				
		//Test valid file
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
	
		//Test invalid file
		try {
			fd.loadFacultyFromFile("nosuchfileshouldexistsforthistest.txt");
			fail("Should not have a file called this");
		} catch (IllegalArgumentException iae) {
			assertEquals(8, fd.getFacultyDirectory().length);
		}
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
		
		//Test valid Faculty
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String [][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		
		//Test invalid Faculty and that order doesnt change
		try {
			fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "INVALID", MAX_COURSES);
			fail("Passwords should mismatch");
		} catch (IllegalArgumentException iae){
			//No changes should have been made.
			assertEquals(1, facultyDirectory.length);
			assertEquals(FIRST_NAME, facultyDirectory[0][0]);
			assertEquals(LAST_NAME, facultyDirectory[0][1]);
			assertEquals(ID, facultyDirectory[0][2]);
		}
		
		//Test adding a duplicate faculty
		assertFalse(fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();
				
		//Add faculty and remove
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		assertTrue(fd.removeFaculty("haguirr"));
		String [][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Ashely", facultyDirectory[0][0]);
		assertEquals("Witt", facultyDirectory[0][1]);
		assertEquals("awitt", facultyDirectory[0][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();
		
		//Add a faculty member
		fd.addFaculty("Ashely","Witt","awitt","mollis@Fuscealiquetmagna.net","pw", "pw",2);
		assertEquals(1, fd.getFacultyDirectory().length);
		fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
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
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}