package edu.ncsu.csc216.pack_scheduler.course.roll;


import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.util.ArrayQueue;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * The CourseRoll class is responsible for handling the 
 * enrollment cap of each Course within the Course Catalog, as well
 * as providing functionality for a student to enroll or drop a course
 * from their schedule, based on the student's maximum credit hours and
 * the Course's enrollment cap.
 *  
 * @author Steven Mayo
 */
public class CourseRoll {
	
	/** LinkedAbstractList of Students */
	private LinkedAbstractList<Student> roll;
	/** The roll's enrollment capacity */
	private int enrollmentCap;
	/** The smallest class size allowed */
	private static final int MIN_ENROLLMENT = 10;
	/** The largest class size allowed */ 
	private static final int MAX_ENROLLMENT = 250;
	/** Waitlist size */
	private static final int WAITLIST_SIZE = 10;
	/** The waitlist for the current course */
	private ArrayQueue<Student> waitlist;
	private ArrayList<Student> waitListCopy;
	private boolean isWaitListed = false;
	
	
	
	/**
	 * Constructor for the CourseRoll object. An empty LinkedList is created
	 * with the provided capacity parameter.
	 * @param c Course to construct the CourseRoll object for.
	 * @param capacity Capacity of the LinkedList, which corresponds to the 
	 * enrollmentCap of the Course object.
	 */
	public CourseRoll(Course c, int capacity) {
		if(c == null) throw new IllegalArgumentException();
		setEnrollmentCap(capacity);
		roll = new LinkedAbstractList<>(capacity);
		waitlist = new ArrayQueue<Student>(WAITLIST_SIZE); //defaults to capacity of 10;
	}
	
	/**
	 * Sets the enrollment cap for the current Course object. If the specified
	 * capacity is outside of the minimum or maximum enrollment requirements, or
	 * the capacity is set below the number of currently enrolled students, then an
	 * IllegalArgumentException will be thrown. 
	 * @param capacity The enrollment cap for the provided Course object.
	 * @throws IllegalArgumentException Thrown if the enrollment cap is set outside of
	 * the course's minimum or maximum enrollment limit, as well if the enrollment cap
	 * is set below the current number of enrolled students.
	 */
	public void setEnrollmentCap(int capacity) {
		if(capacity < MIN_ENROLLMENT || capacity > MAX_ENROLLMENT )
			throw new IllegalArgumentException();
		//if not constructed, put the cap at passed in value.
		if(this.roll == null) {
			this.enrollmentCap = capacity;
			roll = new LinkedAbstractList<>(capacity);
			roll.setCapacity(capacity);
			
		}
		//if constructed, only allow changes by increasing the cap.
		if(roll != null && capacity < roll.size()) throw new IllegalArgumentException();
		else {
			this.enrollmentCap = capacity;
			if(roll != null) roll.setCapacity(capacity);
			if(roll == null) roll = new LinkedAbstractList<>(capacity);
			roll.setCapacity(capacity);
		}
	}
	
	/**
	 * Getter method that returns the current course's enrollment capacity.
	 * @return Enrollment cap of the current course.
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}
	
	/**
	 * Getter method that returns the number of open seat's for the current 
	 * Course object.
	 * @return The number of open seats available for the current Course.
	 */
	public int getOpenSeats() {
		return getEnrollmentCap() - roll.size();
	}
	
	/**
	 * Attempts to enroll a student into the current Course by checking the Course's
	 * enrollment capacity and opening seating, as well as the Student's remaining Course
	 * hours. 
	 * @param student The Student to enroll into the current Course.
	 * @throws IllegalArgumentException if the Student to enroll is null or cannot enroll
	 * into the current Course. If an exception is encountered while attempting to add 
	 * a Student to current CourseRoll, then it is propagated to the user.
	 */
	public void enroll(Student student) {
		if(student == null || waitlist.size() == CourseRoll.WAITLIST_SIZE || !canEnroll(student)) throw new IllegalArgumentException();
		if(this.getOpenSeats() < 1 && waitlist.size() < WAITLIST_SIZE) {
			waitlist.enqueue(student);
			isWaitListed = true;
			//add return here if not wantint to check for duplicates!
		}
		try {
			roll.add(student);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	/**
	 * Removes a student from the current Course's course roll. 
	 * @param student Student to remove from the current Course's course roll.
	 * @throws IllegalArgumentException if the student to remove is null or an
	 * Exception is thrown while attempting to remove the current Student from the
	 * CourseRoll object.
	 */
	public void drop(Student student) {
		if(student == null) throw new IllegalArgumentException();
		if(waitlist.size() < 1) {
			try {
				roll.remove(student);
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
		else if(waitlist.size() > 0) {
			Student waitlisted = null;
			waitListCopy = new ArrayList<Student>();
			for(int i = 0; i < waitlist.size(); i++) {
				waitlisted = waitlist.dequeue();
				if(waitlisted == student) {
					isWaitListed = false;
					continue; //skip the student if a match is found
				}
				else waitListCopy.add(waitlisted); //add non matches to the copy
			}
			for(int i = 0; i < waitListCopy.size(); i++) waitlist.enqueue(waitListCopy.get(i)); //re-queue the list from the copy
		}

	}
	
	/**
	 * Checks to see if the specified Student can enroll into the current Course by
	 * checking if the number of available seats is at least 1 or greater.
	 * @param student Student to enroll into the selected course.
	 * @return True if there is at least 1 seat left available for the student to 
	 * occupy.
	 */
	public boolean canEnroll(Student student) {
		if(getOpenSeats() < 1 && waitlist.size() == WAITLIST_SIZE) return false;
		for(int i = 0; i < roll.size(); i++) 
			if(roll.get(i) == student || isWaitListed == true) return false;
		return true;
	}
	
	public int getNumberOnWaitList() {
		return waitlist.size();
	}
			
}
