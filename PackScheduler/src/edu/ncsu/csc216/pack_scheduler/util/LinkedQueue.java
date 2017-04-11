package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Queue class that implements behaivor representing that of a 
 * LinkedList. This class is used to handle the waitlist of Students
 * attempting to register for a Course. 
 * 
 * @author Steven Mayo
 *
 * @param <E> Generic element of Type E.
 */
public class LinkedQueue<E> implements Queue<E> {

	/**LinkedAbstractList to construct the queue. */
	private LinkedAbstractList<E> queue;
	/**Size of the queue initially. */
	private int size = 0;
	/**Capacity of the queue. */
	private int capacity;
	
	/**
	 * Constructor for the LinkedQueue class that accepts an argument for
	 * the capacity of the Queue.
	 * @param capacity Capacity of the queue.
	 */
	public LinkedQueue(int capacity) {
		queue = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	
	/**
	 * Queues an element to the back of the current wait list.
	 * @throws IllegalArgumentException if the wait list is currently at capacity
	 * when attempting to add another element.
	 * @param element Element to add to the queue.
	 */
	@Override
	public void enqueue(E element) {
		//LinkedAbstractList should delegate if capacity == size, but put code in to remove checkstyle warning.
		if(this.size() == this.capacity) throw new IllegalArgumentException();
		if(isEmpty()) queue.add(0, element); //add to front if empty
		else queue.add(size(), element); //add to last position if not empty
		size++;
		
	}

	/**
	 * Removes an element from the front of the current queue.
	 * @throws NoSuchElementException if the current queue does not
	 * have any elements when attempting to remove an element.
	 * @return Element removed from the queue. 
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) throw new NoSuchElementException();
		this.size--;
		return queue.remove(0);
	}

	/**
	 * Checks to see if the queue is empty.
	 * @return True if the size of the queue is zero.
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the size of the current Queue.
	 * @return Size of the Queue.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Sets the capacity of the Queue using the value passed in as
	 * the capacity limit.
	 * @param capacity Capacity of the Queue.
	 */
	@Override
	public void setCapacity(int capacity) {
		queue.setCapacity(capacity); //delegated to LinkedAbstractList
		this.capacity = capacity;
		
	}

}
