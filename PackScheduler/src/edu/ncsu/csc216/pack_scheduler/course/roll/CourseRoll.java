package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

public class CourseRoll {
	
	/** LinkedAbstractList of Students */
	private LinkedAbstractList<Student> roll;
	/** The roll's enrollment capacity */
	private int enrollmentCap;
	/** The smallest class size allowed */
	private static final int MIN_ENROLLMENT = 10;
	/** The largest class size allowed */ 
	private static final int MAX_ENROLLMENT = 250;
	
	public CourseRoll(int capacity) {
		setEnrollmentCap(capacity);
		roll = new LinkedAbstractList<>(capacity);
	}
	
	public void setEnrollmentCap(int capacity) {
		if(capacity < MIN_ENROLLMENT || capacity > MAX_ENROLLMENT )
			throw new IllegalArgumentException();
		//if not constructed, put the cap at passed in value.
		if(this.roll == null) this.enrollmentCap = capacity;
		//if constructed, only allow changes by increasing the cap.
		if(roll != null && capacity < roll.size()) throw new IllegalArgumentException();
		else this.enrollmentCap = capacity;
	}
	
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}
	
	public int getOpenSeats() {
		return getEnrollmentCap() - roll.size();
	}
	
	public void enroll(Student student) {
		if(student == null || getOpenSeats() < 1 || !canEnroll(student)) throw new IllegalArgumentException();
		//propagating exception to IAE
		try {
			roll.add(student);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public void drop(Student student) {
		if(student == null) throw new IllegalArgumentException();
		//propagating exception to IAE
		try {
			roll.remove(student);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	
	public boolean canEnroll(Student student) {
		if(getOpenSeats() < 1) return false;
		for(int i = 0; i < roll.size(); i++) 
			if(roll.get(i) == student) return false;
		return true;
	}
}
