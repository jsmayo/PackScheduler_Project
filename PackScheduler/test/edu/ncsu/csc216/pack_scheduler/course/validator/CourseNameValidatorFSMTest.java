/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for CourseNameValidatorFSM class. 
 * 
 *@author Steven Mayo
 */
public class CourseNameValidatorFSMTest {

	CourseNameValidatorFSM cnv = new CourseNameValidatorFSM();
	
	@Test
	public void testIsValid() {
		//Test for non-alphanumerics case
		try {
			cnv.isValid("%%%%%@@#$@#$@$#$^");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		
		//Test to get to STATE_LLLDDD
		try {	
			assertTrue(cnv.isValid("CSC216"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test that STATE_D throws an exception
		try {
			cnv.isValid("3");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		//Test that STATE_L returns false
		try {
			assertFalse(cnv.isValid("C"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		//Test that STATE_LL returns false
		try {
			assertFalse(cnv.isValid("CC"));
		} catch (InvalidTransitionException e){
			fail();
		}
		
		//Test that STATE_LD returns false
		try {
			cnv.isValid("C1SC116");
		} catch (InvalidTransitionException e1) {
			assertEquals("Course name must have 3 digits.",  e1.getMessage());
		}
		
		//Test that STATE_LLL returns false
		try {
			assertFalse(cnv.isValid("CSC"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test that STATE_LLD returns false
		try {
			cnv.isValid("CS1C116");
		} catch (InvalidTransitionException e1) {
			assertEquals("Course name must have 3 digits.", e1.getMessage());
		}
		
		//Test for exception thrown on STATE_LLLLL
		try {
			cnv.isValid("CSCSC116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		//Test that STATE_LLLLD returns false;
		try {
			assertFalse(cnv.isValid("CSCC1"));
			
		} catch (InvalidTransitionException e) {
			fail();
			//assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		//Test that STATE_LLLD returns false
		try {
			assertFalse(cnv.isValid("CSC1"));
		} catch (InvalidTransitionException e){
			fail();
		}
			
		
		//Test that STATE_LLLDL throws an exception
		try {
			cnv.isValid("CSC1S16");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		//Test that STATE_LLLDD returns false
		try {
			assertFalse(cnv.isValid("CSC11"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		
		//Test that STATE_LLLDDL throws an exception
		try {
			cnv.isValid("CSC11D");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		//Test that STATE_LLLDDDD throws an exception
		try {
			cnv.isValid("CSC1116");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		//Test that STATE_LLLDDDL returns true
		try {
			assertTrue(cnv.isValid("CSC116C"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		//Test that STATE_LLLDDDLL throws an exception
		try {
			cnv.isValid("CSC116CS");
			fail();
		} catch (InvalidTransitionException e){
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		//Test that STATE_LLLDDDLD throws an exception
		try {
			cnv.isValid("CSC116C1");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		
		//Test that STATE_LLLDDDL returns true
		try {
			cnv.isValid("CSC116C");
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		//Test for invalid characters 
		try {
			cnv.isValid("CS@C");
			cnv.isValid("C#");
			cnv.isValid("CSC%");
			cnv.isValid("CSC1^");
			cnv.isValid("CSC11(");
			cnv.isValid("CSC116%");
			cnv.isValid("CSC116S$");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
	}

}
