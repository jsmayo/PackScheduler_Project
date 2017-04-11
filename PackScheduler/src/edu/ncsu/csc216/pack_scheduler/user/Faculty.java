package edu.ncsu.csc216.pack_scheduler.user;


/**
 * Represents a Faculty object for the PackScheduler application.
 * @author Steven
 *
 */
public class Faculty extends User {

	/** Number of courses assigned per sememster */
	private int maxCourses;
	/** Minimum number of courses allowed to teach per semester */
	public static final int MIN_COURSES = 1;
	/** Maximum number of courses allowed to teach per semester */
	public static final int MAX_COURSES = 3;
	
	/**
	 * Constructor for a Faculty member.
	 * @param firstName First name of the Faculty member.
	 * @param lastName Last name of the Faculty member
	 * @param id ID of the faculty member.
	 * @param email Email of the Faculty member.
	 * @param hashedPassword Hashed password value for the Faculty member.
	 * @param maxCourses Number of courses assigned to teach per semester for the Faculty member.
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashedPassword, int maxCourses) {
		super(firstName, lastName, id, email, hashedPassword);
		setMaxCourses(maxCourses);
		//this.schedule = new Schedule();
	}
	
	/**
	 * Sets the amount of Courses that the Faculty member can teach.
	 * @param maxCourses Number of courses that the Faculty member can teach.
	 * @throws IllegalArgumentException if the provided parameter is outside of the 
	 * Faculty course boundaries.
	 */
	public void setMaxCourses(int maxCourses) {
		if(maxCourses < Faculty.MIN_COURSES || maxCourses > Faculty.MAX_COURSES)
			throw new IllegalArgumentException();
		this.maxCourses = maxCourses;
	}
	
	/**
	 * Gets the number of courses being taught by the Faculty member.
	 * @return Number of courses being taught by the Faculty member.
	 */
	public int getMaxCourses() {
		return this.maxCourses;
	}

	/**
	 * Overwritten hashcode to check for equality of Faculty member on all fields. 
	 * @return int Vaue of the hashcode generated.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Overwritten Equals method to check for equality of Faculty member on all fields. 
	 * @return true if the Faculty members are equal on all fields. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all faculty fields.
	 * @return String representation of Faculty
	 */
	@Override
	public String toString() {
		return this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," + this.getEmail() + "," + this.getPassword() + "," + this.getMaxCourses();
	}
	

	
	
}
	
	


