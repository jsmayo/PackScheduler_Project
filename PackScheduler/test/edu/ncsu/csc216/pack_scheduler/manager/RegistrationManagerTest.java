package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests to ensure that implementation of the RegistrationManager class,
 * as well as, the Registrar inner class, work as intended with no loss in
 * overall functionallity.
 * 
 *@author Steven Mayo
 */
public class RegistrationManagerTest {
	/** Instance class needed for RegistrationManager */
		private RegistrationManager manager;
	
		
	/**
	 * Sets up the CourseManager and clears data within the studentDirectory and
	 * courseCatalog fields.
	 * @throws Exception if an error occurs.
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

	/**
	 * Test to ensure that the studentDirectory field is assigned properly.
	 */
	@Test
	public void testGetStudentDirectory() {
		//Test for creation of a valid, unpopulated studentDirectory. 
		manager = RegistrationManager.getInstance();
		assertFalse(manager.getStudentDirectory() == null);
	
		//Test for a valid and populated studentDirectory.
		StudentDirectory sd = manager.getStudentDirectory();
		sd.addStudent("Jake", "TheDog", "jakethedog", "jakethedog@ncsu.edu", "pw", "pw", 4);
		String[][] sdd = sd.getStudentDirectory();
		assertEquals(sdd[0][0], "Jake");
		assertEquals(sdd[0][1], "TheDog");
		assertEquals(sdd[0][2], "jakethedog");
	}
	
	/**
	 * Test for the login() method of RegistrationManager.
	 */
	@Test
	public void testLogin() {
		//Tests for a valid student login
		manager = RegistrationManager.getInstance();
		StudentDirectory sd = manager.getStudentDirectory();
		sd.addStudent("Jake", "TheDog", "jakethedog", "jakethedog@ncsu.edu", "pw", "pw", 4);
		assertTrue(manager.login("jakethedog", "pw"));
		manager.logout();
		
		//Tests for an invalid student login attempt
		assertFalse(manager.login("jakethedog", "notjakethedogspassword"));		
		
		//Test for an invalid student		
		assertFalse(manager.login("jakethedog", "notjakethedogspassword"));
		
		//Test for a valid registrar login
		assertTrue(manager.login("registrar", "Regi5tr@r"));
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
		sd.addStudent("Jake", "TheDog", "jakethedog", "jakethedog@ncsu.edu", "pw", "pw", 4);
		assertTrue(manager.login("jakethedog", "pw"));
		manager.logout();
		assertEquals(null, manager.getCurrentUser());
	
		//Test logging in and out of a registrar profile
		assertTrue(manager.login("registrar", "Regi5tr@r"));
		assertTrue(manager.getCurrentUser() != null);
		manager.logout();
		assertNull(manager.getCurrentUser());
	}
		
	/**
	 * Test to ensure that the currentUser field updates as intended
	 * when logging in and out as a student or registrar.
	 */
	@Test
	public void testGetCurrentUser() {
		//test for null user value when first started.
		manager = RegistrationManager.getInstance();
		assertNull(manager.getCurrentUser());
		
		//Test for student login
		manager.logout();
		StudentDirectory sd = manager.getStudentDirectory();
		sd.addStudent("Jake", "TheDog", "jakethedog", "jakethedog@ncsu.edu", "pw", "pw", 4);
		manager.login("jakethedog", "pw");
		assertTrue(manager.getCurrentUser() instanceof Student);
		manager.logout();
		assertEquals(null, manager.getCurrentUser());
		
		//Test for registrar login
		manager.login("registrar", "Regi5tr@r");
		assertFalse(manager.getCurrentUser() instanceof Student);
		assertNotNull(manager.getCurrentUser());
		manager.logout();
		assertNull(manager.getCurrentUser());
	}

}