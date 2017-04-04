package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class LinkedQueueTest {

	LinkedQueue<String> queue;
	
	@Test
	public void testLinkedQueue() {

		queue = new LinkedQueue<>(20);
		assertEquals("size should be 0", 0, queue.size());
		queue.enqueue("first queue");
		assertEquals("size should be 1",1, queue.size());
		assertTrue("first queue".equals(queue.dequeue()));
		assertEquals("size should be 0", 0, queue.size());
		queue.enqueue("one");
		queue.enqueue("two");
		queue.enqueue("three");

		try {
			queue.setCapacity(1);
			fail("Should not be able to set capacity below the current size");
		} catch (IllegalArgumentException e) {
			assertTrue(queue.size() == 3);
		}
		assertTrue("one".equals(queue.dequeue()));
		assertTrue("two".equals(queue.dequeue()));
		assertTrue("three".equals(queue.dequeue()));

		try {
			queue.dequeue();
			fail("should not be able to dequeue an empty queue");
		} catch (NoSuchElementException e) {
			assertTrue(0 == queue.size());
		}
	}
}
