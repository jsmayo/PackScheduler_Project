package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.Course;




/**
 * Test the CourseCatalog class and ensures proper functionality is achieved.
 * 
 * @author Steven Mayo
 */
public class CourseCatalogTest {	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course enrollment cap */
	private static final int ENROLLMENT_CAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if the file is not found.
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Test for CourseCatalog. An invalid file should result in an empty 
	 * catalog and schedule, while a valid file should not.
	 */
	@Test
	public void testCourseCataog(){
		//Test for an empty catalog and schedule. 
				CourseCatalog cc1 = new CourseCatalog();
				assertEquals(0, cc1.getCourseCatalog().length);
				cc1.saveCourseCatalog("test-files/actual_empty_export.txt");
				checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
	}
	
	/**
	 * Test CourseCatalog .getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		//should return null
		CourseCatalog cc = new CourseCatalog();

		//Attempt to get a course that doesn't exist
		assertNull(cc.getCourseFromCatalog("CSC492", "001"));
		//Attempt to get a course that does exist
		assertEquals(null, cc.getCourseFromCatalog("CSC216", "001"));
	}
	
	
	/**
	 * Test for the loadCoursesFromFile() method. An invalid and valid test file
	 * will be used.
	 * @throws IllegalArgumentException If the file cannot be read from or located.
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog cc = new CourseCatalog();
		//test reading an invalid file
		try {
			cc.loadCoursesFromFile(invalidTestFile);
			assertEquals(0, cc.getCourseCatalog().length);
		} catch (IllegalArgumentException iae){
			fail();
		}

		//test reading a valid file
		try{
			cc.loadCoursesFromFile(validTestFile);
			assertEquals(8, cc.getCourseCatalog().length);
		} catch (IllegalArgumentException iae) {
			fail();
		}
	}
	
	/**
	 * Test CourseCatalog.addCourse().
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		//Attempt to add an invalid course 
		
		//Attempt to add a new course that does exist
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP,  MEETING_DAYS, START_TIME, END_TIME));
		
		//Attempt to add a course that already exists in the catalog
		assertFalse(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP,  MEETING_DAYS, START_TIME, END_TIME));
		
	}
	
	/**
	 * Test CourseCatalog.removeCourseFromCatalog()
	 */
	@Test
	public void testRemoveCourseFromCatalog(){
		CourseCatalog cc = new CourseCatalog();
		//attempt to remove a course from an empty catalog
		assertFalse(cc.removeCourseFromCatalog("NAME", "SECTION"));
		
		//add a course and then attempt to remove a different name
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP,  MEETING_DAYS, START_TIME, END_TIME);
		assertFalse(cc.removeCourseFromCatalog("NAME", "ALDKFJA"));
		//remove the actual course
		assertTrue(cc.removeCourseFromCatalog(NAME, SECTION));
	}
	
	/**
	 * Test CourseCatalog.getCourseCatalog
	 */
	@Test
	public void testGetCourseCatalog(){
		//test valid course retrieval
		CourseCatalog cc = new CourseCatalog();
		Activity course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP,  MEETING_DAYS, START_TIME, END_TIME);
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP,  MEETING_DAYS, START_TIME, END_TIME);
		String[][] course2d = cc.getCourseCatalog();
		assertEquals(NAME, course2d[0][0]);
		assertEquals(SECTION, course2d[0][1]);
		assertEquals(TITLE, course2d[0][2]);
		assertEquals(course.getMeetingString(), course2d[0][3]);
	}
	

	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new File (expFile));
			Scanner actScanner = new Scanner(new File(actFile));
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
