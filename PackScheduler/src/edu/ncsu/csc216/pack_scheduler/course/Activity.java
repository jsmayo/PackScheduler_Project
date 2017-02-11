package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Creates abstract Activity Objects for the WolfScheduler application. Each Activity Object
 * has fields for the title, meeting days, start time, and end time, as well as methods that
 * will be shared between Course and Event objects. 
 * 
 * @author Steven Mayo
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Constructor for the Activity class.
	 * @param title the title of the activity.
	 * @param meetingDays the meeting days for the activity.
	 * @param startTime the start time of the activity.
	 * @param endTime the ending time of the activity.
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}
	
	/**
	 * Abstract method declared here to allow both Course and Event Objects to 
	 * define their own specifics for duplicate Objects.
	 *
	 * @param activity the activity to compare for duplicity. 
	 * @return true if the Activity Object is a duplicate.
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/** 
	 * Compares two activities to see if there is any conflict with their meeting
	 * days and time. Activities are in conflict if there is at least one day 
	 * with an overlapping time. A time is also overlapping if the minutes are the same.
	 * @param possibleConflictingActivity the activity instance to compare the current
	 * activity against.
	 * @throws ConflictException if at least one day has an overlapping time between the
	 * two compared activities.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		String a1days = this.getMeetingDays();
		String a2days = possibleConflictingActivity.getMeetingDays();
		String switchdays = "";
		//get the shortest string length on a1Meet to avoid loop errors.
		if(a1days.length() >= a2days.length()) {
			switchdays = a2days;
			a2days = a1days;
			a1days = switchdays;
		}
		for(int i = 0; i < a1days.length(); i++){
			char day = a1days.charAt(i);
			if(day == 'A') continue;
			for(int j = 0; j < a2days.length(); j++){
				if(day == a2days.charAt(j)) {
						//check to see if the start time of THIS (toSchedule) falls after a scheduled Start and before scheduled end.
					if((this.getStartTime() >= possibleConflictingActivity.getStartTime()) && (possibleConflictingActivity.getEndTime()  >= this.getStartTime()))
						throw new ConflictException();
					//check to see if the end time of THIS (toSchedule) falls after a scheduled Start and before scheduled end.
					if((this.getStartTime() <= possibleConflictingActivity.getStartTime()) && (this.getEndTime() >= possibleConflictingActivity.getStartTime()))
						throw new ConflictException();
					//check to see if THIS (toSchedule) occurs between the start and end of a scheduled activity.
					if((this.getStartTime() <= possibleConflictingActivity.getStartTime()) && (this.getEndTime() >= possibleConflictingActivity.getEndTime()))
						throw new ConflictException();
				
				}
			//else continue; //make sure the loop continues through the iterations.
			}	
		}
	}

	/**
	 * Abstract method allowing both Course and Event Objects to return a string array
	 * containing a brief amount of specific field values.
	 * @return string array containing field related information.
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Abstract method allowing both Course and Event Objects to return a string array
	 * containing the full list of field values.
	 * @return string array containing field related information.
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Returns the Course title.
	 * @return title the title of the course
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course title.
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title String is null or empty. 
	 */
	public void setTitle(String title) {
		if(title == null || title.isEmpty()) throw new IllegalArgumentException();
		this.title = title;
	}

	/**
	 * Returns the Course meeting days.
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the course meeting days.
	 * @param meetingDays the meetingDays to set
	 * @throws IllegalArgumentException if a meeting day outside of 'A', 'M', 'T', 'W', 'H', 'F' 
	 * is specified as an argument. Additionally, an IllegalArgumentException is thrown if and 
	 * only if 'A' is an argument and not the only meeting day specified as an argument.
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Sets the Course start and end time using military format (0000 - 2359).
	 * @param startTime Course start time.
	 * @param endTime Course end time. 
	 * @throws IllegalArgumentException if the start or end time is not in proper military time. The
	 * range is from 0000 - 2359.
	 */
	public void setActivityTime(int startTime, int endTime) {
		if(startTime < 0 || endTime > 2359) throw new IllegalArgumentException();
		if(startTime > 2359 || endTime < 0) throw new IllegalArgumentException();
		if(endTime < startTime) throw new IllegalArgumentException();
		if((startTime % 100) >= 60 || (endTime % 100) >= 60 ) throw new IllegalArgumentException();
		if( (startTime / 100) >= 24 || (endTime / 100) >= 24) throw new IllegalArgumentException();
		this.startTime = startTime;
		this.endTime = endTime; 
	}

	/**
	 * Returns the Course start time.
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course end time.
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Returns the Course start time and end time in standard format. If meeting days
	 * are set to "A", then "Arranged" is returned. Otherwise, the meeting information 
	 * shows the meeting days followed by the start time in standard format, a dash, and
	 * the end time in standard time. Only "AM" and "PM" are used.
	 * @return the meeting days followed by the Course start time (standard format), a dash,
	 * and the Course end time (standard format).
	 */
	public String getMeetingString() {
		//using a stringbuilder for performance and ease of use.
		StringBuilder s1 = new StringBuilder("");
		if(this.meetingDays.equals("A")) return "Arranged";
		int startHour = getStartTime() / 100;
		int startMinute = getStartTime() % 100;
		int endHour = getEndTime() / 100;
		int endMinute = getEndTime() % 100;
		String ampm = "";
		//Build the startHour+startMinute+AMPM
		s1.append(this.getMeetingDays() + " ");
		if(startHour < 12){
			s1.append(startHour + ":");
			ampm = "AM";
		}
		else if(startHour == 12){
			s1.append(startHour + ":");
			ampm = "PM";
		}
		else{
			startHour -= 12;
			s1.append(startHour + ":");
			ampm = "PM";
		}
		
		s1.append(startMinute);
		//account for the int value of "00" being "0"
		if(startMinute == 0) s1.append("0");
		s1.append(ampm + "-");
		
		//Build the endHour+endMinute+AMPM
		if(endHour < 12){
			s1.append(endHour + ":");
			ampm = "AM";
		}
		else if(endHour == 12){
			s1.append(endHour + ":");
			ampm = "PM";
		}
		else{
			endHour -= 12;
			s1.append(endHour + ":");
			ampm = "PM";
		}
		s1.append(endMinute);
		if(endMinute == 0) s1.append("0");
		s1.append(ampm);
		return s1.toString();
	}

	/** 
	 * Generates a hashCode for Activity using all fields.
	 * @return hashCode value for Activity.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}