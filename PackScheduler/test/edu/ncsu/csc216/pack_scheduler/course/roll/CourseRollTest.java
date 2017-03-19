package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

public class CourseRollTest {
	
	StudentDirectory sd = new StudentDirectory();
	
	private final String validTestFile = "test-files/student_records.txt";
	
	/**
	 * Resets student_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
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
	}
	/**
	 * Test for the constructor of CourseRoll.
	 */
	@Test
	public void testCourseRoll() {
		CourseRoll roll = new CourseRoll(20);
		//test that the enrollment cap was set properly.
		assertTrue(roll.getEnrollmentCap() == 20);
		//test that all seats are empty
		assertEquals("20 seats should be open", 20, roll.getOpenSeats());
		
		try {
			roll = new CourseRoll(2);
			fail();
		} catch(IllegalArgumentException e) {
			assertTrue("a new roll should not have been created", roll.getEnrollmentCap() == 20);
		}
	}
	
	@Test
	public void testSetEnrollmentCap() {
		CourseRoll roll = new CourseRoll(20);	
		//test setting the enrollment to an invalid cap
		try {
			roll.setEnrollmentCap(2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("seats should not have changed", 20, roll.getOpenSeats());
		}

		try {
			roll.setEnrollmentCap(300);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("seats should not have changed", 20, roll.getOpenSeats());
		}


		//TODO: add students before testing this roll.setEnrollmentCap(12);
		assertEquals("Cap should only change by increasing below maximum allowed.", 20, roll.getEnrollmentCap());
		roll.setEnrollmentCap(30);
		assertEquals("Cap should have changed to 30", 30, roll.getEnrollmentCap());
	}
	
	@Test
	public void testEnroll() {
		sd.loadStudentsFromFile(validTestFile);
		// get dir, then call .getstudentbyid, with i,2
		String[][] dir = sd.getStudentDirectory();
		Student s = null;
		CourseRoll roll = new CourseRoll(20);
		try {
			roll.enroll(null);
			fail("Should not be able to add a null object");
		} catch (IllegalArgumentException e) {
			assertEquals("seats should all be empty", 20, roll.getOpenSeats());
		}
	
		try {
			s = sd.getStudentById(dir[0][2]);
			roll.enroll(s);
			assertTrue("Ensure proper student retreival", s.getId().equals("daustin"));
			assertEquals("Open seats should be 19", 19, roll.getOpenSeats());
			assertFalse("Make sure the student cannot enroll again", roll.canEnroll(s));
		} catch (Exception e) {
			fail("Should be able to add student" + s.getId());
		}
		
		try {
			roll.enroll(s);
			fail("Should not be able to add the same student");
		} catch (IllegalArgumentException e) {
			assertEquals("seats should remail 19", 19, roll.getOpenSeats());
		}
	
		try {
			for(int i = 1; i < dir.length; i++) {
				roll.enroll(sd.getStudentById(dir[i][2]));
			}
			assertEquals("Open seats should be 10", 10, roll.getOpenSeats());
		} catch (Exception e) {
			fail("Should be able to add all student_records.txt");
		}
	}
	
	

}
