package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Custom Exception class used to alert the user of a scheduling conflict.
 * 
 * @author Steven Mayo
 */
public class ConflictException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the ConflictException class. A String parameter is
	 * accepted and thrown to the parent Class (Exception).
	 * @param ce The exception message to be passed to the Exception class.
	 */
	public ConflictException(String ce){
		super(ce);
	}
	
	/**
	 * Parameterless constructor class for the ConflictException class. When
	 * this constructor is called, a string is passed to the ConflictException(String)
	 * constructor. The String Object is a message stating "Schedule conflict."
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
