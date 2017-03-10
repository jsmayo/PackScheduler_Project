package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;
import org.junit.Test;


/** Test class for InvalidTransitionException class. 
 * 
 *@author Steven Mayo
 */
public class InvalidTransitionExceptionTest {
	
	/**
	 * Test parameterless constructor for the default message being thrown.
	 */
	@Test
	public void testInvalidTransitionException() {
	    InvalidTransitionException ite = new InvalidTransitionException();
	    assertEquals("Invalid FSM Transition.", ite.getMessage());
	}
	
	
	
	/**
	 * Test for string accepting constructor.
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		//Test for string parameter version of the exception.
		InvalidTransitionException ite = new InvalidTransitionException("Invalid FSM Transition");
		assertEquals("Invalid FSM Transition", ite.getMessage());
		
		//Test for custom string parameter
		InvalidTransitionException ite2 = new InvalidTransitionException("NOOOOOPE");
		assertEquals("NOOOOOPE", ite2.getMessage());
	}
	
	

}
