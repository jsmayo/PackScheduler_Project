package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Allows either a currently enrolled student, who is stored within the 
 * university's student directory, or the registrar to attempt to login 
 * and access the privileges assigned to them. 
 * 
 *@author StevenMayo
 */
public class RegistrationManager {
	
	private static RegistrationManager instance;
	  private CourseCatalog courseCatalog;
	private StudentDirectory studentDirectory;
	  private User registrar;
	   private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	   private static final String PW = "Regi5tr@r";
	  private static String hashPW;
	
	//Static code block for hashing the registrar user's password
	{
		try {
			  MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			  digest1.update(PW.getBytes());
			 hashPW = new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Private constructor for the RegistrationManger class. When called, the
	 * RegistrationManager class instantiates the courseCatalog, StudentDirectory,
	 * and registrar classes.
	 */
	private RegistrationManager() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		registrar = new Registrar();
	}
	
	/**
	 * Attempts to get the current instance of the RegistrationManager object, which
	 * if is not active, will then create a new instance. 
	 * @return instance The current RegistrationManager instance.
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Returns the CourseCatalog instance assigned to courseCatalog.
	 * @return courseCatalog The course catalog.
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Returns the StudentDirectory instance assigned to studentDirectory. 
	 * @return studentDirectory The student directory. 
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Attempts to login the current user by comparing the passed in ID and password
	 * arguments against those stored within the StudentDirectory object and within the
	 * Registrar object. If a student or registrar ID is provided, then the password must match for 
	 * the login to be successful. If not, then the login will fail. Also, more than 1 login
	 * at a time is not allowed.
	 * @param id The ID string of the user.
	 * @param password The password for the id specified by the user.
	 * @return true if the provided password matches the provided id. 
	 */
	public boolean login(String id, String password) {
		if(currentUser != null) throw new IllegalArgumentException("Cannot login again");
		Student s = null;
		if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				}
				else return false;
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
			
		}
		
		if(studentDirectory.getStudentById(id) == null) throw new IllegalArgumentException();
		s = studentDirectory.getStudentById(id);
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}

		return false;
	}
	
	/**
	 * Logs the currentUser out of the RegistrationManager by setting the field value to null.
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * Returns the value assigned as the currentUser.
	 * @return currentUser The current user who is logged into the RegistrationManager
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Clears the courseCatalog and studentDirectory fields by 
	 * calling the newCourseCatalog() and newStudentDirectory() methods.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}
	
	/**
	 * Creates a private instance of the Registrar class with hard-coded
	 * values for the creation and login values.
	 * 
	 *@author Steven Mayo
	 */
	private static class Registrar extends User {
		
		  private static final String FIRST_NAME = "Wolf";
		  private static final String LAST_NAME = "Scheduler";
		 private static final String ID = "registrar";
		 	private static final String EMAIL = "registrar@ncsu.edu";
		
		/**
		 * Create a registrar user with the user id of registrar and
		 * password of Regi5tr@r.  Note that hard coding passwords in a 
		 * project is HORRIBLY INSECURE, but it simplifies testing here.
		 * This should NEVER be done in practice!
		 */
		public Registrar() {
			super(FIRST_NAME, LAST_NAME, ID, EMAIL, hashPW);
		}
	}
}