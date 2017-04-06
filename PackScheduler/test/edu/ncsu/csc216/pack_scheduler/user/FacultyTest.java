package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Faculty class.
 * 
 * Note that test methods for all getters have been omitted.  They
 * will be tested through other methods.
 * 
 * @author Steven Mayo
 */
public class FacultyTest {

	private static final String FIRST = "first";
	
	private static final String LAST = "last";
	
	private static final String ID = "id";
	
	private static final String EMAIL = "testcase@junit.com";
	
	private static final String PASSWORD = "hashedpassword";
	
	private static final int MAXCOURSES= 3;

	private Faculty s;
	
	/**
	 * Tests for the Faculty user constructor.
	 */
	@Test
	public void testFaculty() {
		s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES); // making a valid student for comparision
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAXCOURSES, s.getMaxCourses());
		
		//TEST FOR SETMAXCOURSES 
		s.setMaxCourses(1);
		assertEquals(1, s.getMaxCourses());
		
		try {
			s.setMaxCourses(4);
			fail("Should not be a ble to set courses over " + MAXCOURSES);
			
		} catch (IllegalArgumentException e) {
			assertEquals("Max credits should not have changed", 1, s.getMaxCourses());
		}
	}
	
	
	




}
