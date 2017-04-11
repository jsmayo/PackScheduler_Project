package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the LinkedStack class.
 * 
 * @author Steven Mayo
 *
 */
public class LinkedStackTest {

	LinkedStack<String> stack;
	
	/**
	 * Tests for LinkedStack constructor.
	 */
	@Test
	public void testLinkedStack() {
		stack = new LinkedStack<String>(20);
		assertEquals(0, stack.size());
		stack.push("first push");
		assertEquals(1, stack.size());
		stack.pop();
		assertEquals(0, stack.size());
		stack.push("one");
		stack.push("two");
		stack.push("three");
		stack.push("four");
		assertEquals(4, stack.size());
		assertTrue("four".equals(stack.pop()));
		
		try {
			stack.setCapacity(2);
			fail("should not be able to set below " + stack.size());
		} catch (IllegalArgumentException e) {
			assertTrue(3 == stack.size());
		}
	}

}


