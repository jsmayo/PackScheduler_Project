package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Creates a Student Object to be used with the StudentRecordIO class.
 * Students are only created if the input is valid. Conditions exist for
 * the Student's: first name, last name, id, email, password, and maximum credit hours.
 * @author Steven Mayo with partial skeleton code from Sarah Heckman
 */
public class Student extends User implements Comparable<Student> {
	/** Integer for the maximum number of credits available to the student */
	private int maxCredits;
	/** The default maximum number of credits allowed at NC State */
	public static final int MAX_CREDITS = 18;
	private Schedule schedule;

	/**
	 * Student constructor that creates a new student object from passed in parameters using setter methods.
	 * @param firstName Student's first name.
	 * @param lastName Student's last name.
	 * @param id Students identification tag.
	 * @param email Student's email address.
	 * @param hashedPassword A pre-hashed password for the students account.
	 * @param maxCredits The maximum number of credits available to the student. 
	 */
	public Student(String firstName, String lastName, String id, String email, String hashedPassword, int maxCredits)  {
		super(firstName, lastName, id, email, hashedPassword);
		setMaxCredits(maxCredits);
		this.schedule = new Schedule();
		
	}
	
	/**
	 * An overloaded constructor for the Student Object. It uses the maximum number of courses a student can take
	 * at NC State if one is not provided initially. 
	 * @param firstName Student's first name.
	 * @param lastName Student's last name.
	 * @param id Student's identification tag.
	 * @param email Student's email address.
	 * @param hashedPassword Student's pre-hashed password. 
	 */
	public Student(String firstName, String lastName, String id, String email, String hashedPassword) {
		this(firstName, lastName, id, email, hashedPassword, MAX_CREDITS); //passes to other constructor with a default value of 18 for credits.
	}
	
	/**
	 * Retrieves the maximum number of course available to the student.
	 * @return maxCredits
	 */
	public int getMaxCredits(){
		return maxCredits;
	}
	
	/**
	 * Sets the student's maximum number of credits available to that particular student, which must be greater than
	 * 3 hours and less than 18 hours.
	 * @param maxCredits The student's maximum number of credits they can register for.
	 * @throws IllegalArgumentException if exceeding or falling short of the requirements of NC State.
	 */
	public void setMaxCredits(int maxCredits){
		if(maxCredits < 3 || maxCredits > 18) throw new IllegalArgumentException("Invalid max credits");
		this.maxCredits = maxCredits;
	}
	
	/**
	 * Getter method for the student's schedule. 
	 * @return Schedule the student's schedule.
	 */
	public Schedule getSchedule() {
		return this.schedule;
	}

	@Override
	public int compareTo(Student s) {
		//get the two last names for comparison as a char array
		char[] callingFirst = this.getFirstName().toCharArray();
		char[] callingLast = this.getLastName().toCharArray();
		char[] callingID = this.getId().toCharArray();
		char[] compareFirst = s.getFirstName().toCharArray();
		char[] compareLast = s.getLastName().toCharArray();
		char[] compareID = s.getId().toCharArray();
		
		//get the shortest length for the last name to avoid boundary errors and double looping
		int shortestlength = (callingLast.length <= compareLast.length) ? callingLast.length : compareLast.length;
		
		//for loop to compare character digit values. 
		for(int i = 0; i < shortestlength; i++){
			if(callingLast[i] < compareLast[i]) return -1;
			if(callingLast[i] > compareLast[i]) return 1;
			//if(callingLast[i] == compareLast[i]) continue;
		}
		//make sure that similar, but different last names don't pass through
		//i.e (Williams vs Williamsons)
		if(callingLast.length != compareLast.length) {
			if(callingLast.length > compareLast.length) return 1;
			if(callingLast.length < compareLast.length) return -1;
		}
		//start the first name comparison if the last name doesn't return a value
		//get the shortest first name to start the for loop. 
		//ensures iterating through an entire name without a boundary error.
		shortestlength = (callingFirst.length <= compareFirst.length) ? callingFirst.length : compareFirst.length;
		for(int i = 0; i < shortestlength; i++){
			if(callingFirst[i] < compareFirst[i]) return -1;
			if(callingFirst[i] > compareFirst[i]) return 1;
			continue;
		}
		if(callingFirst.length != compareLast.length){
			if(callingFirst.length > compareFirst.length) return 1;
			if(callingFirst.length < compareFirst.length) return -1;
		}
		
		//start the ID comparison if the last and first name comparisons didn't return a value.
		//shortest ID assigned to abvoid boundary errors and double looping
		shortestlength = (callingID.length <= compareID.length) ? callingID.length : compareID.length;
		for(int i = 0; i < shortestlength; i++){
			if(callingID[i] < compareID[i]) return -1;
			if(callingID[i] > compareID[i]) return 1;
			continue;
		}
		if(callingID.length != compareID.length){
			if(callingID.length > compareID.length) return 1;
			if(callingID.length < compareID.length) return -1;
		}
		return 0;
	}

	/**
	 * Generates a hashcode for the Student class using the fields inherited 
	 * from the abstract User class and the objects defined within the Student class.
	 * @return hashCode for Student class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * @param obj the object to compare.
	 * @return will return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}
	
	/**
	 * Returns a comma separated value String of all student fields/
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," + this.getEmail() + "," + this.getPassword() + "," + this.getMaxCredits();
	}
	
	/**
	 * Checks to see if a course can be added to the student's schedule.
	 * If the course is null, already present, conflicts with another scheduled
	 * course, or will exceed the maximum credits alloted to the student, then
	 * false is returned. 
	 * @param course Course to check for scheduling.
	 * @return True if the course can be added to the schedule.
	 */
	public boolean canAdd(Course course) {
		if(schedule.canAdd(course) == true && ((schedule.getScheduleCredits() + course.getCredits()) <= this.getMaxCredits())) 
			return true;
		else return false;
	}


}
		