package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for the CSC216Collections library
 * 
 * @author Steven Mayo
 */
public class SortedListTest {

	/**
	 * Testing the constructor for appropriate growth of the list.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.contains("apple"));
		
		list.add("apple");
		list.add("color");
		list.add("dog");
		list.add("energy");
		list.add("fruit");
		list.add("green");
		list.add("hello");
		list.add("intelligence");
		list.add("jenkins");
		list.add("kicking");
		list.add("love");
		list.add("movie");
		assertEquals(list.size(), 12);
	}

	/**
	 * Test that the constructed list is generated correctly. Methods are 
	 * implemented to test adding Objects to the front section, middle section, 
	 * and end section of the list. Checks for maintaining Object ordering are also
	 * implemented.
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		//Planned ordering: acting, balance, banana, calamity, camera, cold, lonely

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		//Test adding to the front of the list
		list.add("balance");
		list.add("acting");
		//Test for correct size of list.
		assertEquals(list.size(), 3);
		//Test for correct ordering.
		assertEquals("acting", list.get(0));
		assertEquals("balance", list.get(1));
		assertEquals("banana", list.get(2));

		//Test adding to the end of the list
		list.add("cold");
		list.add("lonely");
		assertEquals(list.size(), 5);
		assertEquals(list.get(4), "lonely");
		assertEquals(list.get(3), "cold");
		assertEquals(list.get(0), "acting");

		//Test adding to the middle of the list.
		list.add("calamity");
		list.add("camera");
		assertEquals(list.size(), 7);
		assertEquals(list.get(3), "calamity");
		assertEquals(list.get(4), "camera");

		//Test ordering again:
		assertEquals(list.get(0), "acting");
		assertEquals(list.get(1), "balance");
		assertEquals(list.get(2), "banana");
		assertEquals(list.get(3), "calamity");
		assertEquals(list.get(4), "camera");
		assertEquals(list.get(5), "cold");
		assertEquals(list.get(6), "lonely");

		//Test adding a null element
		try {
			list.add(null);
			fail(); //should not add
		} catch (NullPointerException npe) {
			//check ordering hasn't changed.
			assertEquals(list.size(), 7);
			assertEquals(list.get(0), "acting");
			assertEquals(list.get(1), "balance");
			assertEquals(list.get(2), "banana");
			assertEquals(list.get(3), "calamity");
			assertEquals(list.get(4), "camera");
			assertEquals(list.get(5), "cold");
			assertEquals(list.get(6), "lonely");
		}

		//Test adding a duplicate element
		try {
			list.add("acting");
			fail();
		} catch(IllegalArgumentException iae){
			assertEquals(list.size(), 7);
			assertEquals(list.get(0), "acting");
			assertEquals(list.get(1), "balance");
			assertEquals(list.get(2), "banana");
			assertEquals(list.get(3), "calamity");
			assertEquals(list.get(4), "camera");
			assertEquals(list.get(5), "cold");
			assertEquals(list.get(6), "lonely");
		}
	}
	
	/**
	 * Test the boundary and invalid conditions for calling the get() method.
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		try {
			System.out.println(list.get(0));
			fail(); //list should be empty
		} catch (IndexOutOfBoundsException ibe){
			assertEquals(list.size(), 0);
		}
		
		
		//Add some elements to the list
		list.add("chill");
		list.add("music");
		list.add("playing");
		assertEquals(list.size(), 3); //already tested main functionality of get
		
		//Test getting an element at an index < 0
		try {
			System.out.println(list.get(-1) + "");
			fail();
		} catch (IndexOutOfBoundsException ibe) {
			assertEquals(list.size(), 3);
			assertEquals(list.get(0), "chill");
			assertEquals(list.get(2), "playing");
		}
		
		
		//Test getting an element at size
		try {
			System.out.println("" + list.get(list.size()));
			fail();
		} catch (IndexOutOfBoundsException ibe){
			assertEquals(list.size(), 3);
			assertEquals(list.get(0), "chill");
			assertEquals(list.get(2), "playing");
		}
	}
	
	/**
	 * Tests the remove method for the SortedList class. 
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException ibe) {
			assertEquals(list.size(), 0);
		}
		
		//Add some elements to the list
		list.add("owl");
		list.add("city");
		list.add("absolute");
		list.add("favorite");
		
		
		//Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException ibe){
			assertEquals(list.size(), 4);
			assertEquals(list.get(2), "favorite");
			assertEquals(list.get(3), "owl");
			assertEquals(list.get(0), "absolute");
			assertEquals(list.get(1), "city");
		}
		
		//Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException ibe){
			assertEquals(list.size(), 4);
			assertEquals(list.get(2), "favorite");
			assertEquals(list.get(3), "owl");
			assertEquals(list.get(0), "absolute");
			assertEquals(list.get(1), "city");
		}
		
		//Test removing a middle element
		try {
			list.remove(2);
			assertEquals(list.size(), 3);
			assertEquals(list.get(2), "owl");
			assertEquals(list.get(0), "absolute");
			assertEquals(list.get(1), "city");
		} catch (IndexOutOfBoundsException ibe){
			fail();
		}
		
		//Test removing the last element
		try {
			list.remove(list.size() - 1);
			assertEquals(list.size(), 2);
			assertEquals(list.get(0), "absolute");
			assertEquals(list.get(1), "city");
		} catch (IndexOutOfBoundsException ibe){
			fail();
		}
		
		//Test removing the first element
		try {
			list.remove(0);
			assertEquals(list.size(), 1);
			assertEquals(list.get(0), "city");
		} catch (IndexOutOfBoundsException ibe){
			fail();
		}
		
		try {
			list.remove(0);
			assertEquals(list.size(), 0);
		} catch (IndexOutOfBoundsException ibe){
			fail();
		}
	}
	
	/**
	 * Tests for the indexOf method of the SortedList class.
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		try {
			assertEquals(list.indexOf("hello"), -1);
		} catch (NullPointerException npe) {
			fail();
		} catch (IndexOutOfBoundsException ibe) {
			fail();
		}
		
		//Add some elements
		list.add("always"); // 0
		list.add("never"); //2
		list.add("sometimes"); //3
		list.add("maybe"); //1
				
		//Test various indexOf calls
		assertEquals(list.indexOf("maybe"), 1);
		assertEquals(list.indexOf("not always"), -1);
		assertEquals(list.indexOf("sometimes"), 3);
		assertEquals(list.indexOf("always"), 0);
		assertEquals(list.indexOf("but perhaps"), -1);
		assertEquals(list.indexOf("never"), 2);
		int answers = list.indexOf("always") + list.indexOf("never") + 
				list.indexOf("sometimes") + 2 * list.indexOf("Owl City");
		assertEquals(answers, 3);
		
		
		//Test checking the index of null
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException npe) {
			assertEquals(list.size(), 4);
			assertEquals(list.get(0), "always");
			assertEquals(list.get(3), "sometimes");
			assertEquals(list.get(2), "never");
			assertEquals(list.get(1), "maybe");
		}
		
	}
	
	/**
	 * Test the clear method for SortedList. Elements are added, the 
	 * list is cleared via the method call, and the results are checked.
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		list.add("please");
		list.add("be");
		list.add("a good grade");
		
		//Test the clear method
		try {
			list.clear();
			//Test to ensure the list is empty
			assertEquals(list.size(), 0);
			assertEquals(list.indexOf("please"), -1);
		} catch (Exception e){
			fail(); //should clear with no exceptions thrown.
		}
	}

	/**
	 * Test for the isEmpty method in SortedList. Testing that
	 * an empty list returns true and false otherwise.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that a new list returns true
		assertTrue(list.isEmpty());
		assertFalse(!(list.isEmpty()));
		
		//Test that added elements return false for isEmpty()
		list.add("!?!?!?!??!?!?");
		assertFalse(list.isEmpty());
		assertTrue(!(list.isEmpty()));
		assertEquals(list.get(0), "!?!?!?!??!?!?");
		assertEquals(list.size(), 1);
	}
	
	/**
	 * Test for the contains() method of SortedList. True should 
	 * be returned if the list contains the specified element.
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("Owl City"));
		assertTrue(!(list.contains("My favorite artist")));
				
		//Add some elements
		list.add("hello");
		list.add("goodbye");
		list.add("konnichiwa");
		list.add("sayonara");
		assertEquals(list.size(), 4);
		assertEquals(list.get(list.size() - 1), "sayonara");
		
		//Test for true and false cases
		assertTrue(list.contains("hello"));
		assertFalse(list.contains("sad"));
		assertTrue(list.contains("goodbye"));
		assertTrue(list.contains("konnichiwa"));
		assertFalse(list.isEmpty());
		assertTrue(list.contains("sayonara"));
	}
	
	/**
	 * Test the equals() implementation of SortedList. Two lists should
	 * return true if and only if the specified object is also a list,
	 * both lists have the same size, and all corresponding paris of elements
	 * in the two lists are equal.
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("abc");
		list1.add("def");
		list2.add("abc");
		list2.add("def");
		list3.add("fadlskjf;lsdjflkdj");
		
		//Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertFalse(list3.equals(list1));
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
	}
	
	/**
	 * Test for the hashCode() implementation of SortedList. It should
	 * perform exactly the same as the documentation for the List.hashCode()
	 * method specifies.
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("ROAR"); list1.add("MOO");
		list3.add("ROAR"); list3.add("MOO");
		list2.add("rawr");
		
				
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(),list3.hashCode());
		assertNotEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());	
	}

}
 