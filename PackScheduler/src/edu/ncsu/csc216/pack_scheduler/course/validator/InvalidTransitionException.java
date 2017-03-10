package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Custom checked exception class that throws a checked
 * exception when the user attempts to perform an invalid
 * state transition.
 * 
 *@author Steven Mayo
 */
public class InvalidTransitionException extends Exception {

	/** Default serialVersionUID variable declared for Exception. */
	private static final long serialVersionUID = 1L;

	/**
	 * Parameterless constructor that provides a default message of "Invalid
	 * FSM Transition." 
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
	
	/**
	 * Constructor that accepts a string argument that is either
	 * specified or left as the default message. 
	 * @param s String specified as the exception message.
	 */
	public InvalidTransitionException(String s) {
		super(s);
	}
}
