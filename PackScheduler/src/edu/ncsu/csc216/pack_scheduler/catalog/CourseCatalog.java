package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;
import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Creates a Course Catalog for the PackScheduler application. There is 
 * functionality to add Courses, both in batch and manually, as well as remove
 * Courses, modify the Course listings, and export the catalog to a file.
 * 
 * @author Steven Mayo
 */
public class CourseCatalog {
	/** List of students in the directory */
	private SortedList<Course> courseCatalog;
	
	/**
	 * Empty constructor for the CourseCatalog class.
	 */
	public CourseCatalog(){
		newCourseCatalog();
	}
	
	/**
	 * Creates a new SortedList container for the course catalog. 
	 */
	public void newCourseCatalog(){
		courseCatalog = new SortedList<>();
	}
	
	/**
	 * Constructs the course catalog by reading in course information
	 * from the given filename.  Throws an IllegalArgumentException if the 
	 * file cannot be found.
	 * @param fileName file containing list of students
	 * @throws IllegalArgumentException if the file cannot be read from. 
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			courseCatalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Attempts to add a new Course to the course catalog. If there is an error
	 * creating the Course, then the error is allowed to propagate to the client.
	 * The course will only be added if it has valid constructing parameters and is
	 * not already in the catalog.
	 * @param name The Course name
	 * @param title The Course title
	 * @param section The Course section 
	 * @param credits The Course credit hours
	 * @param prof The instructor's ID
	 * @param enrollmentCap Enrollment cap of the course.
	 * @param meetdays The meeting days of the Course
	 * @param start The time in standard military that the Course is set to begin.
	 * @param end The time in standard military that the Course is set to end.
	 * @return true if the Course can be added to the course catalog.
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String prof, int enrollmentCap, String meetdays, int start, int end){
		//attempt to create a Course using the parameters passed in.
		//Errors are allowed to propogate to the client.
		Course courseToAdd = new Course(name, title, section, credits, prof, enrollmentCap, meetdays, start, end);
		if(getCourseFromCatalog(name, section) != null) return false;
		
		if(courseCatalog.indexOf(courseToAdd) == -1){
			//if an index is not found, then add the course and return true.
			courseCatalog.add(courseToAdd);
			return true;
		}
		return false;
	}
	
	/**
	 * Attempts to remove a course from the Course Catalog, if the 
	 * name and section parameters match a Course listed in the catalog.
	 * @param name Course name.
	 * @param section Course section.
	 * @return true if the Course was removed from the CourseCatalog.
	 */
	public boolean removeCourseFromCatalog(String name, String section){
		//create a course using getCourseFromCatalog method
		Course c = getCourseFromCatalog(name, section);
		if(c == null) return false;
		courseCatalog.remove(courseCatalog.indexOf(c));
		return true;
	}
	
	/** 
	 * Searches through the course catalog and attempts to retrieve a course that matches the name and section
	 * parameters that were passed in. If one is not found, then Null will be returned.
	 * @param name name of the course to find
	 * @param section section of the course to find.
	 * @return Course Course within the course catalog that matches the name and section parameters that were specified
	 * or a value of null if one is not found.
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < courseCatalog.size(); i++){
			if(courseCatalog.get(i).getName().equals(name) && courseCatalog.get(i).getSection().equals(section))
				return courseCatalog.get(i); //This should return the course that was matched.
		}
		return null; //Should return null if no course is matched.
	}
	
	/**
	 * Constructs a 2D array containing the course catalog information. There is a row for
	 * each course within the catalog and 3 columns for: the course name, the course section,
	 * the course title, and available seats left for enrollment. 
	 * @return courseCatalog2d String[][] object containing the name, section, and title for 
	 * all courses within the course catalog.
	 */
	public String[][] getCourseCatalog() {
		String[][] course2d = new String[courseCatalog.size()][5];
		if(courseCatalog.size() == 0) return course2d;
		for(int i = 0; i < courseCatalog.size(); i++){
			course2d[i][0] = courseCatalog.get(i).getName();
			course2d[i][1] = courseCatalog.get(i).getSection();
			course2d[i][2] = courseCatalog.get(i).getTitle();
			course2d[i][3] = courseCatalog.get(i).getMeetingString();
			course2d[i][4] = courseCatalog.get(i).getShortDisplayArray()[4];
		}
		return course2d;
	}
	
	/**
	 * Exports the current course catalog using the filename that's passed in as a parameter.
	 * @param fileName the filename to write the course catalog to.
	 * @throws IllegalArgumentException if an IOException is encountered while attempting to
	 * write to/save the file.
	 */
	public void saveCourseCatalog(String fileName){
		try {
			CourseRecordIO.writeCourseRecords(fileName, courseCatalog);
		} catch(IOException ioe){
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
	
}