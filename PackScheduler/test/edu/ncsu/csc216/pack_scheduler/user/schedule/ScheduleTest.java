package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class that is used to ensure proper functionality of the
 * Schedule class, as well as, ensure that the implementation does not
 * result in any form of regression.
 * 
 *@author Steven Mayo
 */
public class ScheduleTest {

	private Schedule schedule;
	private CourseCatalog catalog = new CourseCatalog();
	
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";

		
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
	 * Test Schedule constructor for proper functionality.
	 */
	@Test
	public void testSchedule() {
		this.schedule = new Schedule();
		
		//test default title
		assertEquals("My Schedule", schedule.getTitle());
		//test that the schedule is empty.
		assertTrue(schedule.getScheduledCourses().length == 0);
	}
	
	/**
	 * Test removeCourseFromSchedule() for proper functionality.
	 */
	@Test
	public void testAddCourseToSchedule() {
		//make a new schedule object
		schedule = new Schedule();
		
		//load valid courses using catalog (dont have to do)
		catalog.loadCoursesFromFile(validTestFile);
		
		//make my own course and add to catalog, then attempt to add.
		if(catalog.addCourseToCatalog("MSE300", "MaterialScienceEngineering", "300", 3, "lburg", "M", 810, 811))
			assertTrue(schedule.addCourseToSchedule(catalog.getCourseFromCatalog("MSE300", "300")));
		//get courses from catalog
		String[][] courses = catalog.getCourseCatalog();
		Course c1 = catalog.getCourseFromCatalog(courses[0][0], courses[0][1]);
		Course c2 = catalog.getCourseFromCatalog(courses[3][0], courses[3][1]);
		//try adding valid courses
		assertTrue(schedule.addCourseToSchedule(c1));
		assertTrue(schedule.addCourseToSchedule(c2));
		
		//test ordering (added to end of schedule)
		String[][] scheduledC = schedule.getScheduledCourses();
		assertTrue(scheduledC[0][0].equals("MSE300"));
		assertTrue(scheduledC[1][0].equals(courses[0][0]));
		assertTrue(scheduledC[2][0].equals(courses[3][0]));
		
		//test adding a duplicate
		try {
			schedule.addCourseToSchedule(c1);
			fail();
		} catch (IllegalArgumentException e) {
			//test that length did not change and ordering stayed the same.
			assertEquals(3, schedule.getScheduledCourses().length);
			scheduledC = schedule.getScheduledCourses();
			assertTrue(scheduledC[0][0].equals("MSE300"));
			assertTrue(scheduledC[1][0].equals(courses[0][0]));
			assertTrue(scheduledC[2][0].equals(courses[3][0]));
		}
		
		//test adding a null course
		try {
			schedule.addCourseToSchedule(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(3, schedule.getScheduledCourses().length);
			scheduledC = schedule.getScheduledCourses();
			assertTrue(scheduledC[0][0].equals("MSE300"));
			assertTrue(scheduledC[1][0].equals(courses[0][0]));
			assertTrue(scheduledC[2][0].equals(courses[3][0]));
		}

	}
	
	/**
	 * Test for proper functionality of removeCourseFromSchedule()
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		//make a new schedule object
		schedule = new Schedule();
		
		//test removing from empty schedule
		assertFalse(schedule.removeCourseFromSchedule(new Course("MSE300", "MaterialScienceEngineering", "300", 3, "lburg", "M", 810, 811)));

		//load valid courses using catalog
		catalog.loadCoursesFromFile(validTestFile);
		catalog.addCourseToCatalog("MSE300", "MaterialScienceEngineering", "300", 3, "lburg", "M", 810, 811);
		assertTrue(schedule.addCourseToSchedule(catalog.getCourseFromCatalog("MSE300", "300")));
		//get courses from catalog
		String[][] courses = catalog.getCourseCatalog();
		Course c1 = catalog.getCourseFromCatalog(courses[0][0], courses[0][1]);
		Course c2 = catalog.getCourseFromCatalog(courses[3][0], courses[3][1]);
		//Add valid courses to schedule
		assertTrue(schedule.addCourseToSchedule(c1));
		assertTrue(schedule.addCourseToSchedule(c2));
		String[][] scheduledC = schedule.getScheduledCourses();
		assertTrue(scheduledC[0][0].equals("MSE300"));
		assertTrue(scheduledC[1][0].equals(courses[0][0]));
		assertTrue(scheduledC[2][0].equals(courses[3][0]));
		
		//test removing valid courses from the schedule
		assertTrue(schedule.removeCourseFromSchedule(new Course("MSE300", "MaterialScienceEngineering", "300", 3, "lburg", "M", 810, 811)));
		scheduledC = schedule.getScheduledCourses();
		//test that the first course was removed and contents shifted correctly.
		assertTrue(scheduledC[0][0].equals(courses[0][0]));
		assertTrue(scheduledC[1][0].equals(courses[3][0]));
		
	}
	
	/**
	 * Test to ensure the schedule title changes
	 */
	@Test
	public void testSetTitle() {
		schedule = new Schedule();
		//tested default title prior
		schedule.setTitle("Awesomeness");
		assertTrue("Awesomeness".equals(schedule.getTitle()));
		
		//try to set title to null
		try {
			schedule.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			//test that the title did not change.
			assertTrue("Awesomeness".equals(schedule.getTitle()));
		}
		
		//try to set title to empty string
		try {
			schedule.setTitle("");
			fail();
		} catch (IllegalArgumentException e) {
			//test that the title did not change.
			assertTrue("Awesomeness".equals(schedule.getTitle()));
		}
	}
	
	/**
	 * Test resetting the schedule using resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		schedule = new Schedule();
		//change the title and add a course to the schedule.
		schedule.setTitle("CatsAreNotAlwaysSoNice");
		assertTrue(schedule.addCourseToSchedule(new Course("MSE300", "MaterialScienceEngineering", "300", 3, "lburg", "M", 810, 811)));
		
		//test resetting the schedule does NOT revert to default title
		schedule.resetSchedule();
		assertFalse(schedule.getTitle().equals("My Schedule"));
		assertTrue(schedule.getTitle().equals("CatsAreNotAlwaysSoNice"));
		//test that the schedule is empty
		assertTrue(schedule.getScheduledCourses().length == 0);
	}

	
}
