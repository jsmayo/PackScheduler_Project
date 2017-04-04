package edu.ncsu.csc216.pack_scheduler.util;

public interface Queue<E> {

	/**
	 * Adds an element to the back of the queue.
	 * @param element Element to add to the queue.
	 */
	public void enqueue(E element);
	
	/**
	 * Removes the front element from the queue.
	 * @throws NoSuchElementException if the queue is empty.
	 * @return E element at the front of the queue.
	 */
	public E dequeue();
	
	/**
	 * Checks to see if the queue contains elements.
	 * @return True if the queue is empty.
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of elements within the queue.
	 * @return Number of elements within the queue.
	 */
	public int size();
	
	/**
	 * Sets the capacity of the queue.
	 * @throws IllegalArgumentException if the capacity parameter
	 * is less than the size or a negative number. 
	 * @param capacity Capacity of the list.
	 */
	public void setCapacity(int capacity);
}
