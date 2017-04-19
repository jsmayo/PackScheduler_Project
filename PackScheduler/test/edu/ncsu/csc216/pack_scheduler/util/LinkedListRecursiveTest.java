package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *Tests for correct functionality of the LinkedListRecursive class.
 *
 * @author Steven Mayo
 */
public class LinkedListRecursiveTest {
	
	private LinkedListRecursive<String> list;

	/**
	 * Tests for LinkedListRecursive constructor
	 */
	@Test
	public void testLinkedListRecursive() {
		list = new LinkedListRecursive<>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Test add() method of LinkedListRecursive class
	 */
	@Test
	public void testAdd() {
		list = new LinkedListRecursive<>();
		assertTrue(list.add("0"));
		assertEquals(1, list.size());
		assertTrue(list.add("1"));
		assertEquals(2, list.size());
	}
	
	/**
	 * Test get() method for LinkedListRecursive Class 
	 */
	@Test
	public void testGet() {
		list = new LinkedListRecursive<>();
		list.add("0");
		assertEquals("0", list.get(0));
		list.add("1");
		assertEquals("1", list.get(1));
		list.add("2");
		assertEquals("2", list.get(2));
		try {
			list.get(5);
			fail("iob");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		try {
			list.get(-1);
			fail("iob");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntE() {
		list = new LinkedListRecursive<>();
		list.add(0, "front");
		assertEquals(1, list.size());
		assertEquals("front", list.get(0));
		list.add(1, "1");
		assertEquals("1", list.get(1));
		
		try {
			list.add(5, "5");
			fail("iob");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}
		
		try {
			list.add(-5, "-5");
			fail("iob");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, list.size());
		}
		
		try {
			list.add(0, "1");
			fail("duplicate found");
		} catch (IllegalArgumentException e) {
			assertEquals(2, list.size());
		}
		
		try {
			list.add(0, null);
			fail("null found");
		} catch (NullPointerException e) {
			assertEquals(2, list.size());
		}
			
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveE() {
		list = new LinkedListRecursive<>();
		list.add(0, "front");
		assertEquals(1, list.size());
		assertEquals("front", list.get(0));
		list.add(1, "1");
		assertEquals("1", list.get(1));
		
		try {
			list.remove(null);
			fail("cannot remove null");
		} catch (NullPointerException e) {
			assertEquals(2, list.size());
		}
		
		assertTrue(list.remove("front"));
		assertEquals(1, list.size());
		assertTrue(list.remove("1"));
		assertEquals(0, list.size());
		list.add("0");
		assertTrue(list.remove("0"));
		list.add("0");
		list.add("1");
		list.add("2");
		assertTrue(list.remove("1"));
		assertEquals(2, list.size());
		assertEquals("2", list.get(1));
	
		assertFalse(list.remove("3"));
			
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		list = new LinkedListRecursive<>();
		list.add(0, "front");
		assertEquals(1, list.size());
		assertEquals("front", list.get(0));
		list.add(1, "1");
		assertEquals("1", list.get(1));
		
		try {
			list.remove(null);
			fail("cannot remove null");
		} catch (NullPointerException e) {
			assertEquals(2, list.size());
		}
		
		assertTrue("front".equals(list.remove(0)));
		assertEquals(1, list.size());
		assertTrue("1".equals(list.remove(0)));
		assertEquals(0, list.size());
	
		try {
			list.remove(1);
			fail("iob");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#set(int, java.lang.Object)}.
	 */
	@Test
	public void testSet() {
		list = new LinkedListRecursive<>();
		list.add(0, "front");
		assertEquals(1, list.size());
		assertEquals("front", list.get(0));
		list.add(1, "1");
		assertEquals("1", list.get(1));
		assertEquals("front", list.set(0, "0"));
		assertEquals(2, list.size());
		
		try {
			list.set(0, null);
			fail("cannot remove null");
		} catch (NullPointerException e) {
			assertEquals(2, list.size());
		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		list = new LinkedListRecursive<>();
		assertFalse(list.contains("bleh"));
		list.add(0, "front");
		assertEquals(1, list.size());
		assertEquals("front", list.get(0));
		list.add(1, "1");
		assertEquals("1", list.get(1));
		assertEquals("front", list.set(0, "0"));
		assertEquals(2, list.size());
		assertTrue(list.contains("1"));
		assertTrue(list.contains("0"));
	}

}
