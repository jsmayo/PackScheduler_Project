package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Creates Course Objects for the WolfScheduler application. Each Course Object
 * keeps track of its name, title, section, credit hours, instructor id, meeting
 * days, start time, and end time.
 * 
 * @author Steven Mayo
 */
public class Course extends Activity implements Comparable<Course> {

	/** Course's name. */
	private String name;
	/** Course name validator */
	private CourseNameValidator validator;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course roll */
	private CourseRoll roll;

	
	
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours of Course
	 * @param instructorId Instructor's unity id
	 * @param enrollmentCap Enrollment cap for the Course.
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		validator = new CourseNameValidator();
		setName(name);
	    //setTitle(title);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    this.roll = new CourseRoll(this, enrollmentCap);
	    //setMeetingDays(meetingDays);
	    //setCourseTime(startTime, endTime);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorID, and meeting days for
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours of Course
	 * @param instructorId Instructor's unity id
	 * @param enrollmentCap Enrollment cap for the Course.
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0); 
		//created to reduce redundancy and have a one path constructor. 
	}
	

	/**
	 * Returns the Course name.
	 * @return the name of the Course
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 4 or 
	 * greater than 6, an IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or length is less than 4 or 
	 * greater than 6
	 */
	private void setName(String name) {
	    if (name == null) {
	        throw new IllegalArgumentException("Invalid name");
	    }
	    if (name.length() < 4 || name.length() > 6) {
	        throw new IllegalArgumentException("Invalid name");
	    }
		try {
			if(validator.isValid(name))
					this.name = name;
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid name");
		}
	}
	
	/**
	 * Returns the Course section.
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the Course section.
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section String is null or empty. 
	 */
	public void setSection(String section) {
		if(section == null || section.isEmpty()) throw new IllegalArgumentException("Invalid section");
		int charCount = 0;
		for(char c : section.toCharArray()){
			if(!Character.isDigit(c)) throw new IllegalArgumentException("Invalid section");
			charCount++;
		}
		if(charCount != 3) throw new IllegalArgumentException("Invalid section");
		this.section = section;
	}
	
	/**
	 * Returns the Course credit hours.
	 * @return credits the Course credit hours.
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course credit hours.
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if(credits < 1 || credits > 5) throw new IllegalArgumentException("Max credits must be a positive number between 3 and 18.");
		this.credits = credits;
	}
	
	/**
	 * Returns the instructor's ID.
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the instructors ID.
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if(instructorId.isEmpty()) throw new IllegalArgumentException("Invalid instructor id");
		//Removed null check to allow Registrar to set the Course's professor. 
		this.instructorId = instructorId;
	}
	

	/**
	 * Sets the course meeting days.
	 * @param meetingDays the meetingDays to set
	 * @throws IllegalArgumentException if a meeting day outside of 'A', 'M', 'T', 'W', 'H', 'F' 
	 * is specified as an argument. Additionally, an IllegalArgumentException is thrown if and 
	 * only if 'A' is an argument and not the only meeting day specified as an argument.
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if(meetingDays == null || meetingDays.isEmpty()) throw new IllegalArgumentException("Invalid meeting days");
		//test to see if AMTWHF are the only characters shown.
		for(int i = 0; i < meetingDays.length(); i++){
			char c = meetingDays.charAt(i);
			if(c == 'A' || c == 'M' || c == 'T' || c == 'W' || c == 'H' || c == 'F') continue;
			else throw new IllegalArgumentException("Invalid meeting days");
		}
		//test to see if A is the only character present if Arranged is selected.
		if(meetingDays.contains("A") && meetingDays.length() != 1) throw new IllegalArgumentException("Invalid meeting days");
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Getter method that returns the Course roll.
	 * @return roll The current Course roll. 
	 */
	public CourseRoll getCourseRoll() {
		return this.roll;
	}
	/** 
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for Course.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/** 
	 * Compares a given object to this object for equality on all fields.
	 * @param obj the Object to compare.
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if (getMeetingDays().equals("A")) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}

	/**
	 * Returns an array containing course information.
	 * @return array of length 4 containing the Course name, section, title, and meeting day string. 
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[5];
		shortDisplay[0] = getName();
		shortDisplay[1] = getSection();
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		shortDisplay[4] = roll.getOpenSeats() + "";
		return shortDisplay;
	}
	
	/**
	 * Returns an array containing a detailed list of course information.
	 * @return array of length 7 containing the Course name, section, title, credits, instructorId,
	 * meeting day string, and an empty string.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = getName();
		longDisplay[1] = getSection();
		longDisplay[2] = getTitle();
		longDisplay[3] = "" + getCredits();
		longDisplay[4] = getInstructorId();
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";
		return longDisplay;
	}

	@Override
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course) {
			Course fromSchedule = (Course) activity;
			if(this.getName().equals(fromSchedule.getName())) return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Course c) {
		//get the two last names for comparison as a char array
		char[] callingName = this.getName().toCharArray();
		char[] callingSection = this.getSection().toCharArray();
		char[] compareName = c.getName().toCharArray();
		char[] compareSection = c.getSection().toCharArray();

		//get the shortest length for the Course name to avoid boundary errors and double looping
		//start the first name comparison if the last name doesn't return a value
		//get the shortest first name to start the for loop. 
		//ensures iterating through an entire name without a boundary error.
		int shortestlength = (callingName.length <= compareName.length) ? callingName.length : compareName.length;
		for(int i = 0; i < shortestlength; i++){
			if(callingName[i] < compareName[i]) return -1;
			if(callingName[i] > compareName[i]) return 1;
			continue;
		}
		if(callingName.length != compareSection.length){
			if(callingName.length > compareName.length) return 1;
			if(callingName.length < compareName.length) return -1;
		}
		
		shortestlength = (callingSection.length <= compareSection.length) ? callingSection.length : compareSection.length;
		//for loop to compare character digit values. 
		for(int i = 0; i < shortestlength; i++){
			if(callingSection[i] < compareSection[i]) return -1;
			if(callingSection[i] > compareSection[i]) return 1;
			//if(callingLast[i] == compareLast[i]) continue;
		}
		//make sure that similar, course Titles do not pass through
		if(callingSection.length != compareSection.length) {
			if(callingSection.length > compareSection.length) return 1;
			if(callingSection.length < compareSection.length) return -1;
		}
		return 0;
	}
}
