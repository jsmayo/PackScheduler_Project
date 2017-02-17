package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

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
	
	private RegistrationManager() {
	}
	
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	public boolean login(String id, String password) {
		Student s = studentDirectory.getStudentById(id);
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
			} catch (NoSuchAlgorithmException e) {
	throw new IllegalArgumentException();
			}
		}
			
				return false;
	}

	public void logout() {
		currentUser = registrar; 
	}
	
	/**
	 * @return 
	 */
	public User getCurrentUser() {
		//TODO implement method
		return null;
	}
	
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}
	
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