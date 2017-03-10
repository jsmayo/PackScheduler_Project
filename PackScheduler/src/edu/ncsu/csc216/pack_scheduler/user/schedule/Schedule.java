package edu.ncsu.csc216.pack_scheduler.user.schedule;


import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * Gives a student the ability to track what courses they have scheduled,
 * as well as, the ability to add, remove, and replace the courses within
 * their schedule, and the ability to rename the schedule's title. 
 * 
 *@author Steven Mayo
 */
public class Schedule {

	/** Title of the schedule */
	private String title; 
	/** Custom ArrayList of courses */
	private ArrayList<Course> schedule;
	
	/**
	 * Constructs the Schedule object, which is used to keep track of
	 * what Courses the Student has scheduled. The title is set to
	 * a default of "My Schedule" and an empty array list is created to
	 * hold Course objects. 
	 */
	public Schedule() {
		//default call to set title to My Schedule.
		setTitle("My Schedule");
		this.schedule = new ArrayList<>();
	}
	
	/** 
	 * Attempts to add a course to the student's schedule, but does not 
	 * allow duplicate or invalid courses.
	 * @param c The course to be added to the schedule.
	 * @return true if the course can be added or false if not.
	 * @throws IllegalArgumentException if a course has the same name as 
	 * one already in the students schedule. An exception is also thrown 
	 * if the Course time conflicts with a previously scheduled activity.
	 * @throws NullPointerException if the course parameter is null.
	 */
	public boolean addCourseToSchedule(Course c) {
		//attempting to add to the students schedule. Return false if the same name appears already.
		//Course toSchedule = c; //new course to test.
		if(c == null) throw new NullPointerException(); //takes care of second requirement
		for(int i = 0; i < schedule.size(); i++){
			////go through whole schedule and check duplicates
			if(c.isDuplicate(schedule.get(i))) throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			try {
				c.checkConflict(schedule.get(i));
			} catch (ConflictException ce) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		schedule.add(schedule.size(), c); //if no error > course exists in catalog & not already enrolled > add it to schedule.
		return true; //returning true if it was added.
	}
		
	/**
	 * Removes a course from the student's schedule. The course to be
	 * removed is passed in as a parameter, which will conditionally 
	 * trigger a boolean return value.
	 * @param c The course to be removed from the student's schedule.
	 * @return True if the course was removed.
	 */
	public boolean removeCourseFromSchedule(Course c) {
		int courseFinder = schedule.lastIndexOf(c);
		if(courseFinder != -1) {
			schedule.remove(courseFinder);
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the user's schedule, but keeps the current title.
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<>();
	}
	
	/**
	 * Returns an 2D ArrayList of the student's schedule with 
	 * one course per row  and columns for the name, section, and title.
	 * @return schedule2d String[][] containing a Course per row with the name, 
	 * title, and section in separate columns. into columns. If the schedule 
	 * is empty, an empty String[][] is returned.
	 */
	public String[][] getScheduledCourses() {
		String[][] schedule2d = new String[schedule.size()][4];
		if(schedule.size() == 0) return schedule2d;
		for(int i = 0; i < schedule.size(); i++){
			String[] details = schedule.get(i).getShortDisplayArray();
			for(int y = 0; y < details.length; y++){
				schedule2d[i][y] = details[y];
			}
		}
		return schedule2d;
	}
	
	/**
	 * Rename's the student's schedule to that of the passed in String
	 * parameter.
	 * @param title The new title of the schedule
	 * @throws IllegalArgumentException if the new title is null or empty.
	 */
	public void setTitle(String title) {
		if(title == null || title.isEmpty()) throw new IllegalArgumentException();
		this.title = title;
	}
	
	/**
	 * Getter method to return the schedule's title.
	 * @return The current title of the schedule.
	 */
	public String getTitle() {
		return this.title;
	}
		
		
}
