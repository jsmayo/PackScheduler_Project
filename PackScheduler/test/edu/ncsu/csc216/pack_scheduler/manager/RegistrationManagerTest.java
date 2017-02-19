package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;


public class RegistrationManagerTest {
		private RegistrationManager manager;
	
		
	/**
	 * Sets up the CourseManager and clears the data.
	 * @throws Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		
		manager = RegistrationManager.getInstance();
		manager.clearData();
	}

	/**
	 * Test for the RegistrationManager.getCourseCatalog() method.
	 */
	@Test
	public void testGetCourseCatalog() {
		//Test's to see if an object is returned.
		manager = RegistrationManager.getInstance();
		assertFalse(manager.getCourseCatalog() == null);
	//make an instance, then test?
	}

	@Test
	public void testGetStudentDirectory() {
		//Test's to see if an object is returned.
		manager = RegistrationManager.getInstance();
		assertFalse(manager.getStudentDirectory() == null);
	//make an instance, then test?
	}
	
	/**
	 * Test for the login() method of RegistrationManager.
	 */
	@Test
	public void testLogin() {
		//Tests for a valid student login
		manager = RegistrationManager.getInstance();
		StudentDirectory sd = manager.getStudentDirectory();
		sd.addStudent("Jake", "TheDog", "jakethedog","jakethedog@ncsu.edu", "pw", "pw", 4);
		assertTrue(manager.login("jakethedog","pw"));
		manager.logout();
		
		//Tests for an invalid student login attempt
		assertFalse(manager.login("jakethedog", "notjakethedogspassword"));		
		
		//Test for an invalid student		
		assertFalse(manager.login("jakethedog", "notjakethedogspassword"));
		
		//Test for a valid registrar login
		assertTrue(manager.login("registrar","Regi5tr@r"));
		manager.logout();
		
		//Test for invalid registrar login
		assertFalse(manager.login("registrar", "nope"));
		
	}
	
	/**
	 * Test that the logout() method sets the current user to a null value.
	 */
	@Test
	public void testLogout() {
		manager = RegistrationManager.getInstance();
		//Test logging in and out of a student profile
		StudentDirectory sd = manager.getStudentDirectory();
		sd.addStudent("Jake", "TheDog", "jakethedog","jakethedog@ncsu.edu", "pw", "pw", 4);
		assertTrue(manager.login("jakethedog","pw"));
		manager.logout();
		assertEquals(null, manager.getCurrentUser());
	
		//Test logging in and out of a registrar profile
		assertTrue(manager.login("registrar", "Regi5tr@r"));
		assertTrue(manager.getCurrentUser() != null);
		manager.logout();
		assertNull(manager.getCurrentUser());
	}
		
	

	@Test
	public void testGetCurrentUser() {
		fail("Not yet implemented");
	}

}