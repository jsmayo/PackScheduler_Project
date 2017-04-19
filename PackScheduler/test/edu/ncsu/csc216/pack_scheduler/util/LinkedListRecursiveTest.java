package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author Steven Mayo
 */
public class LinkedListRecursiveTest {
	
	private LinkedListRecursive list;

	@Test
	public void testLinkedListRecursive() {
		list = new LinkedListRecursive();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testAdd() {
		list = new LinkedListRecursive();
		assertTrue(list.add("0"));
		assertEquals(1, list.size());
		assertTrue(list.add("1"));
		assertEquals(2, list.size());
	}
	
	@Test
	public void testGet() {
		list = new LinkedListRecursive();
		assertTrue(list.add("0"));
		assertEquals(1, list.size());
		assertTrue(list.add("1"));
		assertEquals(2, list.size());
		assertTrue("0".equals(list.get(0)));
		assertTrue("1".equals(list.get(1)));
		assertTrue(list.add("2"));
		assertTrue("2".equals(list.get(2)));
		
	
		
		try {
			list.add("2");
			fail("should not add duplicates");
		} catch (IllegalArgumentException e) {
			assertEquals(3, list.size());
		}
		
	}
	
	
	@Test
	public void testAddIntString() {
		list = new LinkedListRecursive();

	}
	
	

}
