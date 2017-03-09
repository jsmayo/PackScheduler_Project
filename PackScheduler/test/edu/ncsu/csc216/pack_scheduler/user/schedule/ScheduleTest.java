package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

public class ScheduleTest {

	private Schedule schedule;
	private String title;
	private CourseCatalog catalog = new CourseCatalog();
	
	
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
		this.schedule = new Schedule();
		//place valid courses in CourseCatalog
		this.catalog.loadCoursesFromFile(validTestFile);
		
		//test valid course addition.
		assert(schedule.addCourseToSchedule(new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME)));
		
		//test duplicate addition
		try {
			schedule.addCourseToSchedule(new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in " + NAME, e.getMessage());
			assertEquals(1, schedule.getScheduledCourses().length);
		}
		
		
		//test that new courses are added to the end of the catalog.
		assertTrue(schedule.addCourseToSchedule(new Course("MSE", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME)));
		String[][] coursesAdded = new String[2][4];
		//courseAdded[0][0] = NAME;
		//courseAdded[1][0] = "MSE";
		assertTrue(schedule.getScheduledCourses()[0][0].equals(NAME));
		assertTrue(schedule.getScheduledCourses()[1][0].equals("MSE"));
	}
	
}
