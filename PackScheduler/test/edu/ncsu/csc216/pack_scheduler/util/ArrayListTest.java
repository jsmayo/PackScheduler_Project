package edu.ncsu.csc216.pack_scheduler.util;


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the ArrayList implementation.
 * 
 *@author Steven Mayo
 */
public class ArrayListTest {

	/**
	 * Test for the ArrayList constructor.
	 */
	@Test
	public void testArrayList() {
	ArrayList<String> list = new ArrayList<String>();
	assertEquals(0, list.size());
	//assertNull(list.get(0)); removed after .get was implemented to have OBE.
	assertEquals(10, list.capacity());
	}
	
	/**
	 * Test that the add() method functions correctly.
	 */
	@Test
	public void testAdd(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "Bleh");
		//test that the size increased by 1 
		assertEquals(1, list.size());
		//test that the capacity did not change
		assertEquals(10, list.capacity());
		//test that the object was added to the correct index
		assertEquals(list.get(0), "Bleh");
		
		//test adding to list less than capacity.
		list = new ArrayList<String>();
		list.add(0, "a");
		list.add(1, "b");
		list.add(2, "c");
		list.add(3, "d");
		list.add(4, "e");
		list.add(5, "f");
		list.add(6, "g");
		list.add(7, "h");
		list.add(8, "i");
		list.add(9, "j");
		//test that the capacity has not changed
		assertEquals(10, list.size());
		assertEquals(20, list.capacity());
		list.add(10, "lastplace");
		
		//test adding at zero index
		list.add(0, "z");
		//test that the size increased
		assertEquals(12, list.size());
		//test that the capacity did not double
		assertEquals(20, list.capacity());
		//test that the list correctly placed the values.
		String[] values = {"z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "lastplace"};
		for(int i = 0; i < values.length; i++) assertEquals(values[i], list.get(i));
		
		//test adding out of bounds
		try {
			list.add(20, "20");
			fail();
		} catch (IndexOutOfBoundsException e) {
			//test that size did not change
			assertEquals(12, list.size());
			//test that values did not change location
			for(int i = 0; i < values.length; i++) assertEquals(values[i], list.get(i));
		}
		
		//test for adding null 
		try {
			list.add(5, null);
			fail();
		} catch (NullPointerException e) {
			//test that size and location of values did not change
			assertEquals(12, list.size());
			for(int i = 0; i < values.length; i++) assertEquals(values[i], list.get(i));
		}
	
	}
	
	/**
	 * Test the remove() method functionality.
	 */
	@Test
	public void testRemove() {
		ArrayList<String> list = new ArrayList<>();
		list.add(0, "zero");
		list.add(1, "one");
		list.add(2, "two");
		list.add(3, "three");
		list.add(4, "four");
		assertEquals(5, list.size());
		//for(String s: list) System.out.println(s);
		list.remove(0);
		assertEquals(4, list.size());
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
		//for(String s: list) System.out.println(s);
		
		//test only one value not out of bounds
		list = new ArrayList<>();
		list.add(0, "zero");
		//System.out.println(list.get(0));
		assertEquals(1, list.size());
		list.remove(0);
		//System.out.println(list.get(0));
		assertEquals(0, list.size());
				
		//test removing out of bounds
		try {
			list.add(0, "0");
			assertEquals(1, list.size());
			list.remove(7);
			fail();
		} catch (IndexOutOfBoundsException e) {
			//test that size did not change
			assertEquals(1, list.size());
		}
	}
	
	/**
	 * Tests that the set() method functions correctly.
	 */
	@Test
	public void testSet() { 
		ArrayList<String> list = new ArrayList<>();
		
		//add an element
		list.add(0, "1");
		list.add(1, "2");
		//System.out.println(list.get(0));
		assertEquals(list.get(0), "1");
		//test for overwrite
		list.set(0, "0");
		list.set(1, "1");
		assertEquals(list.get(0), "0");
		assertEquals(list.get(1), "1");
		
		//test for correct return
		String s = list.set(1, "4");
		assertTrue(s.equals("1"));
		
		//test for duplicate
		list.add(2, "2");
		list.add(3, "3");
		try {
			list.set(2, "3");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("exception message", "Cannot add duplicate values", e.getMessage());
			
		}
		
	}
	
	/**
	 * Tests that the get method functions correctly.
	 */
	@Test
	public void testGet() {
		//test OBE
		ArrayList<String> list = new ArrayList<> ();
		list.add(0, "0");
		assertEquals(1, list.size());
		try {
			list.get(1);
			fail();
		} catch (IndexOutOfBoundsException e) { 
			assertEquals(1, list.size());
		}
	}
		
}
	