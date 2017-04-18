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
		list.add("2");
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
		assertTrue(list.add("0"));    ////0
		assertEquals(1, list.size());
		assertTrue(list.add("1"));   ///1
		assertEquals(2, list.size());
		
		//add to end
		list.add( "2");    ///2
		assertEquals(3, list.size());
		assertEquals(list.get(2), "2");
		//System.out.println(list.get(0));
		//System.out.println(list.get(1));
		//System.out.println(list.get(2));
		
		
		
		//add to front
		list.add(0, "front");       ///front
		assertEquals(4, list.size()); //size is 4, index is 3
		assertEquals(list.get(0), "front");
		for(int i = 0; i < list.size(); i++) System.out.println(list.get(i));

		
		//add to end
		list.add(4, "end");
		assertEquals(5, list.size());
		System.out.println(list.get(5));
		
		//add to middle
		list.add(2, "middle");
		assertEquals(6, list.size());
		//assertTrue(list.get(2).equals("middle"));
		for(int i = 0; i < list.size(); i++) System.out.println(list.get(i));
	}
	
	

}
