package edu.ncsu.csc216.pack_scheduler.directory;


import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Maintains a directory of all faculty at NC State.
 * 
 * @author Steven Mayo
 */
public class FacultyDirectory {

	/** List of faculty in the directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Creates an empty faculty directory.
	 */
	public FacultyDirectory() {
	}
	
	/**
	 * Creates an empty faculty directory.  All faculty in the previous
	 * list are lost unless saved by the user.
	 */
}
