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
		s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES); // making a valid Faculty for comparision
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
	
	/**
	 * Test to make sure equals() was over written properly. 
	 */
	@Test
	public void testEqualsObject() {
		// 6 fields, so test on each field for equals in both directions!
		User s1 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s2 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s3 = new Faculty("testing", LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s4 = new Faculty(FIRST, "lastname", ID, EMAIL, PASSWORD, MAXCOURSES);
		User s5 = new Faculty(FIRST, LAST, ID, "different@gmail.edu", PASSWORD, MAXCOURSES);
		User s6 = new Faculty(FIRST, LAST, ID, EMAIL, "password", MAXCOURSES);
		User s7 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, 2);
		User s8 = new Faculty(FIRST, LAST, "different", EMAIL, PASSWORD, MAXCOURSES);
		
		
		//testing for equality in both directions.
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		//test for each field in Faculty constructor.
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));

	}
	
	/**
	 * Test to see if hashCode() was over written properly.
	 */
	@Test
	public void testHashCode() {
		User s1 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s2 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s3 = new Faculty("testing", LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		User s4 = new Faculty(FIRST, "lastname", ID, EMAIL, PASSWORD, MAXCOURSES);
		User s5 = new Faculty(FIRST, LAST, ID, "different@gmail.com", PASSWORD, MAXCOURSES);
		User s6 = new Faculty(FIRST, LAST, ID, EMAIL, "password", MAXCOURSES);
		User s7 = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, 2);
		User s8 = new Faculty(FIRST, LAST, "different", EMAIL, PASSWORD, MAXCOURSES);
		
		//testing for the same hash code in both directions.
		assertEquals(s1.hashCode(), s2.hashCode());
		
		//testing for differing hash code on unequal objects.
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}
	
	/**
	 * Test to see if toString() was over written properly. 
	 */
	@Test
	public void testToString() {
		//test to see if a call to .toString() is equal to expected string results.
		User s = new Faculty(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCOURSES);
		String s1 = "first,last,id,testcase@junit.com,hashedpassword,3";
		assertEquals(s1, s.toString());
	}




}
