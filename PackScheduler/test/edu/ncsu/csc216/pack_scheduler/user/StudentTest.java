package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;


/**
 * Tests the Student class.
 * 
 * Note that test methods for all getters have been omitted.  They
 * will be tested through other methods.
 * 
 * @author Steven Mayo
 */
public class StudentTest {
	
	private static final String FIRST = "first";
	
	private static final String LAST = "last";
	
	private static final String ID = "id";
	
	private static final String EMAIL = "testcase@junit.com";
	
	private static final String PASSWORD = "hashedpassword";
	
	private static final int MAXCREDITS = 18;
	
	
	/**
	 * Tests the Student constructor with all field parameters.
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		try{
			Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		} catch(IllegalArgumentException e){
			fail();
		}
	}
	
		
	/**
	 * test constructor for Student object
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		try{
			Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD);
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		} catch(IllegalArgumentException e){
			fail();
		}
	}
	
	/**
	 * Test setFirstname()
	 */
	@Test
	public void testSetFirstName() {
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS); // making a valid student for comparision
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAXCREDITS, s.getMaxCredits());
		
		try {
			s.setFirstName(null); //test to see if the first name can be set to null
			fail(); 
		} catch (IllegalArgumentException iae){
			//These test to make sure nothing was changed!
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
	
		try {
			s.setFirstName(""); //test to see if the first name can be set to an empty string.
			fail(); 
		} catch (IllegalArgumentException iae){
			//These test to make sure nothing was changed!
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
	}
	
	/**
	 * Test setLastName()	
	 */
	@Test
	public void testSetLastName() {
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS); // making a valid student for comparison.
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAXCREDITS, s.getMaxCredits());
		
		try {
			s.setLastName(null); //test to see if the last name can be set to null
			fail(); 
		} catch (IllegalArgumentException iae){
			//These test to make sure nothing was changed!
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
	
		try {
			s.setLastName(""); //test to see if the last name can be set to an empty string.
			fail(); 
		} catch (IllegalArgumentException iae){
			//These test to make sure nothing was changed!
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
	}
	
	/**
	 * Tests set last name
	 */
	@Test
	public void testSetId() {
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS); // making a valid student for comparison.
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAXCREDITS, s.getMaxCredits());
	}
	
	/**
	 * Tests setEmail()
	 */
	@Test
	public void testSetEmail() {
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS); // making a valid student for comparison.
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAXCREDITS, s.getMaxCredits());

		//test to see if the email can be set to null
		try {
			s.setEmail(null); 
			fail(); 
		} catch (IllegalArgumentException iae){
			//These test to make sure nothing was changed!
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}


		try {
			s.setEmail(""); //test to see if the email can be set to an empty string.
			fail(); 
		} catch (IllegalArgumentException iae){
			//These test to make sure nothing was changed!
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}

		//test to see if not adding a "@" changes any of the student details. 
		try{
			s.setEmail("test.com");
			fail();
		} catch (IllegalArgumentException e){
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		//test to see if not adding a "."  after "@" fails and/if it changes any of the student details. 
		try{
			s.setEmail("test.test@com");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}

		try{
			s.setEmail("email@ncsucom");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
	}

	/**
	 * Tests setPassword()
	 */
	@Test
	public void testSetPassword() {
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS); // making a valid student for comparison.
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAXCREDITS, s.getMaxCredits());
		
		try {
			s.setPassword(null); //test to see if the password can be set to null
			fail(); 
		} catch (IllegalArgumentException iae){
			//These test to make sure nothing was changed!
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
	
		try {
			s.setPassword(""); //test to see if the id can be set to an empty string.
			fail(); 
		} catch (IllegalArgumentException iae){
			//These test to make sure nothing was changed!
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
	}
	
	/**
	 * Tests setMaxCredits()
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS); // making a valid student for comparison.
		assertEquals(FIRST, s.getFirstName());
		assertEquals(LAST, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(PASSWORD, s.getPassword());
		assertEquals(MAXCREDITS, s.getMaxCredits());
		
		try {
			s.setMaxCredits(2); //test to see if the maximum amount of credits can be set to 2
			fail(); 
		} catch (IllegalArgumentException iae){
			//These test to make sure nothing was changed!
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
		
		try {
			s.setMaxCredits(20);
			fail();
		}
		catch (IllegalArgumentException e){
			assertEquals(FIRST, s.getFirstName());
			assertEquals(LAST, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(PASSWORD, s.getPassword());
			assertEquals(MAXCREDITS, s.getMaxCredits());
		}
	}
	
	/**
	 * Test to make sure equals() was over written properly. 
	 */
	@Test
	public void testEqualsObject() {
		// 6 fields, so test on each field for equals in both directions!
		User s1 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s2 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s3 = new Student("testing", LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s4 = new Student(FIRST, "lastname", ID, EMAIL, PASSWORD, MAXCREDITS);
		User s5 = new Student(FIRST, LAST, ID, "different@gmail.edu", PASSWORD, MAXCREDITS);
		User s6 = new Student(FIRST, LAST, ID, EMAIL, "password", MAXCREDITS);
		User s7 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, 8);
		User s8 = new Student(FIRST, LAST, "different", EMAIL, PASSWORD, MAXCREDITS);
		
		
		//testing for equality in both directions.
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		//test for each field in student constructor.
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
		User s1 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s2 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s3 = new Student("testing", LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		User s4 = new Student(FIRST, "lastname", ID, EMAIL, PASSWORD, MAXCREDITS);
		User s5 = new Student(FIRST, LAST, ID, "different@gmail.com", PASSWORD, MAXCREDITS);
		User s6 = new Student(FIRST, LAST, ID, EMAIL, "password", MAXCREDITS);
		User s7 = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, 8);
		User s8 = new Student(FIRST, LAST, "different", EMAIL, PASSWORD, MAXCREDITS);
		
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
		User s = new Student(FIRST, LAST, ID, EMAIL, PASSWORD, MAXCREDITS);
		String s1 = "first,last,id,testcase@junit.com,hashedpassword,18";
		assertEquals(s1, s.toString());
	}
	
	/**
	 * Test for the comparable interface. When applied to Student
	 * objects, the ordering should be last name, first name, then 
	 * ID.
	 */
	@Test
	public void testCompareTo() {
		SortedList<Student> list = new SortedList<>();
		Student s1 = new Student("Shelby", "Anderson", "slander", "slander@ncsu.edu", "pw", 15);
		Student s2 = new Student("Cody", "Adcock", "cadcock", "cadcock@ncsu.edu", "pw", 15);
		Student s3 = new Student("James", "Anderson", "janderson", "janderson@ncsu.edu", "pw", 18);
		Student s4 = new Student("Cody", "Adcock", "cadcock1", "cadcock1@ncsu.edu", "pw", 17);
		Student s5 = new Student("Cody", "Adcock", "cadcock", "cadcock@ncsu.edu", "pw", 15);
		//attempt to add students to the SortedList and check ordering.
		try {
			list.add(s1);
			list.add(s2);
			list.add(s3);
			list.add(s4);
			list.add(s5);
			fail(); //should no be able to add s5, s5 == s2
		} catch (IllegalArgumentException iae){
			assertEquals(list.size(), 4);
			assertEquals(list.get(0), s2);
			assertEquals(list.get(1), s4);
			assertEquals(list.get(2), s3);
			assertEquals(list.get(3), s1);
		}
	}
	
		
}
