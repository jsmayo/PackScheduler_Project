package edu.ncsu.csc216.pack_scheduler.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Allows either a currently enrolled student, who is stored within the 
 * university's student directory, or the registrar to attempt to login 
 * and access the privileges assigned to them. 
 * 
 *@author StevenMayo
 */
public class RegistrationManager {

	/**Instance for Registration Manager */
	private static RegistrationManager instance;
	/** Course catelog of all courses */
	private CourseCatalog courseCatalog;
	/** Directory of students */
	private StudentDirectory studentDirectory;
	/** User profile for Registrar */
	private User registrar;
	/** User profile for Current User */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Hard coded pw for registrar profile */
	private static final String PW = "Regi5tr@r";
	/**String field for storing hased password */
	private static String hashPW;
	/** Directory of Faculty */
	private FacultyDirectory facultyDirectory;
	

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
		currentUser = null;
		facultyDirectory = new FacultyDirectory();
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
	 * Returns the FacultyDirectory instance assigned to facultyDirectory
	 * @return facultyDirectory The faculty directory.
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
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
		if(currentUser != null) return false;
		User user = null;
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
				else {
					currentUser = null;
					return false;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}

		}

		if(studentDirectory.getStudentById(id) == null && facultyDirectory.getFacultyById(id) == null) throw new IllegalArgumentException("User doesn't exist.");
		if(studentDirectory.getStudentById(id) == null) user = facultyDirectory.getFacultyById(id);
		else user = studentDirectory.getStudentById(id);
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
			if (user.getPassword().equals(localHashPW)) {
				currentUser = user;
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException();
		}
		currentUser = null;
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

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student)currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student)currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false; 
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student)currentUser;
			Schedule schedule = s.getSchedule();
			String [][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			//do nothing 
		}
	}
}