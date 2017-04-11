package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Custom ArrayQueue Class for the Pack Scheduler application.
 * 
 * @author Steven Mayo
 *
 * @param <E> Generic Object of Type E.
 */
public class ArrayQueue<E> implements Queue<E> {
	/**ArrayList to use as queue */
	private ArrayList<E> queue;
	/**Size of the queue */
	private int size = 0;
	/**Capacity of the queue. */
	private int capacity;
	
	/**
	 * Constructs the ArrayQueue with a acacity of 10 and a current size of 0,
	 * meaning that the Queue is empty. 
	 * @param capacity Capacity of the Queue. 
	 */
	public ArrayQueue(int capacity){
		queue = new ArrayList<>();
		this.size = 0;
		setCapacity(capacity);
	}

	/**
	 * Add's the passed in argument to the back of the Queue.
	 * @throws IllegalArgumentException if the Queue is at capacity.
	 */
	@Override
	public void enqueue(E element) {
		if(size() >= capacity) throw new IllegalArgumentException();
		queue.add(element);
		this.size++;		
	}

	/**
	 * Removes the first element of the Queue.
	 * @throws NoSuchElementException if there is not an Element to remove.
	 */
	@Override
	public E dequeue() {
		if(size() <= 0) throw new NoSuchElementException();
		this.size--;
		return queue.remove(0);
	}

	/**
	 * Checks to see if the Queue is empty.
	 * @return True if the size of the queue is 0.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the Queue.
	 * @return Returns the size of the Queue. 
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Set's the Capacity of the Queue.
	 * @throws IllegalArgumentException if the capacity is less than 0 or less than the amount
	 * of Elements already added to the queue. 
	 * @param capacity Capacity of the queue. 
	 */
	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size()) throw new IllegalArgumentException();
		this.capacity = capacity;
	
	}

}
