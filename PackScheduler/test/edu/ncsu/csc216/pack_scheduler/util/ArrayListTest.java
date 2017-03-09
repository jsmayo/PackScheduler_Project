package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void testArrayList() {
	ArrayList<String> list = new ArrayList<String>();
	assertEquals(0, list.size());
	assertNull(list.get(0));
	assertEquals(10, list.capacity());
	}
	
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
		
		
	}

}
