package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for the Activity class. More specifically, tests are 
 * designed to ensure the correct functionality of the @link {@link Activity#checkConflict(Activity) checkConflict()}
 * method.
 * @author Steven Mayo
 *
 */
public class ActivityTest {
	
	/**
	 * Test to ensure that activities with time conflicts cannot be scheduled.
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 100,  "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 100, "TH", 1330, 1445);
	    Activity e1 = new Course("CSC500", "All Things CSC", "601", 5, "veryname", 100, "MTW", 900, 1000);
	    Activity e2 = new Course("CSC501", "All Things CSC and More", "602", 3, "veryname", 100, "TH", 1100, 1200);
	   
	    //Test for no conflict.
	    try {
	        a1.checkConflict(a2); // this.checkConflict(param);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	
	   //Test for conflict
	  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a1.checkConflict(a2);
	        //check communitive property
	        a2.checkConflict(a1);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }

	    //Test for a single day of conflict
	    a1.setMeetingDays("H");
	    try{
	    	a1.checkConflict(a2);
	    	a2.checkConflict(a1);
	    	fail(); // should fail on Thursday 1445.
	    } catch (ConflictException ce) {
	    	assertEquals("H 2:45PM-3:30PM", a1.getMeetingString());
	    	assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	    
	    //Test Course and Event overlapping start times.
	    a1.setMeetingDays("MW");
	    a1.setActivityTime(900, 1200);
	    try{
	    	a1.checkConflict(e1);
	    	fail();
	    } catch (ConflictException ce) {
	    	assertEquals("Incorrect meeting string", "MW 9:00AM-12:00PM", a1.getMeetingString());
	    	assertEquals("Incorrect meeting string", "MTW 9:00AM-10:00AM", e1.getMeetingString());
	    }
	    
	    //Test event scheduled inside of course time
	    a2.setActivityTime(1330, 1445);
	    a2.setMeetingDays("MW");
	    e2.setActivityTime(1400, 1430);
	    e2.setMeetingDays("W");
	    try {
	    	a2.checkConflict(e2);
	    	e2.checkConflict(a2);
	    	fail();
	    } catch (ConflictException ce) {
	    	assertEquals("Invalid meeting string", "MW 1:30PM-2:45PM", a2.getMeetingString());
	    	assertEquals("Invalid meeting string", "W 2:00PM-2:30PM", e2.getMeetingString());
	    }
	    
	    //Test 2 Course Events
	    a1.setMeetingDays("MW");
	    a2.setMeetingDays("W");
	    a1.setActivityTime(1330, 1445);
	    a2.setActivityTime(1400, 1430);
	    try {
	    	a1.checkConflict(a2);
	    	a2.checkConflict(a1);
	    	fail();
	    } catch (ConflictException ce){
	    	assertEquals("Schedule conflict.", ce.getMessage());
	    }
	
	
	
	}
	

}
