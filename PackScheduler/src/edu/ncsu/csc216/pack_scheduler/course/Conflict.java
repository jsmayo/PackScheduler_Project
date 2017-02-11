package edu.ncsu.csc216.pack_scheduler.course;

/**
 * The Conflict interface is implemented in the Activity class, which is 
 * used to check for potential scheduling conflicts and alert the user if
 * one is found.
 * 
 * @author Steven Mayo
 */
public interface Conflict {
	
	/**
	 * Checks for potential scheduling conflicts. An activity is passed in
	 * as a parameter to be compared to the implementing class. If a conflict
	 * is found, then a ConflictException error is thrown.
	 * @param possibleConflictingActivity An activity to compare against the implementing
	 * class for an potential scheduling conflicts.
	 * @throws ConflictException A custom Exception Class that alerts the user of a 
	 * scheduling conflict. This exception should be thrown when minutes of
	 * compared Activity Objects overlap. See {@link Activity#checkConflict(Activity) checkConflict}.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
