package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;


/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * 
 * @author Steven Mayo
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */

	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(fileName));
		SortedList<Course> courses = new SortedList<Course>();
		while (fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < courses.size(); i++) {
					Course c = courses.get(i);
					if (course.getName().equals(c.getName()) &&
							course.getSection().equals(c.getSection())) {
						//it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					courses.add(course);
				}
			} catch (IllegalArgumentException e) {
				//skip the line
			}
		}
		fileReader.close();
		return courses;
	}
    
	/**
	 * A private helper method that takes a String argument containing course information,
	 * parses, and returns it as a Course Object.
	 * @param nextLine string containing course data.
	 * @return Course Course object representing the parsed input String.
	 * @throws IllegalArgumentException thrown if course input is invalid.
	 */
	private static Course readCourse(String nextLine) throws IllegalArgumentException {
		try (Scanner scanner = new Scanner(nextLine).useDelimiter(",")) {
			String cname = scanner.next();
			String ctitle = scanner.next();
			String csection = scanner.next();
			int ccredits = Integer.parseInt(scanner.next());
			String cid = scanner.next();
			String nullId = null;
			int cenrollmentCap = Integer.parseInt(scanner.next());
			String cmeetingDays = scanner.next();
			if(cmeetingDays.equals("A") && scanner.hasNext()) throw new IllegalArgumentException("Invalid Argument");
			if(scanner.hasNext()){
				int cstartTime = Integer.parseInt(scanner.next());
				int cendTime = Integer.parseInt(scanner.next());
				Course course = new Course(cname, ctitle, csection, ccredits, nullId, cenrollmentCap, cmeetingDays, cstartTime, cendTime); //create the course
				if(RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(cid) != null){
					course.setInstructorId(cid); //check to see if faculty id exists 
					RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(cid).getSchedule().addCourseToSchedule(course); //set the professor id
				}
				return course;
			}
			else {
				Course course = new Course(cname, ctitle, csection, ccredits, null, cenrollmentCap, cmeetingDays);
				if(RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(cid) != null){
					course.setInstructorId(cid); //check to see if faculty id exists 
					RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(cid).getSchedule().addCourseToSchedule(course); //set the professor id
				}
					return course;
			}
		} catch (NoSuchElementException e){
			throw new IllegalArgumentException("Invalid argument");
		}
	} 

	/**
     * Writes the given list of Courses to a specified fileName
     * @param fileName Name of the file to write/save to.
     * @param courses A SortedList containing course objects.
     * @throws IOException if the specified file cannot be written/saved to.
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
       PrintStream fileWriter = new PrintStream(new File(fileName));
       
       for(int i = 0; i < courses.size(); i++){
    	   fileWriter.println(courses.get(i).toString());
       }
       
       fileWriter.close();
        
    }

}